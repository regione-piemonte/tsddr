/*
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.integration.doqui.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.apache.log4j.Logger;

import java.io.InputStream;
import java.io.Reader;

public class XmlSerializer {

	private static XStream xstream = new XStream(new DomDriver());


//	public static final String LOGGER_PREFIX = DoquiConstants.LOGGER_PREFIX + ".util";
//	private static Logger log = Logger.getLogger(LOGGER_PREFIX);	
	private static Logger log = Logger.getLogger(XmlSerializer.class);
	
	private static final String XML_TO_OBJECT = "xmlToObject";
	private static final String XML_TO_OBJECT_ERROR = ". xmlToObject error ";
	
	public XmlSerializer() {
	    // Questo commento è stato aggiunto per rendere il codice compatibile con le regole di SonarQube.
	}
	
	public static String objectToXml(Object obj){
		String method = "objectToXml";
		String s = null;
		try{
			s= xstream.toXML(obj);
		}
		catch(Exception e){
			log.error(method + ". objectToXml error " + e.getMessage());
		}
		return s;
	}

	public static Object xmlToObject(String xml){
		String method = XML_TO_OBJECT;
		Object obj=null;
		try{
		obj  = xstream.fromXML(xml);
		}
		catch(Exception e){
			log.error(method + XML_TO_OBJECT_ERROR + e.getMessage());
		}
		return obj;
	}
	public static Object xmlToObject(InputStream is){
		String method = XML_TO_OBJECT;
		Object obj=null;
		try{
			obj = xstream.fromXML(is);
		}
		catch(Exception e){
			log.error(method + XML_TO_OBJECT_ERROR + e.getMessage());
		}
		return obj;
	}
	public static Object xmlToObject(Reader reader){
		String method = XML_TO_OBJECT;
		Object obj=null;
		try{
			obj = xstream.fromXML(reader);
		}
		catch(Exception e){
			log.error(method + XML_TO_OBJECT_ERROR + e.getMessage());
		}
		return obj;
	}

}
