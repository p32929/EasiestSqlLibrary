package org.richit.easiestsqlandroidapp;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import org.richit.easiestsqllib.Column;
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
                        new Column("Ca1", "text"),
                        new Column("Ca2", "text", "unique")
                )
                .addTableColumns("table 2",
                        new Column("Cb1", "text"),
                        new Column("Cb2", "text", "unique")
                )
                .doneAddingTables();

// Add
//        boolean done = easiestDB.addDataInTable(0,
//                new Datum(1, "a"),
//                new Datum(2, "b")
//        );
//
//        Toast.makeText(this, "" + done, Toast.LENGTH_SHORT).show();
//
//        boolean done2 = easiestDB.addDataInTable(1,
//                new Datum(1, "a"),
//                new Datum(2, "b")
//        );
//
//        Toast.makeText(this, "" + done2, Toast.LENGTH_SHORT).show();


//        Get
//        Cursor cursor = easiestDB.getAllDataFrom(0);
//        String data = "";
//        if (cursor != null) {
//            while (cursor.moveToNext()) {
//                data += cursor.getString(0) + " " + cursor.getString(1) + " " + cursor.getString(2) + "\n";
//            }
//        }
//
//        Log.d(TAG, "onCreate: " + data);



    }
}
