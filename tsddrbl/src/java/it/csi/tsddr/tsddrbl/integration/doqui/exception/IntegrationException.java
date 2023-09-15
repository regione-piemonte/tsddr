/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.integration.doqui.exception;

 /**
 * Eccezione da creare quando impossibile accedere alle risorse necessarie
 * nel tear di integration
 */
public class IntegrationException extends Exception
{
   
    /**
	 * 
	 */
	private static final long serialVersionUID = -7156599370005938086L;
	
	private Exception itsOriginalException;
        
    public IntegrationException(final String aMessage, final Exception anException)
    {
        super(aMessage);
        this.itsOriginalException = anException;
    }
    
    public IntegrationException(final String aMessage){
        super(aMessage);
        
    }
    
    public void printStackTrace()
    {
        this.itsOriginalException.printStackTrace();
    }

	public Exception getItsOriginalException() {
		return itsOriginalException;
	}

	public void setItsOriginalException(Exception itsOriginalException) {
		this.itsOriginalException = itsOriginalException;
	}
    
    
    
}
