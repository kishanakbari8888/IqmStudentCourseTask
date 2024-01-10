package com.example.StudentCourse.exceptions;

public class ParameterException extends RuntimeException{

    private String errorMsg;
    private String errorFiled;

    public ParameterException(){
        super("Pls check parameter");
    }

    public ParameterException(String errorMsg){
        super(errorMsg);
        this.errorFiled = "Not difine";
        this.errorMsg = errorMsg;
    }

}
