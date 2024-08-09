package com.pragma.powerup.application.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ResponseDto<T> {
    private int statusCode;
    private T data;
    private List<String> errors;

    public ResponseDto() {}

    public ResponseDto(int statusCode, T data, List<String> errors) {
        this.statusCode = statusCode;
        this.data = data;
        this.errors = errors;
    }

}