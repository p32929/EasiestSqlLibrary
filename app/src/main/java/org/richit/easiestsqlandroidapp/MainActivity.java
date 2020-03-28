package org.richit.easiestsqlandroidapp;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

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
        for (int i = 0; i < 5; i++) {
            boolean done = easiestDB.addDataInTable(0,
                    new Datum(1, "a" + i),
                    new Datum(2, "b" + i)
            );

            Toast.makeText(this, "" + done, Toast.LENGTH_SHORT).show();

            boolean done2 = easiestDB.addDataInTable(1,
                    new Datum(1, "a" + i),
                    new Datum(2, "b" + i)
            );

            Toast.makeText(this, "" + done2, Toast.LENGTH_SHORT).show();
        }


//        Get all data from a table
//        Cursor cursor = easiestDB.getAllDataFrom(0);
//        String data = "";
//        if (cursor != null) {
//            while (cursor.moveToNext()) {
//                data += cursor.getString(0) + " " + cursor.getString(1) + " " + cursor.getString(2) + "\n";
//            }
//        }
//
//        Log.d(TAG, "onCreate: " + data);


//        Get one row data from a table
        Cursor cursor = easiestDB.getOneRowData(0, 2);
        String data = "";
        if (cursor != null) {
            cursor.moveToFirst();
            data += cursor.getString(0) + " " + cursor.getString(1) + " " + cursor.getString(2) + "\n";
        }
        Log.d(TAG, "onCreate: " + data);
    }
}
