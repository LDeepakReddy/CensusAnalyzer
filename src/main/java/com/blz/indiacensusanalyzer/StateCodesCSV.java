package com.blz.indiacensusanalyzer;

import com.opencsv.bean.CsvBindByName;

public class StateCodesCSV {
    @CsvBindByName(column = "SrNo")
    public int srNo;
    @CsvBindByName(column = "StateName")
    public String stateName;
    @CsvBindByName(column = "TIN")
    public int tin;
    @CsvBindByName(column = "StateCode")
    public String stateCode;

    public StateCodesCSV(int srNo, String stateName, int tin, String stateCode) {
        this.srNo = srNo;
        this.stateName = stateName;
        this.tin = tin;
        this.stateCode = stateCode;
    }

    public StateCodesCSV() {

    }

    @Override
    public String toString() {
        return "StateCodesCSV{" +
                "srNo=" + srNo +
                ", stateName='" + stateName + '\'' +
                ", tin=" + tin +
                ", stateCode='" + stateCode + '\'' +
                '}';
    }
}

