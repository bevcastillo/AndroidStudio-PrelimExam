package com.example.beverly.prelim;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    Context context;
    //data container
    ArrayList<Prelim> list;
    LayoutInflater inflater;

    public CustomAdapter(Context context, ArrayList<Prelim> list) {
        super();
        this.context = context;
        this.list = list;
        this.inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        PrelimHandler handler=null;
        if(convertView==null){
            convertView=inflater.inflate(R.layout.custom_layout,null);
            handler=new PrelimHandler();
            handler.name= (TextView) convertView.findViewById(R.id.textView1);
            handler.course= (TextView) convertView.findViewById(R.id.textView2);
            convertView.setTag(handler);
        }
        else handler=(PrelimHandler) convertView.getTag();
        //fill the view elements
        handler.name.setText(list.get(position).getName());
        handler.course.setText(list.get(position).getCourse());


        return convertView;
    }

    static class PrelimHandler{
        TextView name, course;
    }
}
