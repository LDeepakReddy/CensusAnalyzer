package com.blz.indiacensusanalyzer;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;

public class OpenCSV {
    public <T> Iterator getCSVIterator(Reader reader, Class csvClass) {
        CsvToBean<T> csvToBean = new CsvToBeanBuilder<IndiaCensusCSV>(reader)
                .withType(csvClass)
                .withIgnoreLeadingWhiteSpace(true)
                .build();
        Iterator<T> censusCSVIterator = csvToBean.iterator();
        return censusCSVIterator;
    }
}

