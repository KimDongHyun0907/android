package com.example.movierealtimedb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DisplayMovie extends AppCompatActivity {

    DBHelper dbHelper;
    EditText editname;
    EditText editdirector;
    EditText edityear;
    EditText editrating;
    EditText editnation;

    DatabaseReference firebaseDB;

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

        firebaseDB = FirebaseDatabase.getInstance().getReference().child("movies");

        Intent data = getIntent();
        Bundle bundle = data.getExtras();
        int id = bundle.getInt("id");
        String action = bundle.getString("action");
        if(action.equals("update")){
            DatabaseReference movieElement = firebaseDB.child(Integer.toString(id));
            movieElement.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String name = (String)snapshot.child("name").getValue();
                    String director = (String)snapshot.child("director").getValue();
                    String year = (String)snapshot.child("year").getValue();
                    String rating = (String)snapshot.child("rating").getValue();
                    String nation = (String)snapshot.child("nation").getValue();

                    editname.setText(name);
                    editdirector.setText(director);
                    edityear.setText(year);
                    editrating.setText(rating);
                    editnation.setText(nation);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
    }

    public void buttonSave(View view) {
        Intent data = getIntent();
        Bundle bundle = data.getExtras();
        int id = bundle.getInt("id");
        String name = editname.getText().toString();
        String director = editdirector.getText().toString();
        String year = edityear.getText().toString();
        String rating = editrating.getText().toString();
        String nation = editnation.getText().toString();
        firebaseDB.child(Integer.toString(id)).child("id").setValue(Integer.toString(id));
        firebaseDB.child(Integer.toString(id)).child("name").setValue(name);
        firebaseDB.child(Integer.toString(id)).child("director").setValue(director);
        firebaseDB.child(Integer.toString(id)).child("year").setValue(year);
        firebaseDB.child(Integer.toString(id)).child("rating").setValue(rating);
        firebaseDB.child(Integer.toString(id)).child("nation").setValue(nation);
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
        firebaseDB.child(Integer.toString(id)).child("id").setValue(Integer.toString(id));
        firebaseDB.child(Integer.toString(id)).child("name").setValue(name);
        firebaseDB.child(Integer.toString(id)).child("director").setValue(director);
        firebaseDB.child(Integer.toString(id)).child("year").setValue(year);
        firebaseDB.child(Integer.toString(id)).child("rating").setValue(rating);
        firebaseDB.child(Integer.toString(id)).child("nation").setValue(nation);
        finish();
    }

    public void buttonDelete(View view) {
        Intent data = getIntent();
        Bundle bundle = data.getExtras();
        int id = bundle.getInt("id");
        DatabaseReference deleteData=firebaseDB.child(Integer.toString(id));
        deleteData.removeValue();
        finish();
    }
}