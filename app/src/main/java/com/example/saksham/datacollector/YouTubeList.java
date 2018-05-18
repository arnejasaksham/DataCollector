package com.example.saksham.datacollector;

import android.app.ListActivity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class YouTubeList extends ListActivity {

    int id_count;

    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
    ArrayList<EditText> listItems=new ArrayList<EditText>();

    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
    ArrayAdapter<EditText> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        id_count=0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_tube_list);

        adapter=new ArrayAdapter<EditText>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        setListAdapter(adapter);

        /*RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new adapter()); //create and set the adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        */

        Button add = (Button) findViewById(R.id.addID);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listItems.add(new EditText(YouTubeList.class));
                adapter.notifyDataSetChanged();
            }
        });

        final EditText editText = (EditText) findViewById(R.id.playbackTime);

        final Button play = (Button) findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int time = parseInt(editText.getText().toString());
                String[] arr={"twZggnNbFqo", "Fn57JS9WnAQ", "NNeJR14kgTY"};
                for(String s:arr)
                {
                    watchYoutubeVideo(YouTubeList.this, s, time);
                }
            }
        });
    }

    public void watchYoutubeVideo(Context context, String id, int time) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + id));
        try {
            context.startActivity(appIntent);
            long t1 = 0;
            t1 = System.nanoTime();
            while ((System.nanoTime() - t1) / 1000000000 < time) {
            }
            finish();
        } catch (ActivityNotFoundException ex) {
            context.startActivity(webIntent);
        }
    }


    public class adapter extends RecyclerView.Adapter<idViewHolder> {

        @Override
        public idViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //inflate the view - id_fields.xml
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.id_fields, parent, false);
            return new idViewHolder(view);
        }

        @Override
        public void onBindViewHolder(idViewHolder holder, int position) {
            //setting the data
        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }
}
