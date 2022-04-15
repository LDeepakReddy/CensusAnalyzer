package com.blz.indiacensusanalyzer;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class CensusAnalyzer {
    public int loadIndiaCensusData(String csvPath) throws CensusAnalyzerException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvPath));
            CsvToBean<IndiaCensusCSV> csvToBean = new CsvToBeanBuilder<IndiaCensusCSV>(reader)
                    .withType(IndiaCensusCSV.class)
                    .build();
            Iterator<IndiaCensusCSV> censusCSVIterator = csvToBean.iterator();
            int numOfEntries = 0;
            while (censusCSVIterator.hasNext()) {
                numOfEntries++;
                censusCSVIterator.next();
            }
            return numOfEntries;

        } catch (IOException e) {
            throw new CensusAnalyzerException(e.getMessage(), CensusAnalyzerException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (TypeNotPresentException e) {
            throw new CensusAnalyzerException(e.getMessage(), CensusAnalyzerException.ExceptionType.INCORRECT_FILE_TYPE);

        } catch (RuntimeException e) {
            throw new CensusAnalyzerException(e.getMessage(), CensusAnalyzerException.ExceptionType.INCORRECT_HEADER);


        }
    }

    public int loadIndianStateCodeData(String csvFilePath) throws CensusAnalyzerException {
        try {
            if (csvFilePath.contains("txt")) {
                throw new CensusAnalyzerException("File must be in CSV Format", CensusAnalyzerException.ExceptionType.INCORRECT_FILE_TYPE);
            }
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            CsvToBean<StateCodesCSV> csvToBean = new CsvToBeanBuilder<StateCodesCSV>(reader)
                    .withType(StateCodesCSV.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            Iterator<StateCodesCSV> iterator = csvToBean.iterator();
            Iterable<StateCodesCSV> csvIterable = () -> iterator;
            int count = (int) StreamSupport.stream(csvIterable.spliterator(), true).count();
            return count;
        } catch (RuntimeException e) {
            throw new CensusAnalyzerException("CSV File Must Have Comma As Delimiter", CensusAnalyzerException.ExceptionType.INCORRECT_DELIMETER);
        } catch (IOException e) {
            throw new CensusAnalyzerException(e.getMessage(), CensusAnalyzerException.ExceptionType.INCORRECT_HEADER);
        }
    }
}

