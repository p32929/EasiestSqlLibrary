# EasiestSqlLibrary
The Easiest and Laziest approach to Android SQL Database.

## Installation
Add it in your root build.gradle at the end of repositories:
```
allprojects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}
```

Add the dependency
```
dependencies {
     implementation 'com.github.p32929:EasiestSqlLibrary:1.0.0.1'
}
```

## Basic Usage
Steps to follow:
1. Initialize
2. Add Tables and Columns
3. Call `doneAddingTables` method

After that, you can do all kinds of CRUD ( Create, Read, Update, Delete ) operations.

## Initialize the Database and the Tables
###### EasiestDB.init(Context context)
###### easiestDB.addTableColumns(String tableName, Column... columns)
###### easiestDB.doneAddingTables()

```
EasiestDB easiestDB = EasiestDB.init(this)
                    .addTableColumns("table 1",
                            new Column("Column a1", "text"),
                            new Column("Column a2", "text")
                    )
                    .addTableColumns("table 2",
                            new Column("Column b1", "text"),
                            new Column("Column b2", "text", "unique")
                    )
                    .doneAddingTables();
```

* You don't need to add the "ID" primary key column. ```EasiestSqlLibrary``` does that by default.

## Add data
###### easiestDB.addDataInTable(int tableIndex, Datum... data)
###### Datum(String columnName, String value)
###### Datum(String columnName, int value)
###### Datum(String columnName, double value)
###### Datum(int columnIndex, String value)
###### Datum(int columnIndex, int value)
###### Datum(int columnIndex, double value)

```
boolean added = easiestDB.addDataInTable(0,
        new Datum(1, "Value1"),
        new Datum(2, "Value2")
);
```

## Get All data from a table
###### easiestDB.getAllDataFrom(int tableIndex)
###### easiestDB.getAllDataFrom(String tableName)

```
Cursor cursor = easiestDB.getAllDataFrom(0);
if (cursor != null) {
    while (cursor.moveToNext()) {
        int value1 = cursor.getInt(columnIndex);
        String value2 = cursor.getString(columnIndex);
        double value3 = cursor.getDouble(columnIndex);
    }
}
```
or
###### easiestDB.getAllDataOrderedBy(int columnIndex, boolean ascending, int tableIndex)

```
Cursor cursor = easiestDB.getAllDataOrderedBy(0, true, 0);
if (cursor != null) {
    while (cursor.moveToNext()) {
        int value1 = cursor.getInt(columnIndex);
        String value2 = cursor.getString(columnIndex);
        double value3 = cursor.getDouble(columnIndex);
    }
}
```

## Get All data from a row within a table
###### easiestDB.getOneRowData(int tableIndex, int rowNumber)
###### easiestDB.getOneRowData(String tableName, int rowNumber)

```
Cursor cursor = easiestDB.getOneRowData(0, 0);
if (cursor != null) {
    cursor.moveToFirst();
    int value1 = cursor.getInt(columnIndex);
    String value2 = cursor.getString(columnIndex);
    double value3 = cursor.getDouble(columnIndex);
}
```

## Search value in a column within a table
###### easiestDB.searchInOneColumn(int columnIndex, String valueToSearch, int limit, int tableIndex)
###### easiestDB.searchInOneColumn(String columnName, String valueToSearch, int limit, int tableIndex)

```
Cursor cursor = easiestDB.searchInOneColumn(1, "Value1", 0, 0);
if (cursor != null) {
    cursor.moveToFirst();
    int value1 = cursor.getInt(columnIndex);
    String value2 = cursor.getString(columnIndex);
    double value3 = cursor.getDouble(columnIndex);
}
```

## Match values in multiple columns. ( Example: Matching ID and password for a user within a table )
###### easiestDB.searchValuesInMultipleColumns(int tableIndex, Datum... data)

```
Cursor cursor = easiestDB.searchValuesInMultipleColumns(1,
        new Datum(1, "Value1"),
        new Datum(2, "Value2")
);
boolean matched = cursor.getCount() > 0;
```

## Update data in a row
###### easiestDB.updateData(int tableIndex, int rowNumber, Datum... data)

```
boolean updated = easiestDB.updateData(0, 1,
        new Datum(1, "Value1.1"),
        new Datum(2, "Value2.2")
);
```

## Delete one row
###### easiestDB.deleteRow(int tableIndex, int rowNumber)

```
boolean deleted = easiestDB.deleteRow(0, 1);
```

## Delete a row if values match in a column
###### easiestDB.deleteRowIfValuesMatchIn(int tableIndex, Datum data)
###### easiestDB.deleteRowIfValuesMatchIn(int tableIndex, Datum data)

```
boolean deleted = easiestDB.deleteRowIfValuesMatchIn(0,
        new Datum(1, "Value1")
);
```

## Delete all data from a table
###### easiestDB.deleteAllDataFrom(String tableName)
###### easiestDB.deleteAllDataFrom(int tableIndex)

```
easiestDB.deleteAllDataFrom(0)
```

## Delete the all data from the database
###### easiestDB.deleteDatabase()

```
boolean deleted = easiestDB.deleteDatabase();
```

I hope, you will enjoy using the library. Feel free to contribute codes.

If you want to use the previous version of this library, you can still use that from here: https://github.com/p32929/AndroidEasySQL-Library

#### License

```
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
```