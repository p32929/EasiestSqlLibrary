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

public class Column {

    //
    private String columnName = "", columnDataType = "";

    public Column(String columnName, String... columnDataTypes) {
        this.columnName = columnName.replaceAll(" ", "_").toUpperCase();
        String finalDatatype = "";
        for (int i = 0; i < columnDataTypes.length; i++) {
            if (!columnDataTypes[i].startsWith(" ")) {
                columnDataTypes[i] = " " + columnDataTypes[i];
            }
            if (!columnDataTypes[i].endsWith(" ")) {
                columnDataTypes[i] = columnDataTypes[i] + " ";
            }
            finalDatatype += columnDataTypes[i];
        }
        this.columnDataType = finalDatatype.toUpperCase();
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnDataType() {
        return columnDataType;
    }

    public void setColumnDataType(String columnDataType) {
        this.columnDataType = columnDataType;
    }
}
