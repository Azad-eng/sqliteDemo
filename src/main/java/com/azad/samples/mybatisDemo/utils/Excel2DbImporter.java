package com.azad.samples.mybatisDemo.utils;

import com.azad.samples.mybatisDemo.entity.Intensity;
import com.azad.samples.mybatisDemo.service.IntensityService;
import com.azad.samples.mybatisDemo.service.impl.IntensityServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
public class Excel2DbImporter {
    public void import2Db() {
        IntensityService intensityService = IntensityServiceImpl.intensityServiceImpl;
        XSSFWorkbook workbook;
        try {
            FileInputStream fis = new FileInputStream("IntensityRecord.xlsx");
            workbook = new XSSFWorkbook(fis);
        } catch (IOException e) {
            log.error("读取Excel IO异常", e);
            return;
        }
        List<Intensity> intensityList = new ArrayList<>();
        int numberOfSheets = workbook.getNumberOfSheets();
        for (int i = 0; i < numberOfSheets; i++) {
            XSSFSheet sheet = workbook.getSheetAt(i);
            for (Row row : sheet) {
                if (row.getRowNum() == 0){
                    continue;
                }
                Intensity intensity = new Intensity();
                StringBuilder stringBuilder = new StringBuilder();
                Iterator<Cell> cellIterator = row.cellIterator();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
                        stringBuilder.append(cell.getStringCellValue()).append(";");
                    } else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
                        if (cell.getCellStyle().getDataFormat() == workbook.getCreationHelper().createDataFormat()
                                .getFormat("yyyy-MM-dd HH:mm:ss")){
                            stringBuilder.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cell.getDateCellValue()))
                                    .append(";");
                        } else {
                            stringBuilder.append(cell.getNumericCellValue()).append(";");
                        }
                    }
                }
                System.out.println(stringBuilder.toString());
                String[] strings = stringBuilder.toString().split(";");
                intensity.setOpenTime(strings[0]);
                intensity.setCloseTime(strings[1]);
                intensity.setTime(Float.parseFloat(strings[2]));
                intensity.setIntensity(Double.parseDouble(strings[3]));
                intensityList.add(intensity);
            }
        }
        intensityService.saveBatch(intensityList);
    }
}
