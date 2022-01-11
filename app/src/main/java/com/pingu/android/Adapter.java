package com.pingu.android;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> implements Filterable {

    private List<ApplicationInfo> applist=null;
    private List<ApplicationInfo> exampleListFull;
    private Context context;
    private PackageManager packageManager;


    Adapter(Context context,int textViewResourceId,List<ApplicationInfo> applist) {
        this.context = context;
        this.applist = applist;
        exampleListFull = new ArrayList<>(applist);
        packageManager = context.getPackageManager();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_custom_gride_layout,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final ApplicationInfo applicationInfo = applist.get(position);
        holder.getIconView().setImageDrawable(applicationInfo.loadIcon(packageManager));
        holder.getAppName().setText(applicationInfo.loadLabel(packageManager));
        holder.getIconView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent nav = new Intent(view.getContext(), ConnectScreen.class);
                nav.putExtra("AppName",holder.getAppName().getText());
                holder.getIconView().getContext().startActivity(nav);

            }
        });
    }

    @Override
    public int getItemCount() {
        return applist.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ApplicationInfo> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(exampleListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (ApplicationInfo item : exampleListFull) {
                    if (item.loadLabel(packageManager).toString().toLowerCase().contains(filterPattern)) {
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
            applist.clear();
            applist.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

        class ViewHolder extends RecyclerView.ViewHolder
        {
            private final TextView appName;
            private final ImageView iconView;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                iconView = itemView.findViewById(R.id.imageViewAppIcon);
                appName = itemView.findViewById(R.id.txtAppName);

            }
            public TextView getAppName() {
                return appName;
            }

            public ImageView getIconView() {
                return iconView;
            }
        }


}
