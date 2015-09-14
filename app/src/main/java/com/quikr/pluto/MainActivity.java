package com.quikr.pluto;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.quikr.pluto.Structure.DataCollection;
import com.quikr.pluto.Structure.DataSet;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    static List<DataSet> dataCollecion = new ArrayList<DataSet>();

    List<String> appList = new ArrayList<>();
    List<String> packageList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final PackageManager pm = getPackageManager();
        //get a list of installed apps.
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);


        for (ApplicationInfo packageInfo : packages)
        {
            appList.add(packageInfo.loadLabel(pm).toString().toLowerCase());
            packageList.add(packageInfo.packageName);
            ///Log.d("**pluto**", packageInfo.loadLabel(pm).toString().toLowerCase());
        }

        if(isNetworkAvailable(getApplicationContext()))
            getTags();

        DataCollection.dataCollection = dataCollecion;

        Intent intent = new Intent(MainActivity.this, Suggestions.class);
        startActivity(intent);
        finish();


    }
    private void getTags()
    {
        String keyList[] = {"guitar","keyboard","drum",
                "run","fit" ,
                "photoshop",
                "job","naukri",
                "cars",
                "99acers","housing","common floor",
                "pet",
                "vtu","coursera",
                "verge"};
        int contentId[] = {148,148,148, //musical instruments
                53,53, //fitness
                201, //camera
                272,272, //jobs
                71, //cars
                32,32,32, //housing
                152,   //pet care
                255,255, //study materials
                147}; //tech

        int keyCount[] = new int[keyList.length];
        String tagList[] = new String[keyList.length];
        for(int kC: keyCount) { kC = 0; }
        for(int i=0;i<tagList.length;i++) { tagList[i] = ""; }

        for(int i=0; i<keyList.length; i++)
            for(String appName: appList)
                if(appName.contains(keyList[i]))
                {
                    keyCount[i]++;
                    tagList[i] += appName+"\n";
                }

        for(int i =0; i<contentId.length;i++)
            for(int j =i+1; j<contentId.length;j++)
                if(contentId[i]==contentId[j] && keyCount[j]!=0)
                {
                    keyCount[i] += keyCount[j];
                    keyCount[j]=0;
                    tagList[j] += "\n"+tagList[i];
                }

        for(int i=0;i<keyCount.length;i++)
        if(keyCount[i]>0)
        {
            String params[] = {contentId[i]+"","0","1"};
            DataSet dataSet = GetAd.getAdsByCategory(params);
            dataSet.tag = tagList[i];
            addToDataSet(dataSet);

            String param2[] = {contentId[i]+"","1","2"};
            dataSet = GetAd.getAdsByCategory(param2);
            dataSet.tag = tagList[i];
            addToDataSet(dataSet);
        }
    }
    private static boolean isNetworkAvailable(Context act) {

        //Check for internet

        ConnectivityManager connectivityManager = (ConnectivityManager) act.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    private static void addToDataSet(DataSet ds)
    {
        dataCollecion.add(ds);
    }
}