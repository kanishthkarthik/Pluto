package com.quikr.pluto.Adapter;

/**
 * Created by kanishth on 14/9/15.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.quikr.pluto.R;
import com.quikr.pluto.Structure.DataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kanishth Karthik on 05/05/15.
 */
public class AdAdapter extends ArrayAdapter<DataSet> {

    private List<DataSet> list = new ArrayList<>();
    private Context context;

    public AdAdapter(List<DataSet> list, Context cxt) {
        super(cxt, 0, list);
        this.list = list;
        this.context = cxt;
    }

    @Override
    //This is called every time the list is scrolled.
    public View getView(final int position, View view, ViewGroup parent) {

        // This a new view we inflate the new layout
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.template_ad, null, true);

        //Name
        TextView tv = (TextView)view.findViewById(R.id.textView1);
        tv.setText(list.get(position).title);
        //Number
        tv = (TextView) view.findViewById(R.id.textView2);
        //tv.setText(list.get(position).comment);

        if(list.get(position).hasImage)
        {
            ImageView imageView = (ImageView) view.findViewById(R.id.imageView1);
            imageView.setImageBitmap(list.get(position).bitmap);
        }

        return view;
    }
}

