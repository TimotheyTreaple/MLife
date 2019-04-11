package com.example.master.mlife.Fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.master.mlife.R;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private ArrayList titleList = arrayList;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;
        public MyViewHolder(TextView v) {
            super(v);
            textView = v;
        }
    }

    public void setItems(Collection<Tweet> tweets) {
        titleList.addAll(tweets);
        notifyDataSetChanged();
    }

    public void clearItems() {
        titleList.clear();
        notifyDataSetChanged();
    }
}
