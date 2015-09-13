package com.quikr.pluto;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.quikr.pluto.Adapter.AdAdapter;
import com.quikr.pluto.Structure.DataCollection;
import com.quikr.pluto.Structure.DataSet;

import java.io.InputStream;

public class Suggestions extends ActionBarActivity {

    AdAdapter adapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestions);

        listView = (ListView) findViewById(R.id.listView);

        adapter = new AdAdapter(DataCollection.dataCollection, getApplicationContext());
        listView.setAdapter(adapter);


        for(int i=0;i<DataCollection.dataCollection.size();i++)
            if(DataCollection.dataCollection.get(i).image!=null
                    && !DataCollection.dataCollection.get(i).image.equalsIgnoreCase(""))
                new DownloadImageTask(i).execute(DataCollection.dataCollection.get(i).image);

        listView.setOnItemClickListener(new ListClickListener());
    }
    /**
     * item click listener
     * */
    private class ListClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            // display activity for selected option item
            ToastMessage(position);
        }
    }

    private void ToastMessage(int position)
    {
        String tag = DataCollection.dataCollection.get(position).tag;
        Toast.makeText(Suggestions.this, "This suggestion cause you have installed the app ->\n"+tag, Toast.LENGTH_LONG).show();
    }


    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        int position;
        DownloadImageTask(int position)
        {this.position = position;}

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result)
        {
            DataCollection.dataCollection.get(position).bitmap = result;
            DataCollection.dataCollection.get(position).hasImage = true;
            refresh();
        }
    }
    private void refresh()
    {
        adapter = new AdAdapter(DataCollection.dataCollection, getApplicationContext());
        listView.setAdapter(adapter);
    }
}
