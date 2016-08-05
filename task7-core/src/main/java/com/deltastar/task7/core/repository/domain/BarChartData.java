package com.deltastar.task7.core.repository.domain;

public class BarChartData {


    private String[] labels;
    private DataSet[] datasets;

    public DataSet[] getDatasets() {
        return datasets;
    }

    public void setDatasets(DataSet[] datasets) {
        this.datasets = datasets;
    }

    public String[] getLabels() {
        return labels;
    }

    public void setLabels(String[] labels) {
        this.labels = labels;
    }
}