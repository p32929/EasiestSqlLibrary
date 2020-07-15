package org.richit.easiestsqlandroidapp;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import org.richit.easiestsqllib.Column;
import org.richit.easiestsqllib.Datum;
import org.richit.easiestsqllib.EasiestDB;

public class MainActivity extends AppCompatActivity {

    private String TAG = this.getClass().getSimpleName();

    EasiestDB easiestDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: ");

        easiestDB = EasiestDB.init(this)
                .addTableColumns("table 1",
                        new Column("Column a1", "text"),
                        new Column("Column a2", "text")
                )
                .addTableColumns("table 2",
                        new Column("Column b1", "text"),
                        new Column("Column b2", "text", "unique")
                )
                .doneAddingTables();
        Log.d(TAG, "init: ");
    }

    public void add(View view) {
        boolean added = easiestDB.addDataInTable(0,
                new Datum(1, "Value1"),
                new Datum(2, "Value2")
        );
        Log.d(TAG, "add: " + added);
    }

    public void get(View view) {
        Cursor cursor = easiestDB.getAllDataFrom(0);
        if (cursor != null) {
            while (cursor.moveToNext()) {

//                int value1 = cursor.getInt(columnNumber);
//                String value2 = cursor.getString(columnNumber);
//                double value3 = cursor.getDouble(columnNumber);

                String id = cursor.getString(0);
                Log.d(TAG, "get: " + id);
            }
        }
    }

    int count = 1;

    public void update(View view) {
        boolean updated = easiestDB.updateData(0, count,
                new Datum(1, "NValue" + count),
                new Datum(2, "NValue" + count)
        );
        count++;
        Log.d(TAG, "update: " + updated);
    }

    public void delete(View view) {
        boolean deleted = easiestDB.deleteRow(0, 1);
        Log.d(TAG, "delete: " + deleted);
    }

    public void deleteAll(View view) {
        boolean deleted = easiestDB.deleteDatabase();
        Log.d(TAG, "deleteAll: " + deleted);
    }
}
