package com.cienet.util;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class UtilReadExcel {
	public static List readExcel(String filePath) throws Exception {
		List list = new LinkedList();
		try {
			// 创建对Excel工作薄文件的引用
			HSSFWorkbook wookbook = new HSSFWorkbook(new FileInputStream(filePath));
			
			HSSFSheet sheet = wookbook.getSheetAt(0);
			// 获取Excel的所有行
			int rows = sheet.getPhysicalNumberOfRows();
			if(rows<1) return list;
			// 遍历行
			for (int i = 1; i < rows; i++) {
				// 读取左上角单元格
				HSSFRow row = sheet.getRow(i);
				// 行不能为空
				if (row != null) {
					HashMap hashMap = new HashMap();
					// 获取Excel文件中的所以列
					int cells = row.getPhysicalNumberOfCells();
					
					// 遍历列
					for (short j = 0; j < cells; j++) {
						String cellValue = "";
						// 获取列的值
						HSSFCell cell = row.getCell(j);
						String cellStr = cell+"";
						if (cell != null) {
							switch (cell.getCellType()) {
							case HSSFCell.CELL_TYPE_STRING:   
							    cellValue = cell.getStringCellValue();   
							    if (cellValue.trim().equals("") || cellValue.trim().length() <= 0)   
							      cellValue = " ";   
							    break;   
							  case HSSFCell.CELL_TYPE_NUMERIC:   
							    cellValue = String.valueOf(cell.getNumericCellValue());   
							    break;   
							  case HSSFCell.CELL_TYPE_FORMULA:   
							    cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);   
							    cellValue = String.valueOf(cell.getNumericCellValue());   
							    break;   
							  case HSSFCell.CELL_TYPE_BLANK:   
							    cellValue = " ";   
							    break;   
							  case HSSFCell.CELL_TYPE_BOOLEAN:   
							    break;   
							  case HSSFCell.CELL_TYPE_ERROR:   
							    break;   
							  default:   
							    break; 
							}

						}
						String key = "excel"+Integer.toString(j);
						hashMap.put(key, cellValue);
					}
					list.add(hashMap);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return list;
	}
}
