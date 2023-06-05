package com.example.demo.Config;

import com.example.demo.GoogleSheetsServiceImpl;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Objects;

public class SheetConfig {

    private static final String APPLICATION_NAME = "RupiCard";
    private static final String CREDENTIALS_FILE_PATH = "/Users/deepakbhilala/Documents/googleSheet.json";



    public Sheets createSheetsService() throws IOException, GeneralSecurityException {
        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
        GoogleCredential credentials = GoogleCredential.fromStream(
                        Objects.requireNonNull(GoogleSheetsServiceImpl.class.getResourceAsStream(CREDENTIALS_FILE_PATH)))
                .createScoped(List.of(SheetsScopes.SPREADSHEETS));

        return new Sheets.Builder(httpTransport, jsonFactory, credentials)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }
}
