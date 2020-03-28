package org.richit.easiestsqllib;

public class Datum {
    private String columnName = "", value = "";
    private int columnNumber;

    public Datum(String columnName, String value) {
        this.columnName = columnName.replace(" ", "_").toUpperCase();
        this.value = value;
    }

    public Datum(String columnName, int value) {
        this.columnName = columnName.toUpperCase();
        this.value = String.valueOf(value);
    }

    public Datum(String columnName, double value) {
        this.columnName = columnName.toUpperCase();
        this.value = String.valueOf(value);
    }

    public Datum(int columnNumber, String value) {
        this.columnNumber = columnNumber;
        this.value = value;
    }

    public Datum(int columnNumber, int value) {
        this.columnNumber = columnNumber;
        this.value = String.valueOf(value);
    }

    public Datum(int columnNumber, double value) {
        this.columnNumber = columnNumber;
        this.value = String.valueOf(value);
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(int columnNumber) {
        this.columnNumber = columnNumber;
    }
}
