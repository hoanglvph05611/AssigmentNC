package com.hoang.lvhco.assigmentnc.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.hoang.lvhco.assigmentnc.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends ArrayAdapter<ReadNews> {

    public Adapter(Context context, int resource, List<ReadNews> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view =  inflater.inflate(R.layout.item_readnews, null);
        }
        ReadNews p = getItem(position);
        if (p != null) {
            // Anh xa + Gan gia tri
            ImageView img = view.findViewById(R.id.imgView);
           //của t cái nớ hắn khác, rauws đúng r đó
            Picasso.with(getContext()).load(p.img).into(img);
            TextView tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            tvTitle.setText(p.title);


        }
        return view;
    }

}
