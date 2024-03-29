package com.pingu.android;

import androidx.annotation.WorkerThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
//import android.support.v7.widget.SearchView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DownloadAppsRView extends AppCompatActivity {

    private Adapter adapter;
    private List<ApplicationInfo> applist=null;
    String data=null;
    RecyclerView DownloadARV;
    TextView AppName;
    private PackageManager packageManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        if(extras.getString("ScreenType") != "")
        {
            data =extras.getString("ScreenType");
        }
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

        //CardApp=findViewById(R.id.CrdApp);
        AppName=findViewById(R.id.txtAppName);

        DownloadARV=findViewById(R.id.DownloadARV);
        packageManager = getPackageManager();
        new LoadApplications().execute();

        setUpRecyclerView();
        getSelectedApp();
        //fillExampleList();
        //setUpRecyclerView();

    }

    private void getSelectedApp()
    {
        /*CardApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nav = new Intent(getApplicationContext(), ConnectScreen.class);
                startActivity(nav);
                finish();
            }
        });*/


    }


   /* private void fillExampleList() {
        exampleList = new ArrayList<>();
        exampleList.add(new ExampleItem(R.drawable.ic_baseline_emoji_emotions_24, "One"));
        exampleList.add(new ExampleItem(R.drawable.ic_baseline_emoji_emotions_24, "Two"));
        exampleList.add(new ExampleItem(R.drawable.ic_baseline_emoji_emotions_24, "Three"));
        exampleList.add(new ExampleItem(R.drawable.ic_baseline_emoji_emotions_24, "Four"));
        exampleList.add(new ExampleItem(R.drawable.ic_baseline_emoji_emotions_24, "One"));
        exampleList.add(new ExampleItem(R.drawable.ic_baseline_emoji_emotions_24, "Two"));
        exampleList.add(new ExampleItem(R.drawable.ic_baseline_emoji_emotions_24, "Three"));
        exampleList.add(new ExampleItem(R.drawable.ic_baseline_emoji_emotions_24, "Four"));
        exampleList.add(new ExampleItem(R.drawable.ic_baseline_emoji_emotions_24, "One"));
        exampleList.add(new ExampleItem(R.drawable.ic_baseline_emoji_emotions_24, "Two"));
        exampleList.add(new ExampleItem(R.drawable.ic_baseline_emoji_emotions_24, "Three"));
        exampleList.add(new ExampleItem(R.drawable.ic_baseline_emoji_emotions_24, "Four"));
        exampleList.add(new ExampleItem(R.drawable.ic_baseline_emoji_emotions_24, "One"));
        exampleList.add(new ExampleItem(R.drawable.ic_baseline_emoji_emotions_24, "Two"));
        exampleList.add(new ExampleItem(R.drawable.ic_baseline_emoji_emotions_24, "Three"));
        exampleList.add(new ExampleItem(R.drawable.ic_baseline_emoji_emotions_24, "Four"));
    }*/

    class LoadApplications extends AsyncTask<Void, Void, Void> {

        private ProgressDialog progress = null;

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(DownloadAppsRView.this, null, "Loading applications...");
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {

            runOnUiThread(new Runnable() {
                public void run() {
                    applist = checkForLaunchIntent(packageManager.getInstalledApplications(PackageManager.GET_META_DATA));
                    adapter = new Adapter(DownloadAppsRView.this, R.layout.activity_custom_gride_layout, applist);
                }
            });
            return null;
        }

        private List<ApplicationInfo> checkForLaunchIntent(List<ApplicationInfo> list) {
            ArrayList<ApplicationInfo> applist = new ArrayList<ApplicationInfo>();
            for (ApplicationInfo info : list) {
                try {
                    /*if (null != packageManager.getLaunchIntentForPackage(info.packageName)) {
                        applist.add(info)
                        ;
                    }*/
                    if((info.flags & ApplicationInfo.FLAG_SYSTEM) == 0 && data.equals("Download")) {
                        applist.add(info);
                        //it's a system app, not interested
                    } else if ((info.flags & ApplicationInfo.FLAG_SYSTEM) != 0 && data.equals("System")) {
                        applist.add(info);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return applist;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            DownloadARV.setAdapter(adapter);
            progress.dismiss();
            super.onPostExecute(aVoid);
        }
    }

    private void setUpRecyclerView() {

        //DownloadARV.setHasFixedSize(true);
        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        int orientation, grideCol=0; Context ctx=getApplicationContext();
        orientation = ctx.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            grideCol=3;
        } else {
            grideCol=5;
        }
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,grideCol,GridLayoutManager.VERTICAL,false);
        //adapter = new Adapter(DownloadAppsRView.this, R.layout.activity_custom_gride_layout, applist);
        DownloadARV.setLayoutManager(gridLayoutManager);
        DownloadARV.setAdapter(adapter);
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