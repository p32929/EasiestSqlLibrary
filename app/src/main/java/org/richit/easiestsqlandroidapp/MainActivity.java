package org.richit.easiestsqlandroidapp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.richit.easiestsqllib.Column;
import org.richit.easiestsqllib.Datum;
import org.richit.easiestsqllib.EasiestDB;

public class MainActivity extends AppCompatActivity {

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


        boolean done = easiestDB.addDataInTable(0,
                new Datum(1, "a"),
                new Datum(2, "b")
        );

        Toast.makeText(this, "" + done, Toast.LENGTH_SHORT).show();

    }
}
