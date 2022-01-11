package com.pingu.android;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class ConnectScreen extends AppCompatActivity {

    String SetScreen;
    String AppName;
    TextView AppNameText;
    ImageView AppIconView;
    Button btnConnect;
    List<NetworkInfo> listNI=null;
    List<List<InetAddress>> listDns=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_connect_screen);

        AppNameText=findViewById(R.id.txtAppName);
        AppIconView=findViewById(R.id.imageViewAppIcon);
        btnConnect=findViewById(R.id.btnConnect);


        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getInternetPermisson(getApplicationContext());

                System.out.println(listNI);
            }


        });

        Bundle bundle = getIntent().getExtras();
        AppName=bundle.getString("AppName");

        AppNameText.setText(AppName);

        final PackageManager pm = getPackageManager();
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo applicationInfo : packages)
        {
            if(AppName.equals(applicationInfo.loadLabel(pm)))
            {
                AppIconView.setImageDrawable(applicationInfo.loadIcon(pm));
                if((applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM)!=0)
                {
                    SetScreen="System";
                }
                else
                {
                    SetScreen="Download";
                }
            }
        }

    }

    private static native List<InetAddress> jni_getprop(String name);

    private List<List<InetAddress>> getInternetPermisson(Context context) {

        /*ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ani = cm.getActiveNetworkInfo();

        listNI = new ArrayList<>();

        if(!ani.equals(null))
        {
            listNI.add(ani);
        }
        else
        {

        }*/

        //return listNI;

        listDns = new ArrayList<List<InetAddress>>();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            Network an = cm.getActiveNetwork();
            if (an != null)
            {
                LinkProperties lp = cm.getLinkProperties(an);
                if (lp != null)
                {
                    List<InetAddress> dns = lp.getDnsServers();
                    SharedPreferences dns3=context.getSharedPreferences("dns",context.MODE_PRIVATE);
                    dns3.edit().putString("dns","1.1.1.1").apply();
                    listDns.add(dns);
                }
            }
        }
        else
            {
            List<InetAddress> dns1 = jni_getprop("net.dns1");
            List<InetAddress> dns2 = jni_getprop("net.dns2");

            listDns.add(dns1);
            listDns.add(dns2);

        }


        return listDns;



    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:

                Intent nav = new Intent(getApplicationContext(), DownloadAppsRView.class);
                nav.putExtra("ScreenType",SetScreen);
                startActivity(nav);
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }



}