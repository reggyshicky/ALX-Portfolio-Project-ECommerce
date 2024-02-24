package com.reginah.Expensetrackerapi.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorObject {
    private Integer statusCode;
    private String message;
    private Date timeStamp;
    //model class that holds the information about the exception
}
