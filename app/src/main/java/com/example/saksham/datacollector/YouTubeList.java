package com.example.saksham.datacollector;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class YouTubeList extends AppCompatActivity {

    int idCount;
    EditText id, playTime;
    Button add;
    Button play;
    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
    ArrayList<String> a;

    /*//DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
    ArrayAdapter<EditText> adapter;
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_tube_list);
        Log.d("YouTube", "View Set");
        idCount=0;
        id = findViewById(R.id.ID);
        playTime = findViewById(R.id.playbackTime);
        a = new ArrayList<>();

        //adapter=new ArrayAdapter<EditText>(this, android.R.layout.simple_list_item_1, listItems);
        //setListAdapter(adapter);

        /*RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new adapter()); //create and set the adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        */

        add = findViewById(R.id.addID);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.add(id.getText().toString());
                idCount++;
                Toast.makeText(getApplicationContext(), "Added id " + id.getText().toString(), Toast.LENGTH_SHORT).show();
                id.setText("");
                //listItems.add(new EditText(this));
                //adapter.notifyDataSetChanged();
            }
        });

        play = findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int time = parseInt(playTime.getText().toString());
                for(int i=0; i<idCount; i++)
                {
                    watchYoutubeVideo(YouTubeList.this, a.get(i), time);
                }
            }
        });
    }

    public void watchYoutubeVideo(Context context, String id, int time) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("https://www.youtube.com/watch?v=" + id));
        try {
            context.startActivity(appIntent);
            long t1 = System.nanoTime();
            while ((System.nanoTime() - t1) / 1000000000 < time) {
            }
            finish();
        } catch (ActivityNotFoundException ex) {
            context.startActivity(webIntent);
        }
    }

    /*public class adapter extends RecyclerView.Adapter<idViewHolder> {

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
    }*/
}
