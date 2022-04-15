package com.blz.indiacensusanalyzer;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusAnalyzerTest {
    private static final String INDIAN_CENSUS_CSV_FILE_PATH = "C:\\Users\\DEEPAK REDDY\\Desktop\\Java_Bridgelabz\\IndiaCensus_Analyzer\\src\\main\\resources\\IndiaStateCensusData.csv";
    private static final String INDIAN_CENSUS_CSV_WRONG_FILE_PATH = "C:\\Users\\DEEPAK REDDY\\IndiaCensus_Analyzer\\src\\main\\IndiaStateCensusData.csv";
    private static final String INDIAN_CENSUS_WRONG_FILE_TYPE = "C:\\Users\\DEEPAK REDDY\\Desktop\\Java_Bridgelabz\\IndiaCensus_Analyzer\\src\\main\\resources\\IndiaStateCensusData.txt";
    private static final String INDIAN_CENSUS_WITHWRONG_DELIMITER = "C:\\Users\\DEEPAK REDDY\\Desktop\\Java_Bridgelabz\\IndiaCensus_Analyzer\\src\\main\\resources\\IndiaStateCensusDataWithWrongDelimeter.csv";
    CensusAnalyzer censusAnalyzer = new CensusAnalyzer();

    @Test
    public void givenIndianCensusCSVFile_WhenLoad_ShouldReturnCorrectRecords() throws CensusAnalyzerException {
        int numOfRecords = censusAnalyzer.loadIndiaCensusData(INDIAN_CENSUS_CSV_FILE_PATH);
        Assert.assertEquals(29, numOfRecords);
    }

    @Test
    public void givenIndianCensusWrongCSVFile_WhenLoad_ShouldReturnException() {
        try {
            int numOfRecords = censusAnalyzer.loadIndiaCensusData(INDIAN_CENSUS_CSV_WRONG_FILE_PATH);
            Assert.assertEquals(29, numOfRecords);

        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(e.exceptionType, CensusAnalyzerException.ExceptionType.CENSUS_FILE_PROBLEM);
        }

    }

    @Test
    public void givenIndianCensusCSVFile_WhenCorrectPathButWrongFileFormat_ShouldThrowException() {

        try {
            int numOfRecord = censusAnalyzer.loadIndiaCensusData((INDIAN_CENSUS_WRONG_FILE_TYPE));
            Assert.assertEquals(29, numOfRecord);
        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(e.exceptionType, CensusAnalyzerException.ExceptionType.INCORRECT_FILE_TYPE);

        }
    }

}
