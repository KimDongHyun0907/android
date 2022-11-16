package com.example.databasetest;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    DBHelper dbHelper;
    SQLiteDatabase db;

    EditText editname;
    EditText edittel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper=new DBHelper(this);

        db = dbHelper.getWritableDatabase();

        editname = (EditText) findViewById(R.id.editName);
        edittel=(EditText) findViewById(R.id.editTel);
    }

    public void buttonAdd(View view) {
        String name = editname.getText().toString();
        String tel = edittel.getText().toString();
        db.execSQL("INSERT INTO contacts VALUES(null,'" + name +"', '" + tel + "');");
    }

    public void buttonQuery(View view) {
        String name=editname.getText().toString();
        Cursor cursor = db.rawQuery("SELECT name, tel FROM contacts WHERE name='" + name + "';",null);
        while(cursor.moveToNext()){
            String tel = cursor.getString(1);
            edittel.setText(tel);
        }
    }
}