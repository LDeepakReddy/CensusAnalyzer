package com.blz.indiacensusanalyzer;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;


public class CensusAnalyzer {
    static int numOfEntries = 0;

    public int loadIndiaCensusData(String csvPath) throws CensusAnalyzerException {
        try {
            Reader reader = Files.newBufferedReader(Paths.get(csvPath));
            CsvToBean<IndiaCensusCSV> csvToBean = new CsvToBeanBuilder<IndiaCensusCSV>(reader)
                    .withType(IndiaCensusCSV.class)
                    .build();
            Iterator<IndiaCensusCSV> censusCSVIterator = csvToBean.iterator();

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

    public int loadStateCodeData(String csvPath) throws CensusAnalyzerException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvPath))) {
            Iterator<StateCodesCSV> censusCSVIterator = new OpenCSV().getCSVIterator(reader, StateCodesCSV.class);
            Iterable<StateCodesCSV> csvIterator = () -> censusCSVIterator;
            return this.getCount(csvIterator);

        } catch (NoSuchFileException e) {
            if (!csvPath.contains(".csv")) {
                throw new CensusAnalyzerException(e.getMessage(), CensusAnalyzerException.ExceptionType.INCORRECT_FILE_TYPE);
            }
        } catch (IOException e) {
            throw new CensusAnalyzerException(e.getMessage(), CensusAnalyzerException.ExceptionType.STATE_CODE_FILE_PROBLEM);

        } catch (RuntimeException e) {
            throw new CensusAnalyzerException(e.getMessage(), CensusAnalyzerException.ExceptionType.INCORRECT_DELIMETER);
        }
        return 0;
    }

    private <E> int getCount(Iterable<E> csvIterator) {
        return (int) StreamSupport.stream(csvIterator.spliterator(), true)
                .count();

    }
}
