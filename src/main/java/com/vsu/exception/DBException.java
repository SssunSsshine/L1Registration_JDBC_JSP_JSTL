package com.vsu.exception;

public class DBException extends RuntimeException {

    public DBException(Exception e) {
        super(e);
    }
}
