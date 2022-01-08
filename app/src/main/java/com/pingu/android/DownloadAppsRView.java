package com.pingu.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
//import android.support.v7.widget.SearchView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

public class DownloadAppsRView extends AppCompatActivity {

    private Adapter adapter;
    private List<ExampleItem> exampleList;
    String data=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        data =extras.getString("ScreenType");
        if(data.equals("Download"))
        {
            requestWindowFeature(Window.FEATURE_ACTION_BAR);
            getSupportActionBar().setTitle("Download Apps");
        }
        else if(data.equals("System"))
        {
            requestWindowFeature(Window.FEATURE_ACTION_BAR);
            getSupportActionBar().setTitle("System Apps");
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_download_apps_rview);

        fillExampleList();
        setUpRecyclerView();


    }

    private void fillExampleList() {
        exampleList = new ArrayList<>();
        exampleList.add(new ExampleItem(R.drawable.ic_baseline_emoji_emotions_24, "One"));
        exampleList.add(new ExampleItem(R.drawable.ic_baseline_emoji_emotions_24, "Two"));
        exampleList.add(new ExampleItem(R.drawable.ic_baseline_emoji_emotions_24, "Three"));
        exampleList.add(new ExampleItem(R.drawable.ic_baseline_emoji_emotions_24, "Four"));
        exampleList.add(new ExampleItem(R.drawable.ic_baseline_emoji_emotions_24, "One"));
        exampleList.add(new ExampleItem(R.drawable.ic_baseline_emoji_emotions_24, "Two"));
        exampleList.add(new ExampleItem(R.drawable.ic_baseline_emoji_emotions_24, "Three"));
        exampleList.add(new ExampleItem(R.drawable.ic_baseline_emoji_emotions_24, "Four"));
    }

    private void setUpRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.DownloadARV);
        recyclerView.setHasFixedSize(true);
        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        int orientation, grideCol;
        orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            grideCol=2;
        } else {
            grideCol=4;
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,grideCol,GridLayoutManager.VERTICAL,false);
        adapter = new Adapter(exampleList);

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.filter_download_apps, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                Intent intent = new Intent(DownloadAppsRView.this, HomeActivity.class);
                startActivity(intent);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}