package com.quikr.pluto;

import android.provider.ContactsContract;
import android.util.Log;

import com.quikr.pluto.Structure.DataSet;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by kanishth on 13/9/15.
 */
public class JSONExtracter {
    public static DataSet extract(String raw)
    {
        try {
            JSONObject jsonObject = new JSONObject(raw);
            jsonObject = jsonObject.getJSONObject("AdsByCategoryResponse");
            jsonObject = jsonObject.getJSONObject("AdsByCategoryData");
            JSONArray jsonArray = jsonObject.getJSONArray("docs");

            JSONObject j1 = (JSONObject)jsonArray.get(0);

            String t = j1.getString("title");
            String c = j1.getString("content");
            String i;
            Object image = j1.get("images");

            if(image instanceof JSONArray) {
                JSONArray images = (JSONArray)image;
                if (images.length() > 0)
                    i = images.getString(0);
                else
                    i = null;
            }
            else if(image instanceof String)
            {
                i = (String) image;
            }
            else
                i = null;

            return new DataSet(t,c,i);

            //Log.d("**t1**",t1+"\n\n"+c1);
            //Log.d("**t2**",t2+"\n\n"+c2);

        }
        catch(Exception e)
        { Log.d("**plutoEx",e.toString()); }
        
        return null;
    }
}
