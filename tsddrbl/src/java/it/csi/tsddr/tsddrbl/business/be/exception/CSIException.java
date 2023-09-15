/**
 *  SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 *  SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.business.be.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

public class CSIException extends Exception
{
    static final long serialVersionUID = 8485894515601068477L;
    public static final String NO_TRACE_AVAILABLE = "<<no stack trace available>>";
    public static final String NO_NESTED_EXC = "";
    private String nestedExcClassName;
    private String stackTraceMessage;
    private String nestedExcMsg;
    
    /**
     * Instantiates a new CSI exception.
     *
     * @param msg the msg
     */
    public CSIException(final String msg) {
        super(msg);
        this.nestedExcClassName = "";
        this.nestedExcMsg = null;
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw);
        this.fillInStackTrace();
        this.printStackTrace(pw);
        this.stackTraceMessage = sw.toString();
    }
    
    /**
     * Instantiates a new CSI exception.
     *
     * @param msg the msg
     * @param nestedExcClassName the nested exc class name
     * @param nestedExcMsg the nested exc msg
     */
    public CSIException(final String msg, final String nestedExcClassName, final String nestedExcMsg) {
        super(msg);
        this.nestedExcClassName = nestedExcClassName;
        this.nestedExcMsg = nestedExcMsg;
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw);
        this.fillInStackTrace();
        this.printStackTrace(pw);
        this.stackTraceMessage = sw.toString();
    }
    
    /**
     * Instantiates a new CSI exception.
     *
     * @param msg the msg
     * @param nestedExcClassName the nested exc class name
     * @param nestedExcMsg the nested exc msg
     * @param stackTrace the stack trace
     */
    public CSIException(final String msg, final String nestedExcClassName, final String nestedExcMsg, final String stackTrace) {
        super(msg);
        this.nestedExcClassName = nestedExcClassName;
        this.nestedExcMsg = nestedExcMsg;
        this.stackTraceMessage = stackTrace;
    }
    
    /**
     * Instantiates a new CSI exception.
     *
     * @param msg the msg
     * @param nestedExc the nested exc
     */
    public CSIException(final String msg, final Throwable nestedExc) {
        super(msg);
        if (nestedExc != null) {
            this.nestedExcClassName = nestedExc.getClass().getName();
            this.nestedExcMsg = nestedExc.getMessage();
        }
        else {
            this.nestedExcClassName = "";
            this.nestedExcMsg = null;
        }
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw);
        this.fillInStackTrace();
        this.printStackTrace(pw);
        this.stackTraceMessage = sw.toString();
    }
    
    /**
     * Gets the nested exc class name.
     *
     * @return the nested exc class name
     */
    public String getNestedExcClassName() {
        return this.nestedExcClassName;
    }
    
    /**
     * Sets the nested exc class name.
     *
     * @param nestedExcClassName the new nested exc class name
     */
    public void setNestedExcClassName(final String nestedExcClassName) {
        this.nestedExcClassName = nestedExcClassName;
    }
    
    /**
     * Sets the stack trace message.
     *
     * @param stackTraceMessage the new stack trace message
     */
    public void setStackTraceMessage(final String stackTraceMessage) {
        this.stackTraceMessage = stackTraceMessage;
    }
    
    /**
     * Gets the stack trace message.
     *
     * @return the stack trace message
     */
    public String getStackTraceMessage() {
        return this.stackTraceMessage;
    }
    
    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        String s = this.getClass().getName() + ":" + this.getMessage();
        if (this.getNestedExcClassName() != null && !this.getNestedExcClassName().equals("")) {
            s = s + " (nested: " + this.getNestedExcClassName() + ", " + this.nestedExcMsg + ")";
        }
        return s;
    }
    
    /**
     * Sets the nested exc msg.
     *
     * @param nestedExcMsg the new nested exc msg
     */
    public void setNestedExcMsg(final String nestedExcMsg) {
        this.nestedExcMsg = nestedExcMsg;
    }
    
    /**
     * Gets the nested exc msg.
     *
     * @return the nested exc msg
     */
    public String getNestedExcMsg() {
        return this.nestedExcMsg;
    }
}