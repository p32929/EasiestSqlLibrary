# EasiestSqlLibrary

A small SQLite library for Android that lets you work with several tables at once without writing SQL. Describe your tables and columns once, then add, read, search, update and delete rows with one call each. Every table gets an auto-increment `ID` column for free.

Looking for something else?

1. A single-table library that is rewritten in Kotlin (2026): [AndroidEasySQL-Library](https://github.com/p32929/AndroidEasySQL-Library).
2. The same idea for Flutter: [EasiestdbFlutter](https://github.com/p32929/EasiestdbFlutter) or [easiestdb on pub.dev](https://pub.dev/packages/easiestdb).

## Installation
Add it in your root build.gradle at the end of repositories:
```gradle
allprojects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}
```

Add the dependency
```gradle
dependencies {
     implementation 'com.github.p32929:EasiestSqlLibrary:1.0.0.2'
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

```java
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

You don't need to add the `ID` primary key column. EasiestSqlLibrary adds it for you.

## Add data
###### easiestDB.addDataInTable(int tableIndex, Datum... data)
###### Datum(String columnName, String value)
###### Datum(String columnName, int value)
###### Datum(String columnName, double value)
###### Datum(int columnIndex, String value)
###### Datum(int columnIndex, int value)
###### Datum(int columnIndex, double value)

```java
boolean added = easiestDB.addDataInTable(0,
        new Datum(1, "Value1"),
        new Datum(2, "Value2")
);
```

## Get All data from a table
###### easiestDB.getAllDataFrom(int tableIndex)
###### easiestDB.getAllDataFrom(String tableName)

```java
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

```java
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

```java
Cursor cursor = easiestDB.getOneRowData(0, 1); // rowNumber starts from 1 but tableIndex starts from 0
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

```java
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

```java
Cursor cursor = easiestDB.searchValuesInMultipleColumns(1,
        new Datum(1, "Value1"),
        new Datum(2, "Value2")
);
boolean matched = cursor.getCount() > 0;
```

## Update data in a row
###### easiestDB.updateData(int tableIndex, int rowNumber, Datum... data)

```java
boolean updated = easiestDB.updateData(0, 1,
        new Datum(1, "Value1.1"),
        new Datum(2, "Value2.2")
);
```

## Delete one row
###### easiestDB.deleteRow(int tableIndex, int rowNumber)

```java
boolean deleted = easiestDB.deleteRow(0, 1);
```

## Delete a row if values match in a column
###### easiestDB.deleteRowIfValuesMatchIn(int tableIndex, Datum data)
###### easiestDB.deleteRowIfValuesMatchIn(int tableIndex, Datum data)

```java
boolean deleted = easiestDB.deleteRowIfValuesMatchIn(0,
        new Datum(1, "Value1")
);
```

## Delete all data from a table
###### easiestDB.deleteAllDataFrom(String tableName)
###### easiestDB.deleteAllDataFrom(int tableIndex)

```java
easiestDB.deleteAllDataFrom(0)
```

## Delete the all data from the database
###### easiestDB.deleteDatabase()

```java
boolean deleted = easiestDB.deleteDatabase();
```

I hope you enjoy using the library.

## License

MIT License — Copyright (c) 2020 Fayaz Bin Salam. See [LICENSE](LICENSE) for the full text.

## Contributing

Contributions are very welcome, thank you! Before you start on a pull request, please open an issue and tell me what you would like to add or change. That way we can agree on it first, and your time is not wasted on something that might not fit. Thanks for helping make EasiestSqlLibrary better.

## You might also like

<p><a href="https://openstore.pfay.workers.dev/more/p32929/easiestsqllibrary/personal/0"><picture><source media="(prefers-color-scheme: dark)" srcset="https://openstore.pfay.workers.dev/more/p32929/easiestsqllibrary/personal/0-dark.svg"><img src="https://openstore.pfay.workers.dev/more/p32929/easiestsqllibrary/personal/0-light.svg" width="50%" align="top" alt="More apps by p32929 on OpenStore (1)"></picture></a><a href="https://openstore.pfay.workers.dev/more/p32929/easiestsqllibrary/personal/1"><picture><source media="(prefers-color-scheme: dark)" srcset="https://openstore.pfay.workers.dev/more/p32929/easiestsqllibrary/personal/1-dark.svg"><img src="https://openstore.pfay.workers.dev/more/p32929/easiestsqllibrary/personal/1-light.svg" width="50%" align="top" alt="More apps by p32929 on OpenStore (2)"></picture></a><br><a href="https://openstore.pfay.workers.dev/more/p32929/easiestsqllibrary/personal/2"><picture><source media="(prefers-color-scheme: dark)" srcset="https://openstore.pfay.workers.dev/more/p32929/easiestsqllibrary/personal/2-dark.svg"><img src="https://openstore.pfay.workers.dev/more/p32929/easiestsqllibrary/personal/2-light.svg" width="50%" align="top" alt="More apps by p32929 on OpenStore (3)"></picture></a><a href="https://openstore.pfay.workers.dev/more/p32929/easiestsqllibrary/personal/3"><picture><source media="(prefers-color-scheme: dark)" srcset="https://openstore.pfay.workers.dev/more/p32929/easiestsqllibrary/personal/3-dark.svg"><img src="https://openstore.pfay.workers.dev/more/p32929/easiestsqllibrary/personal/3-light.svg" width="50%" align="top" alt="More apps by p32929 on OpenStore (4)"></picture></a></p>

<p><a href="https://p32929.github.io/share/?repo=EasiestSqlLibrary"><picture><source media="(prefers-color-scheme: dark)" srcset="https://p32929.github.io/readme/footer-share-dark.svg"><img src="https://p32929.github.io/readme/footer-share-light.svg" width="50%" align="top" alt="Share this project with your friends in one click"></picture></a><a href="https://p32929.github.io/support/"><picture><source media="(prefers-color-scheme: dark)" srcset="https://p32929.github.io/readme/footer-support-dark.svg"><img src="https://p32929.github.io/readme/footer-support-light.svg" width="50%" align="top" alt="If you like my work and want to support me, feel free to support"></picture></a><br><a href="https://openstore.pfay.workers.dev/add"><picture><source media="(prefers-color-scheme: dark)" srcset="https://p32929.github.io/readme/footer-join-dark.svg"><img src="https://p32929.github.io/readme/footer-join-light.svg" width="100%" align="top" alt="Put your own apps on OpenStore: free, your listing lives in your own repo"></picture></a></p>
