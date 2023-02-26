package com.azad.samples.mybatisDemo.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

public class Excel2DbImporter {
    public void import2Db() throws IOException {
        FileInputStream fis = new FileInputStream("IntensityRecord.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()){
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()){
                Cell cell = cellIterator.next();
                if (cell.getCellType() == Cell.CELL_TYPE_STRING){
                    System.out.println(cell.getStringCellValue());
                } else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                    System.out.println(cell.getNumericCellValue());
                }
            }
        }
    }
}
