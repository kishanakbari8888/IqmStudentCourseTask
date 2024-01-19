package com.example.StudentCourse.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import com.example.StudentCourse.exceptions.ParameterException;
import com.example.StudentCourse.exceptions.SecurityException;
import com.fasterxml.jackson.annotation.JsonInclude;

@Data
public class CommonResponse<T> implements Serializable {
    private static final long serialVersionUID = 718459248673647639L;
    private boolean success;
    private T data;
    private List<ErrorObject> errorObjects;

    public CommonResponse(){
        this.setSuccess(true);
    }

    public CommonResponse(List<ErrorObject> errorObjects){
        this.setSuccess(false);
        this.setErrorObjects(errorObjects);
    }

    public CommonResponse(T data){
        this.setSuccess(true);
        this.setData(data);
    }

    public CommonResponse(ErrorObject e){
        errorObjects = new LinkedList<>();
        errorObjects.add(e);
    }

    public CommonResponse(ParameterException e){
        ErrorObject errorObject = new ErrorObject("parameter not proper",e.getErrorMsg(),e.getErrorFiled());
        errorObjects = new LinkedList<>();
        errorObjects.add(errorObject);
    }

    public CommonResponse(SecurityException e){
        ErrorObject errorObject = new ErrorObject("parameter not proper",e.getErrorReason(),e.getErrorFiled());
        errorObjects = new LinkedList<>();
        errorObjects.add(errorObject);
    }

    public CommonResponse(Exception e){
        ErrorObject errorObject = new ErrorObject("unknown","check you req body again","don't know");
        errorObjects = new LinkedList<>();
        errorObjects.add(errorObject);
    }


    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ErrorObject implements Serializable {
        private String error;
        private String reason;
        private String field;

    }
}
