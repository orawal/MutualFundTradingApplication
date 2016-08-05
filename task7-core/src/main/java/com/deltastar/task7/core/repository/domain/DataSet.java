package com.deltastar.task7.core.repository.domain;

public class DataSet {


    private String fillColor = "rgba(220,220,220,0.5)";
    private String strokeColor = "rgba(220,220,220,0.8)";
    private String highlightFill = "rgba(220,220,220,0.75)";
    private String highlightStroke = "rgba(220,220,220,1)";
    private double[] data;

    public String getFillColor() {
        return fillColor;
    }

    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
    }

    public String getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(String strokeColor) {
        this.strokeColor = strokeColor;
    }

    public String getHighlightFill() {
        return highlightFill;
    }

    public void setHighlightFill(String highlightFill) {
        this.highlightFill = highlightFill;
    }

    public String getHighlightStroke() {
        return highlightStroke;
    }

    public void setHighlightStroke(String highlightStroke) {
        this.highlightStroke = highlightStroke;
    }

    public double[] getData() {
        return data;
    }

    public void setData(double[] data) {
        this.data = data;
    }
}