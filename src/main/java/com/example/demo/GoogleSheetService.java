package com.example.demo;

import com.example.demo.models.User;

import java.io.IOException;

public interface GoogleSheetService {

    void writeClassElementsToSheet(User user) throws IOException;
}
