package com.example.demo;

import com.example.demo.Config.SheetConfig;
import com.example.demo.models.User;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.Sheet;
import com.google.api.services.sheets.v4.model.Spreadsheet;
import com.google.api.services.sheets.v4.model.SpreadsheetProperties;
import com.google.api.services.sheets.v4.model.ValueRange;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class GoogleSheetsServiceImpl implements GoogleSheetService{


   SheetConfig sheetConfig = null;

   Sheets sheetsService = null;

    private String spreadSheetId = null;




    public  String createSpreadsheet(String title) throws IOException {
        Spreadsheet spreadsheet = new Spreadsheet().setProperties(
                new SpreadsheetProperties().setTitle(title)
        );
        spreadsheet = sheetsService.spreadsheets().create(spreadsheet).execute();
        return spreadsheet.getSpreadsheetId();
    }
    public void writeToSheet(String range, List<List<Object>> values) throws IOException {
        try {
            ValueRange body = new ValueRange().setValues(values);
            if(spreadSheetId == null){
                spreadSheetId = createSpreadsheet("rupicard");
                sheetsService.spreadsheets().values().append(spreadSheetId, range, body);
            }
            else
            sheetsService.spreadsheets().values()
                    .update(spreadSheetId, range, body)
                    .setValueInputOption("RAW")
                    .execute();

        }catch ( Exception e){
            System.out.println(e.getMessage());
        }

    }


    public void writeClassElementsToSheet(User user) throws IOException {

        try{

            if(sheetConfig == null){
               sheetConfig = new SheetConfig();
            }

            if( sheetsService == null){
                sheetsService = sheetConfig.createSheetsService();
            }
            List<List<Object>> values = new ArrayList<>();

            // Convert class elements to ValueRange format
            List<Object> row = new ArrayList<>();
            row.add(user.getName());
            row.add(user.getPhone());
            // Add more properties as needed
            values.add(row);

            // Specify the range where the data should be written
            String range = "Sheet1!A2:B"; // Example range, adjust as per your requirements
            writeToSheet(range, values);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }
}







