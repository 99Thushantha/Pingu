package com.pingu.android;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> implements Filterable {

    private List<ExampleItem> exampleList;
    private List<ExampleItem> exampleListFull;

    List<String> titles;
    List<Integer> images;
    LayoutInflater inflater;

    Adapter(List<ExampleItem> exampleList) {
        this.exampleList = exampleList;
        exampleListFull = new ArrayList<>(exampleList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int orientation;
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_custom_gride_layout,
                parent, false);
        /*Context ctx= parent.getContext();
        orientation = ctx.getResources().getConfiguration().orientation;
        if(orientation== Configuration.ORIENTATION_PORTRAIT)
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ui_app_list,
                    parent, false);
        }
        else
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_custom_gride_layout,
                    parent, false);
        }*/
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ExampleItem currentItem = exampleList.get(position);
        holder.title.setText(currentItem.getTitle());
        holder.gridIcon.setImageResource(currentItem.getGrideIcon());
    }

    @Override
    public int getItemCount() {
        return exampleList.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ExampleItem> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(exampleListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (ExampleItem item : exampleListFull) {
                    if (item.getTitle().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            exampleList.clear();
            exampleList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

        class ViewHolder extends RecyclerView.ViewHolder
        {
            TextView title;
            ImageView gridIcon;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                title=itemView.findViewById(R.id.txtAppName);
                gridIcon=itemView.findViewById(R.id.imageViewAppIcon);
            }
        }


}
