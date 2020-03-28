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
    }
}
