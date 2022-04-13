package com.blz.indiacensusanalyzer;

import org.junit.Assert;
import org.junit.Test;

public class CensusAnalyzerTest {
    private static final String INDIAN_CENSUS_CSV_FILE_PATH = "C:\\Users\\DEEPAK REDDY\\Desktop\\Java_Bridgelabz\\IndiaCensus_Analyzer\\src\\main\\resources\\IndiaStateCensusData.csv";

    @Test
    public void givenIndianCensusCSVFile_WhenLoad_ShouldReturnCorrectRecords() {

        CensusAnalyzer censusAnalyzer = new CensusAnalyzer();
        int numOfRecords = censusAnalyzer.loadIndiaCensusData(INDIAN_CENSUS_CSV_FILE_PATH);
        Assert.assertEquals(29, numOfRecords);
    }
}
