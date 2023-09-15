/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.util;

import java.util.Date;

import it.csi.tsddr.tsddrbl.business.be.entity.AbstractEntity;
import it.csi.tsddr.tsddrbl.business.be.entity.AbstractValidableEntity;

public class EntityUtil {
	
	public static <T extends AbstractEntity> T setInserted(T entity, Long idUtente, Date targetDate) {
		entity.setDataInsert(targetDate);
		entity.setIdUserInsert(idUtente);
		return entity;
	}
	
	public static <T extends AbstractValidableEntity> T setInsertedWithValidity(T entity, Long idUtente, Date targetDate) {
		entity.setDataInizioValidita(targetDate);
		entity.setDataFineValidita(null);
		return setInserted(entity, idUtente, targetDate);
	}
	
	public static <T extends AbstractEntity> T setUpdated(T entity, Long idUtente, Date targetDate) {
		entity.setDataUpdate(targetDate);
		entity.setIdUserUpdate(idUtente);
		return entity;
	}
	
	public static <T extends AbstractEntity> T setDeleted(T entity, Long idUtente, Date targetDate) {
		entity.setDataDelete(targetDate);
		entity.setIdUserDelete(idUtente);
		return entity;		
	}
	
	public static <T extends AbstractValidableEntity> T setDeletedWithValidity(T entity, Long idUtente, Date targetDate) {
		entity.setDataFineValidita(targetDate);
		return setDeleted(entity, idUtente, targetDate);
	}

}
