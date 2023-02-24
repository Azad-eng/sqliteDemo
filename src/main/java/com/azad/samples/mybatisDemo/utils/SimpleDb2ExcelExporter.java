package com.azad.samples.mybatisDemo.utils;

import com.azad.samples.mybatisDemo.service.IntensityService;
import com.azad.samples.mybatisDemo.service.impl.IntensityServiceImpl;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

public class SimpleDb2ExcelExporter {
    String excelFilePath = "Reviews-export.xlsx";

    public void export() {
        IntensityService intensityService = IntensityServiceImpl.intensityServiceImpl;

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Reviews");
        writeHeaderLine(sheet);

        AtomicInteger rowCount = new AtomicInteger(1);

        intensityService.list().forEach(e -> {
            Row row = sheet.createRow(rowCount.getAndIncrement());

            String openTime = e.getOpenTime();
            String closeTime = e.getCloseTime();
            float time = e.getTime();
            double intensity = e.getIntensity();
            Date createTime = e.getCreateTime();
            Date updateTime = e.getUpdateTime();

            int columnCount = 0;
            Cell cell = row.createCell(columnCount++);
            cell.setCellValue(openTime);

            cell = row.createCell(columnCount++);
            cell.setCellValue(closeTime);

            cell = row.createCell(columnCount++);
            cell.setCellValue(time);

            cell = row.createCell(columnCount++);
            cell.setCellValue(intensity);

            cell = row.createCell(columnCount++);
            CellStyle cellStyle = workbook.createCellStyle();
            CreationHelper creationHelper = workbook.getCreationHelper();
            cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
            cell.setCellStyle(cellStyle);
            cell.setCellValue(createTime);

            cell = row.createCell(columnCount);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(updateTime);
        });

        try {
            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException e) {
            System.out.println("File IO error:");
            e.printStackTrace();
        }
    }

    public void exportByJDBC() {
        String jdbcURL = "jdbc:mysql://localhost:3306/sales";
        String username = "root";
        String password = "password";
        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
            String sql = "SELECT * FROM review";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Reviews");
            writeHeaderLine(sheet);
            writeDataLines(result, workbook, sheet);
            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
            workbook.write(outputStream);
            workbook.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Datababse error:");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("File IO error:");
            e.printStackTrace();
        }
    }

    private void writeHeaderLine(XSSFSheet sheet) {

        Row headerRow = sheet.createRow(0);

        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("Course Name");

        headerCell = headerRow.createCell(1);
        headerCell.setCellValue("Student Name");

        headerCell = headerRow.createCell(2);
        headerCell.setCellValue("Timestamp");

        headerCell = headerRow.createCell(3);
        headerCell.setCellValue("Rating");

        headerCell = headerRow.createCell(4);
        headerCell.setCellValue("Comment");
    }


    private void writeDataLines(ResultSet result, XSSFWorkbook workbook,
                                XSSFSheet sheet) throws SQLException {
        int rowCount = 1;
        while (result.next()) {
            String courseName = result.getString("course_name");
            String studentName = result.getString("student_name");
            float rating = result.getFloat("rating");
            Timestamp timestamp = result.getTimestamp("timestamp");
            String comment = result.getString("comment");
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            Cell cell = row.createCell(columnCount++);
            cell.setCellValue(courseName);
            cell = row.createCell(columnCount++);
            cell.setCellValue(studentName);
            cell = row.createCell(columnCount++);
            CellStyle cellStyle = workbook.createCellStyle();
            CreationHelper creationHelper = workbook.getCreationHelper();
            cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
            cell.setCellStyle(cellStyle);
            cell.setCellValue(timestamp);
            cell = row.createCell(columnCount++);
            cell.setCellValue(rating);
            cell = row.createCell(columnCount);
            cell.setCellValue(comment);
        }
    }
}
