package com.example.StudentCourse.exceptions;

import lombok.Data;

@Data
public class ParameterException extends RuntimeException{

    private String errorFiled;
    private String errorMsg;

    public ParameterException(){
        super("Pls check parameter");
    }

    public ParameterException(String errorMsg){
        super(errorMsg);
        this.errorFiled = "Not difine";
        this.errorMsg = errorMsg;
    }

    public ParameterException(String errorFiled,String errorMsg){
        super(errorMsg);
        this.errorFiled = errorFiled;
        this.errorMsg = errorMsg;
    }

}
