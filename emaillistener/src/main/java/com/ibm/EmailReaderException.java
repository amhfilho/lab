package com.ibm;

public class EmailReaderException extends RuntimeException {
    public EmailReaderException(String msg, Throwable t){
        super(msg,t);
    }
}
