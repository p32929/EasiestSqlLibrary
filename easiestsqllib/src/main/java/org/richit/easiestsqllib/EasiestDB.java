package org.richit.easiestsqllib;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class EasiestDB extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "DEMO_DATABASE.db";
    private ArrayList<Table> tableArrayList = new ArrayList<>();

    private SQLiteDatabase writableDatabase;
    private ContentValues contentValues;

    // Get data
    public Cursor getAllDataFrom(int tableIndex) {
        Cursor res = writableDatabase.rawQuery("select * from " + tableArrayList.get(tableIndex).getTableName(), null);
        return res;
    }

    public Cursor getAllDataFrom(String tableName) {
        Cursor res = writableDatabase.rawQuery("select * from " + tableName, null);
        return res;
    }

    public Cursor getAllDataOrderedBy(int columnNumber, boolean ascending, int tableIndex) {
        String postfix = ascending ? "" : " DESC ";
        String colNam = columnNumber == 0 ? " ID " : tableArrayList.get(tableIndex).getColumns()[columnNumber - 1].getColumnName();
        Cursor res = writableDatabase.rawQuery("select * from " + tableArrayList.get(tableIndex).getTableName() + " ORDER BY " + colNam + postfix, null);
        return res;
    }

    public Cursor getAllDataOrderedBy(int columnNumber, boolean ascending, String tableName) {
        String postfix = ascending ? "" : " DESC ";
        String colNam = columnNumber == 0 ? " ID " : getColumnNameFromTable(tableName, columnNumber);
        Cursor res = writableDatabase.rawQuery("select * from " + tableName + " ORDER BY " + colNam + postfix, null);
        return res;
    }

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

    public Cursor getOneRowData(String tableName, int rowID) {
        Cursor cursor = writableDatabase.query(tableName.replace(" ", ""),
                getAllColumnsFromTable(tableName), "ID=?",
                new String[]{String.valueOf(rowID)},
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
                contentValues.put(getColumnNameFromTable(tableIndex, data[i].getColumnNumber()), data[i].getValue());
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

    public boolean addDataInTable(String tableName, Datum... data) {
        contentValues = new ContentValues();
        for (int i = 0; i < data.length; i++) {
            if (data[i].getColumnName().isEmpty()) {
                contentValues.put(getColumnNameFromTable(tableName, data[i].getColumnNumber()), data[i].getValue());
            } else {
                contentValues.put(data[i].getColumnName(), data[i].getValue());
            }
        }
        long result = writableDatabase.insert(tableName, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }

    private String getColumnNameFromTable(int tableIndex, int columnNumber) {
        return tableArrayList.get(tableIndex).getColumns()[columnNumber - 1].getColumnName();
    }

    private String getColumnNameFromTable(String tableName, int columnNumber) {
        int index = -1;
        for (int i = 0; i < tableArrayList.size(); i++) {
            if (tableName.toUpperCase().equals(tableArrayList.get(i).getTableName())) {
                index = i;
                break;
            }
        }

        return tableArrayList.get(index).getColumns()[columnNumber - 1].getColumnName();
    }

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
