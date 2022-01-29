package com.solveus.exception;

import lombok.Getter;

@Getter
public class PhoneDuplicateException extends RuntimeException{

    private ErrorCode errorCode;

    public PhoneDuplicateException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}
