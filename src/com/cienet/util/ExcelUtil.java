package com.cienet.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ExcelUtil {

    public static String getCellStringValue(Row row, int index) {
        Cell cell = row.getCell(index);
        if (cell != null) {
            row.getCell(index).setCellType(Cell.CELL_TYPE_STRING);
            return row.getCell(index).getStringCellValue();
        } else {
            return null;
        }
    }
}