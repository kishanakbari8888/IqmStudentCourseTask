package com.example.StudentCourse.exceptions;

import lombok.Data;

@Data
public class SecurityException extends RuntimeException{
    private String errorFiled;
    private String errorReason;

    public SecurityException() {
        super("Security Error Occur Due to you Reason Value");
        this.errorFiled = "pls check your value";
        this.errorReason = "Reason is not define";
    }

    public SecurityException(String errorFiled){
        super(errorFiled);
        this.errorFiled = errorFiled;
        this.errorFiled = "Not define Reason";
    }

    public SecurityException(String errorFiled,String errorReason){
        super(errorFiled);
        this.errorFiled = errorFiled;
        this.errorReason = errorReason;
    }

}
