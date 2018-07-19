package com.example.setareh.musiclibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class First_Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first__menu);

        ImageView macan=(ImageView)findViewById(R.id.ivMacan);
        macan.setOnClickListener(new OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                         startActivity(intent);
                                     }
                                 }

        );


        ImageView farzin=(ImageView)findViewById(R.id.ivFarzin);
        farzin.setOnClickListener(new OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         Intent intent=new Intent(getApplicationContext(),Farzin.class);
                                         startActivity(intent);
                                     }
                                 }

        );
    }
}
