package com.pingu.android;

import android.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    CardView DownloadApps,SystemApps,Games;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_home);
        Nav();
    }

    private void Nav()
    {
        DownloadApps=findViewById(R.id.CardDownloadApps);
        SystemApps=findViewById(R.id.CardSystemApp);
        Games=findViewById(R.id.CardGames);

        DownloadApps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nav = new Intent(HomeActivity.this, DownloadAppsRView.class);
                nav.putExtra("ScreenType","Download");
                startActivity(nav);
                finish();
            }
        });

        SystemApps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nav = new Intent(HomeActivity.this, DownloadAppsRView.class);
                nav.putExtra("ScreenType","System");
                startActivity(nav);
                finish();
            }
        });

        Games.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Alert();
            }
        });
    }
    private void Alert()
    {
        //Uncomment the below code to Set the message and title from the strings.xml file
        //builder.setMessage("Feature Comming Soon!-Stay Turned") .setTitle("Information");
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        //AlertDialog alertDialog = builder.create();

        //Setting message manually and performing action on button click
        builder.setMessage("Feature Coming Soon!...")
                .setCancelable(true)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ///finish();
                    }
                });

        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle("Information");
        alert.show();
    }
}