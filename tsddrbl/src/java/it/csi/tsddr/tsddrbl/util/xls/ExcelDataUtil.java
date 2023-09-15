/**
 * SPDX-FileCopyrightText: Copyright 2022 - 2023 | Regione Piemonte
 * SPDX-License-Identifier: EUPL-1.2
 */
package it.csi.tsddr.tsddrbl.util.xls;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ExcelDataUtil {
	
	public static String SEPARATOR = "_";
	public SimpleDateFormat formatDate = new SimpleDateFormat("ddMMyyyy");
	
    private String newFileName(String prefix, String suffix) {
        String name = (prefix.replace(" ", SEPARATOR) + SEPARATOR + formatDate.format(new Date()) + suffix  +".xlsx");
        if(name.length()>255) name = name.substring(0, 255);
        return name;
    }

    private String fileName;
    private XSSFWorkbook workbook = new XSSFWorkbook();

    private List<ExcelSheet> excelSheetList = new ArrayList<>();

    public ExcelDataUtil(String fileNamePrefix, String fileNameSuffix) {
        this.fileName = newFileName(fileNamePrefix, fileNameSuffix);
    }
    public ExcelSheet addSheet(String sheetName){
        excelSheetList.add(new ExcelSheet(workbook,sheetName));
        return excelSheetList.get(excelSheetList.size()-1);
    }
    public ExcelSheet findSheetByName(String sheetName){
        for (ExcelSheet sheet : excelSheetList)
            if (sheet.getSheet().getSheetName().equalsIgnoreCase(sheetName)) return sheet;
        return null;
    }
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public void close() throws IOException {
        workbook.close();
    }

    public byte[] getFile() throws IOException {
        ByteArrayOutputStream outputStream = null;
        try {
            outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return outputStream.toByteArray();
        }finally{
            if (outputStream != null) outputStream.close();
        }
    }

    public static void main(String[] args) throws IOException {
//        ExcelDataUtil excel = new ExcelDataUtil("Test", "filtro impostati da fe");
//        ExcelSheet sheet = excel.addSheet("prova");
//		sheet.addColumn("CF/PI");
//        sheet.addColumn("Ragione Sociale");
//        sheet.addColumn("Natuta G.");
//        sheet.addColumn("DATA INIZIO Validità");
//        sheet.addColumn("DATA FINE Validità");
//        sheet.addColumn("Indirizzo sede legale");
//        sheet.addColumn("Comune");
//        sheet.addColumn("CAP");
//        sheet.addColumn("Prov");
//        sheet.addColumn("Telefono");
//        sheet.addColumn("Email");
//        sheet.addColumn("PEC");
//        List<Object> dati = new ArrayList<>();
//        sheet.addDataToBody("ciao", "miao","bau", "ciao", "miao","bau", "ciao", "miao","bau", "ciao", "miao","bau");
//        File file = new File("C:\\TEST\\" + excel.getFileName());
//        if (!file.exists()) file.createNewFile();
//        FileOutputStream result = new FileOutputStream(file);
//        result.write(excel.getFile());
//        result.flush();
//        result.close();
    }
}