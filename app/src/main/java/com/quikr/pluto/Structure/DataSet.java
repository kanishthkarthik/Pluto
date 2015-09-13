package com.quikr.pluto.Structure;

import android.graphics.Bitmap;

/**
 * Created by kanishth on 14/9/15.
 */
public class DataSet {
    public String title;
    public String comment;
    public String image;
    public boolean hasImage = false;
    public Bitmap bitmap;
    public String tag;

    public DataSet(String t,String c , String i)
    {
        title = t;
        comment = c;
        image = i;
    }
}
