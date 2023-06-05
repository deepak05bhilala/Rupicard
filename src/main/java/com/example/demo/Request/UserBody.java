package com.example.demo.Request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserBody {

    @JsonProperty(value = "name")
    String name;

    @JsonProperty(value = "phone_no")
    String phoneNo;
}
