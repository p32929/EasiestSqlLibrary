package org.richit.easiestsqllib;

/*
MIT License

Copyright (c) 2020 Fayaz Bin Salam

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

public class Datum {
    private String columnName = "", value = "";
    private int columnIndex;

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

    public Datum(int columnIndex, String value) {
        this.columnIndex = columnIndex;
        this.value = value;
    }

    public Datum(int columnIndex, int value) {
        this.columnIndex = columnIndex;
        this.value = String.valueOf(value);
    }

    public Datum(int columnIndex, double value) {
        this.columnIndex = columnIndex;
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

    public int getColumnIndex() {
        return columnIndex;
    }

    public void setColumnIndex(int columnIndex) {
        this.columnIndex = columnIndex;
    }
}
