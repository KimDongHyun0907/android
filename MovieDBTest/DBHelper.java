package com.example.moviedbtest;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyMovies.db";
    public static final int DATABASE_VERSION = 1;
    public static final String MOVIE_COLUMN_ID = "id";
    public static final String MOVIE_COLUMN_NAME = "name";
    public static final String MOVIE_COLUMN_DIRECTOR = "director";
    public static final String MOVIE_COLUMN_YEAR = "year";
    public static final String MOVIE_COLUMN_NATION = "nation";
    public static final String MOVIE_COLUMN_RATING = "rating";

    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE movies (id integer primary key autoincrement, name text, director text, year text, nation text, rating text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS movies");
        onCreate(sqLiteDatabase);
    }

    public boolean insertMovies(String name, String director, String year, String nation, String rating){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("director",director);
        contentValues.put("year",year);
        contentValues.put("nation",nation);
        contentValues.put("rating",rating);

        db.insert("movies", null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from movies where id=" + id, null);
        return cursor;
    }

    public boolean updateMovies(int id, String name, String director, String year, String nation, String rating){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("director",director);
        contentValues.put("year",year);
        contentValues.put("nation",nation);
        contentValues.put("rating",rating);

        db.update("movies",contentValues, "id = ?",new String[]{Integer.toString(id)});
        return true;
    }

    public Integer deleteMovie(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("movies", "id = ?",new String[]{Integer.toString(id)});
    }

    @SuppressLint("Range")
    public ArrayList getAllMovies() {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from movies", null);
        cursor.moveToFirst();
        while(cursor.isAfterLast()==false){
            arrayList.add(cursor.getString(cursor.getColumnIndex(MOVIE_COLUMN_ID)) + " " +
                    cursor.getString(cursor.getColumnIndex(MOVIE_COLUMN_NAME)));
            cursor.moveToNext();
        }
        return arrayList;
    }
}
