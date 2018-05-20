package com.example.saksham.datacollector;

//import android.content.Context;
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

public class TedList extends AppCompatActivity {
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
        setContentView(R.layout.activity_ted_list);
        Log.d("Ted", "View Set");
        idCount=0;
        id = findViewById(R.id.t_ID);
        playTime = findViewById(R.id.t_playbackTime);
        a = new ArrayList<>();

        //adapter=new ArrayAdapter<EditText>(this, android.R.layout.simple_list_item_1, listItems);
        //setListAdapter(adapter);

        /*RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setAdapter(new adapter()); //create and set the adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        */

        add = findViewById(R.id.t_addID);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = id.getText().toString();
                if(!str.equals("")) {
                    a.add(str);
                    idCount++;
                    Toast.makeText(getApplicationContext(), "Added id " + str, Toast.LENGTH_SHORT).show();
                    id.setText("");
                }
                else {
                    Toast.makeText(getApplicationContext(), "ID can't be blank.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        play = findViewById(R.id.t_play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = playTime.getText().toString();
                if(!str.equals("") && idCount > 0) {
                    int time = parseInt(str);
                    for(int i=0; i<idCount; i++)
                    {
                        watchTedVideo(a.get(i), time);
                    }
                }
                else {
                    if(str.equals(""))
                        Toast.makeText(getApplicationContext(), "Please Enter Playback time.", Toast.LENGTH_SHORT).show();
                    if(idCount == 0)
                        Toast.makeText(getApplicationContext(), "Please enter at least one id.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void watchTedVideo(String id, int time) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://go.ted.com/" + id));
        if (appIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(appIntent);
            try {
                Thread.sleep(time * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else {
            Toast.makeText(this,"No app found to perform this action!",Toast.LENGTH_SHORT).show();
        }
    }
}
