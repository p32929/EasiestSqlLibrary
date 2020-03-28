package org.richit.easiestsqlandroidapp;

import android.database.Cursor;
import android.os.Bundle;

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

        // Init
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

        // Add
        boolean added = easiestDB.addDataInTable(0,
                new Datum(1, "Value1"),
                new Datum(2, "Value2")
        );

        // Get all
        Cursor cursor = easiestDB.getAllDataFrom(0);
        if (cursor != null) {
            while (cursor.moveToNext()) {
//                int value1 = cursor.getInt(columnNumber);
//                String value2 = cursor.getString(columnNumber);
//                double value3 = cursor.getDouble(columnNumber);

                String id = cursor.getString(0);

            }
        }

        boolean deleted = easiestDB.deleteDatabase();

    }
}
