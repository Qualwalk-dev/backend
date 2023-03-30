package com.qualwalk.backend.utility;

import com.qualwalk.backend.entity.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.util.*;

public class ExcelUtility {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERS = { "First name", "Last name", "Email ID", "Learning mode", "Message" };
    static String SHEET = "Enquiry";

    public static ByteArrayInputStream tutorialsToExcel(List<EnquiryEntity> enquiryEntities) {

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet(SHEET);

            // Header
            Row headerRow = sheet.createRow(0);

            for (int col = 0; col < HEADERS.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERS[col]);
            }

            int rowIdx = 1;
            for (EnquiryEntity enquiryEntity : enquiryEntities) {
                Row row = sheet.createRow(rowIdx++);

                row.createCell(0).setCellValue(enquiryEntity.getFirstname());
                row.createCell(1).setCellValue(enquiryEntity.getLastname());
                row.createCell(2).setCellValue(enquiryEntity.getEmail());
                row.createCell(3).setCellValue(enquiryEntity.getLearningModes().getOrdinal());
                row.createCell(4).setCellValue(enquiryEntity.getMessage());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }
}

