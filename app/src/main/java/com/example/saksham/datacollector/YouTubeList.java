package com.example.saksham.datacollector;

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
    ArrayList<String> a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_tube_list);
        Log.d("YouTube", "View Set");
        idCount=0;
        id = findViewById(R.id.y_ID);
        playTime = findViewById(R.id.y_playbackTime);
        a = new ArrayList<>();

        add = findViewById(R.id.y_addID);
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

        play = findViewById(R.id.y_play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = playTime.getText().toString();
                if(!str.equals("") && idCount > 0) {
                    int time = parseInt(str);
                    for (int i = 0; i < idCount; i++) {
                        watchYoutubeVideo(a.get(i), time);
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

    public void watchYoutubeVideo(String id, int time) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=" + id));
        if (appIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(appIntent);
            try {
                Thread.sleep(time * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else {
            if (webIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(webIntent);
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
}
