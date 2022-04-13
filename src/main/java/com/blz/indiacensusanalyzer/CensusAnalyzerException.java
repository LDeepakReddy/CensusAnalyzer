package com.blz.indiacensusanalyzer;

public class CensusAnalyzerException extends Exception {
    public enum ExceptionType {
        CENSUS_FILE_PROBLEM
    }

    public ExceptionType exceptionType;
    public String message;

    CensusAnalyzerException(String message, ExceptionType exceptionType) {
        this.exceptionType = exceptionType;
        this.message = message;
    }


}
