package com.blz.indiacensusanalyzer;

import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusAnalyzerTest {
    private static final String INDIAN_CENSUS_CSV_FILE_PATH = "C:\\Users\\DEEPAK REDDY\\Desktop\\Java_Bridgelabz\\IndiaCensus_Analyzer\\src\\main\\resources\\IndiaStateCensusData.csv";
    private static final String INDIAN_CENSUS_CSV_WRONG_FILE_PATH = "C:\\Users\\DEEPAK REDDY\\IndiaCensus_Analyzer\\src\\main\\IndiaStateCensusData.csv";
    private static final String INDIAN_CENSUS_WRONG_FILE_TYPE = "C:\\Users\\DEEPAK REDDY\\Desktop\\Java_Bridgelabz\\IndiaCensus_Analyzer\\src\\main\\resources\\IndiaStateCensusData.txt";
    private static final String INDIAN_CENSUS_WITHWRONG_DELIMITER = "C:\\Users\\DEEPAK REDDY\\Desktop\\Java_Bridgelabz\\IndiaCensus_Analyzer\\src\\main\\resources\\IndiaStateCensusDataWithWrongDelimeter.csv";
    private static final String INDIAN_CENSUS_WITHWRONG_HEADER = "C:\\Users\\DEEPAK REDDY\\Desktop\\Java_Bridgelabz\\IndiaCensus_Analyzer\\src\\main\\resources\\IndiaStateCensusDataWrongHeader.csv";

    private String INIDAN_STATE_CODE_CSV_FILE_PATH = "C:\\Users\\DEEPAK REDDY\\Desktop\\Java_Bridgelabz\\IndiaCensus_Analyzer\\src\\main\\resources\\IndiaStateCode.csv";

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

    @Test
    public void givenIndianCensusCSVFile_WhenWrongDelimiter_ShouldThrowException() {

        try {
            ExpectedException exception = ExpectedException.none();
            exception.expect(CensusAnalyzerException.class);
            censusAnalyzer.loadIndiaCensusData(INDIAN_CENSUS_WITHWRONG_DELIMITER);
        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(CensusAnalyzerException.ExceptionType.INCORRECT_DELIMETER, e.exceptionType);
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusCSVFile_WhenWrongHeader_ShouldThrowException() {

        try {

            ExpectedException exception = ExpectedException.none();
            exception.expect(CensusAnalyzerException.class);
            censusAnalyzer.loadIndiaCensusData(INDIAN_CENSUS_WITHWRONG_HEADER);
        } catch (CensusAnalyzerException e) {
            Assert.assertEquals(CensusAnalyzerException.ExceptionType.INCORRECT_HEADER, e.exceptionType);

        }
    }

    @Test
    public void givenIndianStateCodeCSVFile_WhenLoad_ShouldReturnCorrectRecords() throws CensusAnalyzerException {
        int count = censusAnalyzer.loadStateCodeData(INIDAN_STATE_CODE_CSV_FILE_PATH);
        Assert.assertEquals(37, count);

    }
}
