package com.blz.indiacensusanalyzer;

import org.junit.Assert;
import org.junit.Test;

public class CensusAnalyzerTest {
    private static final String INDIAN_CENSUS_CSV_FILE_PATH = "C:\\Users\\DEEPAK REDDY\\Desktop\\Java_Bridgelabz\\IndiaCensus_Analyzer\\src\\main\\resources\\IndiaStateCensusData.csv";
    private static final String INDIAN_CENSUS_CSV_WRONG_FILE_PATH = "C:\\Users\\DEEPAK REDDY\\IndiaCensus_Analyzer\\src\\main\\IndiaStateCensusData.csv";

    @Test
    public void givenIndianCensusCSVFile_WhenLoad_ShouldReturnCorrectRecords() throws CensusAnalyzerException{

        CensusAnalyzer censusAnalyzer = new CensusAnalyzer();
        int numOfRecords = censusAnalyzer.loadIndiaCensusData(INDIAN_CENSUS_CSV_FILE_PATH);
        Assert.assertEquals(29, numOfRecords);
    }
    @Test
    public void givenIndianCensusWrongCSVFile_WhenLoad_ShouldReturnException() {
        try {
            CensusAnalyzer censusAnalyzer = new CensusAnalyzer();
            int numOfRecords=censusAnalyzer.loadIndiaCensusData(INDIAN_CENSUS_CSV_WRONG_FILE_PATH);
            Assert.assertEquals(29,numOfRecords);

        } catch (CensusAnalyzerException e) {
            Assert.assertEquals( e.exceptionType ,CensusAnalyzerException.ExceptionType.CENSUS_FILE_PROBLEM);
        }

    }
}
