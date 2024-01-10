package com.example.StudentCourse.entities;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

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





    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Data
    public static class ErrorObject implements Serializable {
        private String error;
        private String reason;
        private String field;

    }
}
