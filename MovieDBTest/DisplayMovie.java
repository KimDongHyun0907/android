package com.example.moviedbtest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DisplayMovie extends AppCompatActivity {

    DBHelper dbHelper;
    EditText editname;
    EditText editdirector;
    EditText edityear;
    EditText editrating;
    EditText editnation;

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_movie);

        editname = (EditText) findViewById(R.id.name);
        editdirector = (EditText) findViewById(R.id.director);
        edityear = (EditText) findViewById(R.id.year);
        editrating = (EditText) findViewById(R.id.rating);
        editnation = (EditText) findViewById(R.id.nation);

        dbHelper = new DBHelper(this);
        Intent data = getIntent();
        Bundle bundle = data.getExtras();
        int id = bundle.getInt("id");
        String action = bundle.getString("action");
        if(action.equals("update")){
            Cursor cursor =dbHelper.getData(id);
            cursor.moveToFirst();
            String name = cursor.getString(cursor.getColumnIndex(DBHelper.MOVIE_COLUMN_NAME));
            String director = cursor.getString(cursor.getColumnIndex(DBHelper.MOVIE_COLUMN_DIRECTOR));
            String year = cursor.getString(cursor.getColumnIndex(DBHelper.MOVIE_COLUMN_YEAR));
            String rating = cursor.getString(cursor.getColumnIndex(DBHelper.MOVIE_COLUMN_RATING));
            String nation = cursor.getString(cursor.getColumnIndex(DBHelper.MOVIE_COLUMN_NATION));
            editname.setText(name);
            editdirector.setText(director);
            edityear.setText(year);
            editrating.setText(rating);
            editnation.setText(nation);
        }
    }

    public void buttonSave(View view) {
        String name = editname.getText().toString();
        String director = editdirector.getText().toString();
        String year = edityear.getText().toString();
        String rating = editrating.getText().toString();
        String nation = editnation.getText().toString();
        dbHelper.insertMovies(name, director,year, nation, rating);
        finish();
    }

    public void buttonUpdate(View view) {
        Intent data = getIntent();
        Bundle bundle = data.getExtras();
        int id = bundle.getInt("id");
        String name = editname.getText().toString();
        String director = editdirector.getText().toString();
        String year = edityear.getText().toString();
        String rating = editrating.getText().toString();
        String nation = editnation.getText().toString();
        dbHelper.updateMovies(id, name, director, year, nation, rating);
        finish();
    }

    public void buttonDelete(View view) {
        Intent data = getIntent();
        Bundle bundle = data.getExtras();
        int id = bundle.getInt("id");
        dbHelper.deleteMovie(id);
        finish();
    }
}