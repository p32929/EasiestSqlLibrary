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

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class EasiestDB extends SQLiteOpenHelper {
    // Start from the bottom for better understanding
    private static String DATABASE_NAME = "DEMO_DATABASE.db";
    public ArrayList<Table> tableArrayList = new ArrayList<>();

    private SQLiteDatabase writableDatabase;
    private ContentValues contentValues;

    // Delete more
    public boolean deleteDatabase() {
        return context.deleteDatabase(DATABASE_NAME);
    }

    public void deleteAllDataFrom(String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + tableName);
    }

    public void deleteAllDataFrom(int tableIndex) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from " + tableIndex);
    }

    // Delete data
    public boolean deleteRow(int tableIndex, int rowNumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(tableArrayList.get(tableIndex).getTableName(), "id = ?", new String[]{String.valueOf(rowNumber)}) == 1;
    }

    public boolean deleteRowIfValuesMatchIn(int tableIndex, Datum data) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(tableArrayList.get(tableIndex).getTableName(), data.getColumnName() + " = ?", new String[]{String.valueOf(data.getValue())}) == 1;
    }

    // Update data
    public boolean updateData(int tableIndex, int rowNumber, Datum... data) {
        contentValues = new ContentValues();
        for (int i = 0; i < data.length; i++) {
            if (data[i].getColumnName().isEmpty()) {
                contentValues.put(tableArrayList.get(tableIndex).getColumns()[i].getColumnName(), data[i].getValue());
            } else {
                contentValues.put(data[i].getColumnName(), data[i].getValue());
            }
        }
        try {
            return writableDatabase.update(tableArrayList.get(tableIndex).getTableName(), contentValues, "id = ?", new String[]{String.valueOf(rowNumber)}) > 0;
        } catch (Exception e) {
            return false;
        }
    }

    // Search in multiple columns
    public Cursor searchValuesInMultipleColumns(int tableIndex, Datum... data) {
        String query = "";
        for (int i = 0; i < data.length; i++) {
            query += data[i].getColumnName() + " = ? ";
            if (i != data.length - 1) {
                query += " AND ";
            }
        }
        String[] columnsToMatch = new String[data.length];
        String[] valuesToMatch = new String[data.length];

        for (int i = 0; i < data.length; i++) {
            if (data[i].getColumnName().isEmpty()) {
                columnsToMatch[i] = tableArrayList.get(tableIndex).getColumns()[data[i].getColumnIndex()].getColumnName();
            } else {
                columnsToMatch[i] = data[i].getColumnName();
            }
            valuesToMatch[i] = data[i].getValue();
        }

        return writableDatabase.query(tableArrayList.get(tableIndex).getTableName(), columnsToMatch, query, valuesToMatch, null, null, null);
    }

    // Search
    public Cursor searchInOneColumn(int columnIndex, String valueToSearch, int limit, int tableIndex) {
        String allColNames[] = new String[tableArrayList.get(tableIndex).getColumns().length + 1];
        allColNames[0] = "ID";
        for (int i = 0; i < tableArrayList.get(tableIndex).getColumns().length; i++) {
            allColNames[i + 1] = tableArrayList.get(tableIndex).getColumns()[i].getColumnName();
        }
        Cursor cursor = writableDatabase.query(tableArrayList.get(tableIndex).getTableName(),
                allColNames, allColNames[columnIndex] + "=?",
                new String[]{valueToSearch},
                null, null, null, limit == -1 ? null : String.valueOf(limit));

        if (cursor.getCount() > 0) {
            return cursor;
        } else {
            return null;
        }
    }

    public Cursor searchInOneColumn(String columnName, String valueToSearch, int limit, int tableIndex) {
        String allColNames[] = new String[tableArrayList.get(tableIndex).getColumns().length + 1];
        allColNames[0] = "ID";
        for (int i = 0; i < tableArrayList.get(tableIndex).getColumns().length; i++) {
            allColNames[i + 1] = tableArrayList.get(tableIndex).getColumns()[i].getColumnName();
        }
        Cursor cursor = writableDatabase.query(tableArrayList.get(tableIndex).getTableName(),
                allColNames, " " + columnName + " " + "=?",
                new String[]{valueToSearch},
                null, null, null, limit == -1 ? null : String.valueOf(limit));

        if (cursor.getCount() > 0) {
            return cursor;
        } else {
            return null;
        }
    }

    // Get data
    public Cursor getAllDataFrom(int tableIndex) {
        Cursor res = writableDatabase.rawQuery("select * from " + tableArrayList.get(tableIndex).getTableName(), null);
        return res;
    }

    public Cursor getAllDataFrom(String tableName) {
        Cursor res = writableDatabase.rawQuery("select * from " + tableName, null);
        return res;
    }

    public Cursor getAllDataOrderedBy(int columnIndex, boolean ascending, int tableIndex) {
        String postfix = ascending ? "" : " DESC ";
        String colNam = columnIndex == 0 ? " ID " : tableArrayList.get(tableIndex).getColumns()[columnIndex - 1].getColumnName();
        Cursor res = writableDatabase.rawQuery("select * from " + tableArrayList.get(tableIndex).getTableName() + " ORDER BY " + colNam + postfix, null);
        return res;
    }

// #1

    private String[] getAllColumnsFromTable(int tableIndex) {
        Column[] columns = tableArrayList.get(tableIndex).getColumns();
        String allColumnNames[] = new String[columns.length + 1];
        allColumnNames[0] = "ID";
        for (int i = 0; i < columns.length; i++) {
            allColumnNames[i + 1] = columns[i].getColumnName();
        }

        return allColumnNames;
    }

    private String[] getAllColumnsFromTable(String tableName) {
        int tableIndex = -1;

        for (int i = 0; i < tableArrayList.size(); i++) {
            if (tableName.toUpperCase().equals(tableArrayList.get(i).getTableName())) {
                tableIndex = i;
                break;
            }
        }

        Column[] columns = tableArrayList.get(tableIndex).getColumns();
        String allColumnNames[] = new String[columns.length + 1];
        allColumnNames[0] = "ID";
        for (int i = 0; i < columns.length; i++) {
            allColumnNames[i + 1] = columns[i].getColumnName();
        }

        return allColumnNames;
    }


    public Cursor getOneRowData(int tableIndex, int rowNumber) {
        Cursor cursor = writableDatabase.query(tableArrayList.get(tableIndex).getTableName(),
                getAllColumnsFromTable(tableIndex), "ID=?",
                new String[]{String.valueOf(rowNumber)},
                null, null, null, "1");

        if (cursor.getCount() > 0) {
            return cursor;
        } else {
            return null;
        }
    }

    public Cursor getOneRowData(String tableName, int rowNumber) {
        Cursor cursor = writableDatabase.query(tableName.replace(" ", ""),
                getAllColumnsFromTable(tableName), "ID=?",
                new String[]{String.valueOf(rowNumber)},
                null, null, null, "1");

        if (cursor.getCount() > 0) {
            return cursor;
        } else {
            return null;
        }
    }

    // Add Data
    public boolean addDataInTable(int tableIndex, Datum... data) {
        contentValues = new ContentValues();
        for (int i = 0; i < data.length; i++) {
            if (data[i].getColumnName().isEmpty()) {
                contentValues.put(getColumnNameFromTableAndcolumnIndex(tableIndex, data[i].getColumnIndex()), data[i].getValue());
            } else {
                contentValues.put(data[i].getColumnName(), data[i].getValue());
            }
        }
        long result = writableDatabase.insert(tableArrayList.get(tableIndex).getTableName(), null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }

// #2

    //
    private String getColumnNameFromTableAndcolumnIndex(int tableIndex, int columnIndex) {
        return tableArrayList.get(tableIndex).getColumns()[columnIndex - 1].getColumnName();
    }

// #3

    // Add Table
    public EasiestDB addTableColumns(String tableName, Column... columns) {
        Table table = new Table(
                tableName.replace(" ", "_"),
                columns
        );

        String SQL = " CREATE TABLE " + table.getTableName() + " ( ID INTEGER PRIMARY KEY AUTOINCREMENT, ";
        for (int i = 0; i < table.getColumns().length; i++) {
            SQL += " " + columns[i].getColumnName() + " " + columns[i].getColumnDataType() + " ";
            if (i == columns.length - 1) {
                SQL += " ) ";
            } else {
                SQL += " , ";
            }
        }
        table.setSql(SQL);
        tableArrayList.add(table);
        return this;
    }

    public EasiestDB doneAddingTables() {
        writableDatabase = getWritableDatabase();
        return this;
    }

    // Init
    public static EasiestDB init(Context context) {
        return new EasiestDB(context, DATABASE_NAME, null, 1);
    }

    public static EasiestDB init(Context context, String dbName) {
        if (!dbName.endsWith(".db"))
            dbName += ".db";
        DATABASE_NAME = dbName.replaceAll(" ", "_").toUpperCase();
        return new EasiestDB(context, DATABASE_NAME, null, 1);
    }

    public static EasiestDB init(Context context, String dbName, int version) {
        if (!dbName.endsWith(".db"))
            dbName += ".db";
        DATABASE_NAME = dbName.replaceAll(" ", "_").toUpperCase();
        return new EasiestDB(context, DATABASE_NAME, null, version);
    }

    public static EasiestDB init(Context context, String dbName, SQLiteDatabase.CursorFactory factory, int version) {
        if (!dbName.endsWith(".db"))
            dbName += ".db";
        DATABASE_NAME = dbName.replaceAll(" ", "_").toUpperCase();
        return new EasiestDB(context, dbName, factory, version);
    }

    public static EasiestDB init(Context context, String dbName, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        if (!dbName.endsWith(".db"))
            dbName += ".db";
        DATABASE_NAME = dbName.replaceAll(" ", "_");
        return new EasiestDB(context, DATABASE_NAME, factory, version, errorHandler);
    }

    // Stock
    private Context context;
    private SQLiteDatabase.CursorFactory factory;
    private int version;
    private DatabaseErrorHandler errorHandler;

    public EasiestDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

        //
        this.context = context;
        this.DATABASE_NAME = name;
        this.factory = factory;
        this.version = version;
    }

    public EasiestDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);

        //
        this.context = context;
        this.DATABASE_NAME = name;
        this.factory = factory;
        this.version = version;
        this.errorHandler = errorHandler;
    }

    //
    @Override
    public void onCreate(SQLiteDatabase db) {
        this.writableDatabase = db;
        for (int i = 0; i < tableArrayList.size(); i++) {
            db.execSQL(tableArrayList.get(i).getSql());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        for (int i = 0; i < tableArrayList.size(); i++) {
            db.execSQL(" DROP TABLE IF EXISTS " + tableArrayList.get(i).getTableName());
        }
        onCreate(db);
    }
}


// #1
//    public Cursor getAllDataOrderedBy(int columnIndex, boolean ascending, String tableName) {
//        String postfix = ascending ? "" : " DESC ";
//        String colNam = columnIndex == 0 ? " ID " : getColumnNameFromTableAndcolumnIndex(tableName, columnIndex);
//        Cursor res = writableDatabase.rawQuery("select * from " + tableName + " ORDER BY " + colNam + postfix, null);
//        return res;
//    }

// #2
//    public boolean addDataInTable(String tableName, Datum... data) {
//        contentValues = new ContentValues();
//        for (int i = 0; i < data.length; i++) {
//            if (data[i].getColumnName().isEmpty()) {
//                contentValues.put(getColumnNameFromTableAndcolumnIndex(tableName, data[i].getcolumnIndex()), data[i].getValue());
//            } else {
//                contentValues.put(data[i].getColumnName(), data[i].getValue());
//            }
//        }
//        long result = writableDatabase.insert(tableName, null, contentValues);
//
//        if (result == -1)
//            return false;
//        else
//            return true;
//    }

// #3
//    private String getColumnNameFromTableAndcolumnIndex(String tableName, int columnIndex) {
//        int index = -1;
//        for (int i = 0; i < tableArrayList.size(); i++) {
//            if (tableName.toUpperCase().equals(tableArrayList.get(i).getTableName())) {
//                index = i;
//                break;
//            }
//        }
//
//        return tableArrayList.get(index).getColumns()[columnIndex - 1].getColumnName();
//    }

// #4
