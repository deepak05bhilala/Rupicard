package com.example.demo.Controller;


import com.example.demo.GoogleSheetService;
import com.example.demo.Request.UserBody;
import com.example.demo.models.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    GoogleSheetService googleSheetService;

    @PostMapping("/user-details")
    ResponseEntity<?> addUserData(@RequestBody User userBody ){
        try{
            googleSheetService.writeClassElementsToSheet(userBody);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            ResponseEntity.badRequest().body("get Error While Adding User");
        }

        return ResponseEntity.ok("User added successfully");
    }

}
