/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import it.csi.tsddr.tsddrbl.filter.iride.dto.UserInfo;
import it.csi.tsddr.tsddrbl.filter.iride.entity.Identita;
import it.csi.tsddr.tsddrbl.vo.common.SelectVO;
import it.csi.tsddr.tsddrbl.vo.menu.MenuCardVO;
import it.csi.tsddr.tsddrbl.vo.response.GenericResponse;
import it.csi.tsddr.tsddrbl.vo.userinfo.UserInfoVO;

/**
 * The Interface LoginService.
 */
public interface LoginService {
	
	/**
	 * Populate user info.
	 *
	 * @param identita the identita
	 * @return the user info
	 */
	UserInfo populateUserInfo(Identita identita);

	/**
	 * Gets the id profilo.
	 *
	 * @param httpSession the http session
	 * @return the id profilo
	 */
	GenericResponse<UserInfoVO> getIdProfilo(HttpSession httpSession);
	
	/**
	 * Gets the struttura menue card.
	 *
	 * @param httpSession the http session
	 * @return the struttura menue card
	 */
	GenericResponse<List<MenuCardVO>> getStrutturaMenueCard(HttpSession httpSession); 
	
	/**
	 * Gets the combo profilo login.
	 *
	 * @param httpSession the http session
	 * @return the combo profilo login
	 */
	GenericResponse<List<SelectVO>> getComboProfiloLogin(HttpSession httpSession);
	
	/**
	 * Log cambio profilo.
	 *
	 * @param httpSession the http session
	 * @param idProfilo the id profilo
	 * @return the generic response
	 */
	GenericResponse<String> logCambioProfilo(HttpSession httpSession, Long idProfilo);
	
}
