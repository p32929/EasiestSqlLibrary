package org.richit.easiestsqllib;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class EasiestDB extends SQLiteOpenHelper {

    private static String DATABASE_NAME = "DEMO_DATABASE.db";
    private ArrayList<Table> tableArrayList = new ArrayList<>();

    private SQLiteDatabase writableDatabase;
    private ContentValues contentValues = new ContentValues();
    private boolean initedDb = false;

    //
    public EasiestDB addTableColumns(String tableName, Column... columns) {

        Table table = new Table(
                tableName.replace(" ", "").toUpperCase(),
                columns
        );

        String SQL = " CREATE TABLE " + table.getTableName() + " ( ID INTEGER PRIMARY KEY AUTOINCREMENT, ";
        for (int i = 0; i < table.getColumns().length; i++) {
            SQL += " " + columns[i].columnName + " " + columns[i].columnDataType + " ";
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
        if (!initedDb || writableDatabase == null) initDatabase();
        return this;
    }

    private void initDatabase() {
        writableDatabase = getWritableDatabase();
        initedDb = true;
    }

    //
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

    //
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
