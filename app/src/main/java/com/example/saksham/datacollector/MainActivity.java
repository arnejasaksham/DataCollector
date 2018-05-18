package com.example.saksham.datacollector;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton y=(ImageButton)findViewById(R.id.YouTube);
        y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,YouTubeList.class);
                startActivity(i);
            }
        });
        ImageButton n=(ImageButton)findViewById(R.id.Netflix);
        n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,NetflixList.class);
                startActivity(i);
            }
        });
        ImageButton h=(ImageButton)findViewById(R.id.Hotstar);
        h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,HotstarList.class);
                startActivity(i);
            }
        });
        ImageButton b=(ImageButton)findViewById(R.id.blah);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,YouTubeList.class);
                startActivity(i);
            }
        });
    }
}
