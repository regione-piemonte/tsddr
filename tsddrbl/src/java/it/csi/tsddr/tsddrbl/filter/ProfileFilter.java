/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.filter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.filter.GenericFilterBean;

import it.csi.tsddr.tsddrbl.business.be.exception.ForbiddenException;
import it.csi.tsddr.tsddrbl.business.be.service.ProfiloService;
import it.csi.tsddr.tsddrbl.filter.iride.dto.UserInfo;
import it.csi.tsddr.tsddrbl.util.Constants;
import it.csi.tsddr.tsddrbl.util.DbConstants;
import it.csi.tsddr.tsddrbl.util.LoggerUtil;
import it.csi.tsddr.tsddrbl.util.enums.CodiceFunzione;
import it.csi.tsddr.tsddrbl.util.web.AclUtil;
import it.csi.tsddr.tsddrbl.util.web.EndpointFunzione;
import it.csi.tsddr.tsddrbl.util.web.EndpointFunzione.FunzType;
import it.csi.tsddr.tsddrbl.util.web.SessionUtil;
import it.csi.tsddr.tsddrbl.vo.profilo.FunzionalitaProfiloVO;

/**
 * Controlla ACL sulle richieste in base al profilo corrente
 */
@Component
public class ProfileFilter extends GenericFilterBean {

	private static final Logger log = Logger.getLogger(ProfileFilter.class);
	private static final String UTENTI_PROFILO = "/utenti-profilo";
	private static final String UTENTI = "/utenti";
	private static final String UTENTE_GESTORE_PROFILO = "/utenti/utente-gestore-profilo";
	
	private List<String> ignorePaths = List.of("/login/id-profilo");

	private Map<Long, List<String>> profilePaths = Map.of(
//			1L,			List.of("/funzionalita-profilo", "/profili", "/profilo-utente"), 
//			2L, List.of("/profili")
			);

	private Map<CodiceFunzione, List<EndpointFunzione>> functionPaths = Map.of(
			CodiceFunzione.AM_005,
			List.of(EndpointFunzione.create(UTENTI_PROFILO, HttpMethod.GET, FunzType.READ),
					EndpointFunzione.create(UTENTI_PROFILO, HttpMethod.POST, FunzType.INSERT),
					EndpointFunzione.create(UTENTI_PROFILO, HttpMethod.DELETE, FunzType.DELETE)),
			CodiceFunzione.AM_002,
			List.of(EndpointFunzione.create("/utenti/dati-utente", HttpMethod.GET, FunzType.READ),
					EndpointFunzione.create(UTENTI, HttpMethod.POST, FunzType.INSERT),
					EndpointFunzione.create(UTENTE_GESTORE_PROFILO, HttpMethod.POST, FunzType.INSERT),
					EndpointFunzione.create(UTENTI, HttpMethod.PUT, FunzType.UPDATE),
					EndpointFunzione.create(UTENTE_GESTORE_PROFILO, HttpMethod.PUT, FunzType.UPDATE),
					EndpointFunzione.create(UTENTI, HttpMethod.DELETE, FunzType.DELETE),
					EndpointFunzione.create(UTENTE_GESTORE_PROFILO, HttpMethod.DELETE, FunzType.DELETE)));

	@Autowired
	private ProfiloService profiloService;

	@Autowired
	private AclUtil aclUtil;

	@Value("${tsddrbl.endpoint_base}")
	private String basePath;

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
			throws IOException, ServletException {
		LoggerUtil.debug(log, "[ProfileFilter::doFilter] BEGIN");
		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) res;

		String path = StringUtils.substringAfter(request.getRequestURI(),
				String.format("%s%s", basePath, Constants.APPLICATION_PATH));
		
		if (ignorePaths.contains(path)) {
			// il path è ignorato-> la richiesta prosegue senza controlli
			LoggerUtil.debug(log,
					String.format("[ProfileFilter::doFilter] Path ignorato [%s] -> la richiesta viene servita", path));
		} else {
			Long currentProfileId = null;
			
			if (StringUtils.isNotBlank(request.getHeader(SessionUtil.IDPROFILO_SESSIONATTR))) {
				currentProfileId = NumberUtils.createLong(request.getHeader(SessionUtil.IDPROFILO_SESSIONATTR));
				
				UserInfo userInfo = SessionUtil.getSessionAttibute(request.getSession(true),
						SessionUtil.USERINFO_SESSIONATTR, UserInfo.class);
	
				List<Long> userProfiles = profiloService.getIdProfiliByCodiceFiscale(userInfo.getCodFisc());
				
				if (CollectionUtils.isEmpty(userProfiles)) {
					userProfiles.add(DbConstants.IDPROFILO_UTENTE_NON_ACCREDITATO);
				}
				
				if (!userProfiles.contains(currentProfileId)) {
					LoggerUtil.warn(log,
							String.format("[ProfileFilter::doFilter] Profilo non associato all'utente [path: %s] -> la richiesta viene bloccata", path));
					throw new ForbiddenException("Profilo non associato all'utente -> la richiesta viene bloccata");
				}
				
				SessionUtil.setSessionAttribute(request.getSession(true), SessionUtil.IDPROFILO_SESSIONATTR, currentProfileId);
			}
			
			String httpMethod = request.getMethod();
			
			// check acl su funzione
			Entry<CodiceFunzione, List<EndpointFunzione>> functionPath = functionPaths.entrySet().stream()
					.filter(e -> e.getValue().stream().anyMatch(ef -> path.equalsIgnoreCase(ef.getEndpoint())))
					.map(e -> Map.entry(e.getKey(), e.getValue().stream().filter(ef -> ef.getMethod().matches(httpMethod)).collect(Collectors.toList())))
					.findFirst().orElse(null);
			
			if (functionPath != null) {
				FunzionalitaProfiloVO funzProf = aclUtil.getACLPerProfilo(currentProfileId, functionPath.getKey());
				
				boolean authorized = true;
				
				switch (HttpMethod.valueOf(httpMethod)) {
				case GET:
					authorized = funzProf.getRead();
					break;
				case POST:
					authorized = funzProf.getInsert();
					break;
				case PUT:
					authorized = funzProf.getUpdate();
					break;
				case DELETE:
					authorized = funzProf.getDelete();
					break;
				default:
					break;
				}
				
				if (!authorized) {
					// il profilo utente non è autorizzato ad eseguire l'operazione richiesta
					LoggerUtil.warn(log,
							String.format("[ProfileFilter::doFilter] Profilo corrente NON abilitato ad eseguire l'operazione [path: %s] -> la richiesta viene bloccata", path));
					throw new ForbiddenException("Profilo corrente NON abilitato ad eseguire l'operazione -> la richiesta viene bloccata");
				}
			}
			
			// estraggo tutti i profili per i quali è definito il path
			List<Long> pathProfiles = profilePaths.entrySet().stream()
					.filter(e -> e.getValue().stream().anyMatch(p -> path.startsWith(p))).map(Map.Entry::getKey)
					.collect(Collectors.toList());
	
			if (CollectionUtils.isEmpty(pathProfiles)) {
				// il path non è profilato -> la richiesta prosegue senza controlli
				LoggerUtil.debug(log, String.format("[ProfileFilter::doFilter] path non profilato [%s] -> la richiesta viene servita", path));
	
			} else {
				if (currentProfileId == null) {
					LoggerUtil.warn(log,
							String.format("[ProfileFilter::doFilter] Header 'idProfilocurrentUser' non presente nella request [path: %s] -> la richiesta viene bloccata", path));
					throw new ForbiddenException(
							"Header 'idProfilocurrentUser' non presente nella request -> la richiesta viene bloccata");
				}
				
				if (!pathProfiles.contains(currentProfileId)) {
					// il path è ristretto ai profili "pathProfiles" e il profilo corrente NON matcha
					// NESSUNO di questi -> la richiesta NON può essere servita
					LoggerUtil.warn(log,
							String.format("[ProfileFilter::doFilter] Profilo corrente NON abilitato al path [%s] -> la richiesta viene bloccata", path));
					throw new ForbiddenException("Profilo corrente NON abilitato al path -> la richiesta viene bloccata");
				} else {
					// il path è ristretto ai profili "pathProfiles" e il profilo corrente matcha uno di
					// questi -> la richiesta può essere servita
					LoggerUtil.debug(log,
							String.format("[ProfileFilter::doFilter] Profilo corrente abilitato al path [%s] -> la richiesta viene servita", path));
				}
			}
		}
		
		LoggerUtil.debug(log, "[ProfileFilter::doFilter] END");
		filterChain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// NOP
	}

}
