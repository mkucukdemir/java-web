/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smallfe.accountactivitiesmonitor.parser;

/**
 *
 * @author mehmet.kucukdemir
 */
public class ExcelParserFactory {
    public static ExcelParser createExcelParser(int titleRow, int numberOfColumns, ExcelParserType type){
        switch(type){
            case POI_EXCEL_PARSER:
                return new PoiExcelParser(titleRow,numberOfColumns);
            default:
                return null;
        }
    }
}
