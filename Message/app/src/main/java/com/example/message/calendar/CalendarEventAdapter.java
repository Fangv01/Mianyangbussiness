package com.example.message.calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.message.R;

import java.util.List;

public class CalendarEventAdapter extends BaseAdapter{
    private Context context;
    private List<CalendarEvent> data;
    private LayoutInflater layoutInflater;

    public CalendarEventAdapter(Context context, List<CalendarEvent> data){
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if( convertView == null){
            viewHolder = new ViewHolder ();
            convertView = LayoutInflater.from(context).inflate(R.layout.calendar_event_item,parent,false);
            viewHolder.content = convertView.findViewById(R.id.event_content);
            viewHolder.images = convertView.findViewById(R.id.event_dot);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.content.setText(data.get(position).getContent());
        viewHolder.images.setImageResource(data.get(position).getImageId());

        return convertView;
    }

    public class ViewHolder{
        private ImageView images;
        private TextView content;
    }
}
