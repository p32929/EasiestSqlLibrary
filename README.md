# EasiestSqlLibrary
The Easiest and the Laziest approach to Android SQL Database. If you like Flutter, the flutter version of this library can be found here -> [Github](https://github.com/p32929/EasiestdbFlutter) or [pub.dev](https://pub.dev/packages/easiestdb) 


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

* You don't need to add the "ID" primary key column. ```EasiestSqlLibrary``` does that by default.

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

I hope, you will enjoy using the library. Feel free to contribute codes.

If you want to use the previous version of this library, you can still use that from here: https://github.com/p32929/AndroidEasySQL-Library

## License

MIT License — Copyright (c) 2020 Fayaz Bin Salam. See [LICENSE](LICENSE) for the full text.

## Contributing

Contributions are warmly welcomed and greatly appreciated! Whether it's a bug fix, new feature, or improvement, your input helps make this project better for everyone.

Before submitting a pull request, please:

1. Create an issue describing the feature or bug fix you'd like to work on
2. Wait for discussion and approval to ensure alignment with project goals
3. Fork the repository and create your feature branch
4. Submit your pull request with a clear description of changes

This approach helps avoid duplicate efforts and ensures smooth collaboration. Thank you for considering contributing!

## Share

Sharing this repository with your friends is just one click away from here

[![facebook](https://user-images.githubusercontent.com/6418354/179013321-ac1d1452-0689-493f-9066-940cf2302b6e.png)](https://www.facebook.com/sharer/sharer.php?u=https://github.com/p32929/EasiestSqlLibrary/)
[![twitter](https://user-images.githubusercontent.com/6418354/179013351-7d8d6d1c-4ce2-46ab-bef8-4c4765a1b888.png)](https://twitter.com/intent/tweet?url=https://github.com/p32929/EasiestSqlLibrary/)
[![tumblr](https://user-images.githubusercontent.com/6418354/179013343-3111f55a-3b90-40c7-8487-9777348672b0.png)](https://www.tumblr.com/share?v=3&u=https://github.com/p32929/EasiestSqlLibrary/)
[![pocket](https://user-images.githubusercontent.com/6418354/179013334-b095c45f-becf-49f4-9ee1-5a731a9b1f85.png)](https://getpocket.com/save?url=https://github.com/p32929/EasiestSqlLibrary/)
[![pinterest](https://user-images.githubusercontent.com/6418354/179013331-44cd9206-11b1-4b65-becb-5863b61c828f.png)](https://pinterest.com/pin/create/button/?url=https://github.com/p32929/EasiestSqlLibrary/)
[![reddit](https://user-images.githubusercontent.com/6418354/179013338-7416ae3f-73ba-4522-86e1-1374d7082d22.png)](https://www.reddit.com/submit?url=https://github.com/p32929/EasiestSqlLibrary/)
[![linkedin](https://user-images.githubusercontent.com/6418354/179013327-ca7b7102-1da8-4b1c-858f-1a6e5f21bd70.png)](https://www.linkedin.com/shareArticle?mini=true&url=https://github.com/p32929/EasiestSqlLibrary/)
[![whatsapp](https://user-images.githubusercontent.com/6418354/179013353-f477fa0b-3e6f-4138-a357-c9991b23ff88.png)](https://api.whatsapp.com/send?text=https://github.com/p32929/EasiestSqlLibrary/)

---

## Support

If this saved you time, you can buy me a coffee — it keeps these projects maintained and free. Other payment options: https://p32929.github.io/SendMoney2MeV1/

[![Buy Me A Coffee](https://img.shields.io/badge/Buy%20me%20a%20coffee-%E2%98%95-FFDD00?style=for-the-badge&logo=buymeacoffee&logoColor=black)](https://www.buymeacoffee.com/p32929)

<!-- hire-block -->

---

## 💼 Need this customised — or need it yesterday?

I take fixed-price native Android work on my own projects. No hourly billing, no surprise scope:

| | |
|---|---|
| **Drop-in integration** — I wire this into your codebase and hand you a PR that builds | **$45** · 3 days |
| **Priority bug fix or small feature** — jumps ahead of the free issue queue | **$95** · 72 hours |
| **Custom build** — branded, packaged and deployed, source yours | **$130** · 7 days |
| **A full app from scratch** | **from $350** · quoted first |

All prices and how to buy → **[p32929.github.io/hire](https://p32929.github.io/hire/)**  
Or buy through [Fiverr](https://www.fiverr.com/fayazbinsalam) (escrow, ID-verified, 5.0★) — safest for a first job.

Scoping and quotes are free: [open an issue](https://github.com/p32929/hire/issues/new) and describe the job.
