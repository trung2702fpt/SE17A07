/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import DataAsset.HistoryCheckoutDAO;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.HistoryCheckout;

public class DownloadExcel {

    public static void writeUsersToExcel(String filePath) throws ClassNotFoundException, SQLException {
        List<HistoryCheckout> historyList = HistoryCheckoutDAO.GetHistoryCheckout();
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("HistoryCheckout");

            // Create a header row
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Usre Name");
            headerRow.createCell(1).setCellValue("Room ID");
            headerRow.createCell(2).setCellValue("Booking Date");
            headerRow.createCell(3).setCellValue("Cancel Date");

            int rowNum = 1;

            for (HistoryCheckout user : historyList) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(user.getUserName());
                row.createCell(1).setCellValue(user.getRoomID());
                row.createCell(2).setCellValue(user.getBookingDate().toString());
                row.createCell(3).setCellValue(user.getCancelDate().toString());

            }

            // Write the workbook to the output file
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }

            System.out.println("Excel file has been created successfully at: " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
