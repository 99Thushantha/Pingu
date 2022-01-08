package com.pingu.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
//import android.support.v7.widget.SearchView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class DownloadAppsRView extends AppCompatActivity {

    RecyclerView DownloadARV;
    List<String> titles;
    List<Integer> images;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_apps_rview);
        DownloadARV=findViewById(R.id.DownloadARV);

        titles = new ArrayList<>();
        images=new ArrayList<>();

        titles.add("T1");
        titles.add("T2");
        titles.add("T3");
        titles.add("T4");
        titles.add("T1");
        titles.add("T2");
        titles.add("T3");
        titles.add("T4");
        titles.add("T1");
        titles.add("T2");
        titles.add("T3");
        titles.add("T4");
        titles.add("T1");
        titles.add("T2");
        titles.add("T3");
        titles.add("T4");
        titles.add("T1");
        titles.add("T2");
        titles.add("T3");
        titles.add("T4");
        titles.add("T1");
        titles.add("T2");
        titles.add("T3");
        titles.add("T4");

        images.add(R.drawable.ic_baseline_emoji_emotions_24);
        images.add(R.drawable.ic_baseline_emoji_emotions_24);
        images.add(R.drawable.ic_baseline_emoji_emotions_24);
        images.add(R.drawable.ic_baseline_emoji_emotions_24);
        images.add(R.drawable.ic_baseline_emoji_emotions_24);
        images.add(R.drawable.ic_baseline_emoji_emotions_24);
        images.add(R.drawable.ic_baseline_emoji_emotions_24);
        images.add(R.drawable.ic_baseline_emoji_emotions_24);
        images.add(R.drawable.ic_baseline_emoji_emotions_24);
        images.add(R.drawable.ic_baseline_emoji_emotions_24);
        images.add(R.drawable.ic_baseline_emoji_emotions_24);
        images.add(R.drawable.ic_baseline_emoji_emotions_24);
        images.add(R.drawable.ic_baseline_emoji_emotions_24);
        images.add(R.drawable.ic_baseline_emoji_emotions_24);
        images.add(R.drawable.ic_baseline_emoji_emotions_24);
        images.add(R.drawable.ic_baseline_emoji_emotions_24);
        images.add(R.drawable.ic_baseline_emoji_emotions_24);
        images.add(R.drawable.ic_baseline_emoji_emotions_24);
        images.add(R.drawable.ic_baseline_emoji_emotions_24);
        images.add(R.drawable.ic_baseline_emoji_emotions_24);
        images.add(R.drawable.ic_baseline_emoji_emotions_24);
        images.add(R.drawable.ic_baseline_emoji_emotions_24);
        images.add(R.drawable.ic_baseline_emoji_emotions_24);
        images.add(R.drawable.ic_baseline_emoji_emotions_24);

        adapter= new Adapter(this,titles,images);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        DownloadARV.setLayoutManager(gridLayoutManager);
        DownloadARV.setAdapter(adapter);
    }
}