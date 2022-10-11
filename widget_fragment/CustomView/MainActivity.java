package com.example.adaptertest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ListView list;

    String[][] movieInfos = {
            {"The Wizard Oz","Fantasy","1939","9.5"},
            {"Citizen Kane","Mystery","1941","9.1"},
            {"All About Eve","Drama","1950","9.3"},
            {"The Third Man","Thriller","1949","9.6"},
            {"A Hard day Night","Rock","1964","8.9"},
            {"Modern Times","Comedy","1936","9.2"},
            {"Metropolis","SF","1927","10"}
    };

    Integer[] images = {
            R.drawable.movie1,
            R.drawable.movie2,
            R.drawable.movie3,
            R.drawable.movie4,
            R.drawable.movie5,
            R.drawable.movie6,
            R.drawable.movie7
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomListAdapter adapter = new CustomListAdapter(MainActivity.this,0);
        list=(ListView) findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

            }
        });
    }



    class CustomListAdapter extends ArrayAdapter<String>{
        private final Activity context;

        public CustomListAdapter(Activity context, int resource) {
            super(context, resource);
            this.context = context;
        }

        @Override
        public int getCount() {
            return movieInfos.length;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.item, null, true);
            ImageView image = (ImageView) rowView.findViewById(R.id.image);
            TextView title = (TextView) rowView.findViewById(R.id.title);
            TextView rating = (TextView) rowView.findViewById(R.id.rating);
            TextView genre = (TextView) rowView.findViewById(R.id.genre);
            TextView year = (TextView) rowView.findViewById(R.id.year);

            image.setImageResource(images[position]);
            title.setText(movieInfos[position][0]);
            rating.setText(movieInfos[position][3]);
            genre.setText(movieInfos[position][1]);
            year.setText(movieInfos[position][2]);

            return rowView;
        }
    }
}