package com.example.adaptertest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView list;

    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    ArrayList<RecyclerViewItem> mList;

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
        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        mList=new ArrayList<>();

        adapter=new RecyclerViewAdapter(mList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        for(int i=0; i<images.length;i++){
            Drawable mImageDrawable = ResourcesCompat.getDrawable(getResources(),images[i],null);
            addItem(mImageDrawable,movieInfos[i][0],movieInfos[i][1],movieInfos[i][2],movieInfos[i][3]);
        }

    }
    void addItem(Drawable icon, String title, String rating, String genre, String year){
        RecyclerViewItem item=new RecyclerViewItem();
        item.setIconDrawable(icon);
        item.setTitle(title);
        item.setRating(rating);
        item.setGenre(genre);
        item.setYear(year);
        mList.add(item);
    }

    class RecyclerViewItem{
        private Drawable iconDrawable;
        private String title;
        private String rating;
        private String genre;
        private String year;

        void setIconDrawable(Drawable icon) {iconDrawable=icon;}
        void setTitle(String title) {this.title=title;}
        void setRating(String rating) {this.rating=rating;}
        void setGenre(String genre) {this.genre=genre;}
        void setYear(String year) {this.year=year;}

        Drawable getIconDrawable() {return iconDrawable;}
        String getTitle() {return title;}
        String getRating() {return rating;}
        String getGenre() {return genre;}
        String getYear() {return year;}
    }

    class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
        private ArrayList<RecyclerViewItem> mData = null;
        public RecyclerViewAdapter(ArrayList<RecyclerViewItem> data){
            mData=data;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            RecyclerViewItem item=mData.get(position);
            holder.imageView.setBackground(item.getIconDrawable());
            holder.title.setText(item.getTitle());
            holder.rating.setText(item.getRating());
            holder.genre.setText(item.getGenre());
            holder.year.setText(item.getYear());
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            private ImageView imageView;
            private TextView title;
            private TextView rating;
            private TextView genre;
            private TextView year;
            public ViewHolder(View itemView){
                super(itemView);
                imageView=(ImageView) itemView.findViewById(R.id.image);
                title=(TextView) itemView.findViewById(R.id.title);
                rating=(TextView) itemView.findViewById(R.id.rating);
                genre=(TextView) itemView.findViewById(R.id.genre);
                year=(TextView) itemView.findViewById(R.id.year);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(),"posigion: "+getAdapterPosition(),Toast.LENGTH_LONG).show();
            }


        }
    }
}