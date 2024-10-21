package org.example.csv;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.example.entity.Accounts;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ReadCSV {

    public static Accounts getAccountDetails(Row row) {
        String firstName = row.getCell(0) != null ? row.getCell(0).getStringCellValue() : "";
        String lastName = row.getCell(1) != null ? row.getCell(1).getStringCellValue() : "";
        long phoneNumber = row.getCell(2) != null ? Long.parseLong(row.getCell(2).getStringCellValue()) : 0L;
        String email = row.getCell(3) != null ? row.getCell(3).getStringCellValue() : "";

        if (!firstName.trim().isEmpty() && !lastName.trim().isEmpty() && !email.trim().isEmpty()) {
            return new Accounts(firstName, lastName, email, phoneNumber);
        }
        return null;
    }

    public static List<Accounts> readExcel(String filePath, String sheetName) {
        List<Accounts> accounts = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(filePath);
            Workbook wb = WorkbookFactory.create(fis);
            Sheet sheet = wb.getSheet(sheetName);
            int rowSize = sheet.getPhysicalNumberOfRows();

            // Start reading from row 1 (skipping the header row 0)
            for (int rowIndex = 1; rowIndex < rowSize; rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                // Create an Employee object and add it to the list
                if (row != null) {
                    Accounts account = getAccountDetails(row);
                    if(account != null)accounts.add(account);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accounts;
    }
}
