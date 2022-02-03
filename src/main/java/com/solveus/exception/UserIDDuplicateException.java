package com.solveus.exception;

import lombok.Getter;

@Getter
public class UserIDDuplicateException extends RuntimeException{

    private ErrorCode errorCode;

    public UserIDDuplicateException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}
