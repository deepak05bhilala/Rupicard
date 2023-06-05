package com.example.demo;

import com.opencsv.CSVWriter;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;

//@Component
public class CsvFileCreator {

    public static  void createCSVFile() {
        String[] headers = {"Name", "Age", "Email"};

        try (CSVWriter writer = new CSVWriter(new FileWriter("/Users/deepakbhilala/Documents/file.csv"))) {
            writer.writeNext(headers);

            // Add data to the CSV file
            String[] data1 = {"John Doe", "25", "john@example.com"};
            String[] data2 = {"Jane Smith", "30", "jane@example.com"};

            writer.writeNext(data1);
            writer.writeNext(data2);

            // You can add more rows of data as needed

            System.out.println("CSV file created successfully.");
        } catch (IOException e) {
            System.out.println("Error occurred while creating the CSV file.");
            e.printStackTrace();
        }
    }
}



