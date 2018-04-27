package com.example.admin.walmartapi.view.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.walmartapi.R;
import com.example.admin.walmartapi.view.model.Item;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    List<Item> items;

    public RecyclerViewAdapter(List<Item> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = items.get(position);

        // bind the views with data
        if (item != null) {
            holder.tvName.setText(item.getName());
            holder.tvSalesPrice.setText(
                    NumberFormat
                            .getCurrencyInstance(Locale.US)
                            .format(item.getSalesPrice())
                            .toString()
            );
            holder.tvShortDescription.setText(item.getShortDescription());

            Glide.with(holder.ivThumbnail.getContext()).load(item.getThumbnailImage()).into(holder.ivThumbnail);

//            try {
//                URL thumbnailUrl = new URL(item.getThumbnailImage());
//                HttpURLConnection conn = (HttpURLConnection) thumbnailUrl.openConnection();
//                conn.setDoInput(true);
//                conn.connect();
//
//                InputStream is = conn.getInputStream();
//                holder.ivThumbnail.setImageBitmap(BitmapFactory.decodeStream(is));
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvName;
        private final TextView tvSalesPrice;
        private final TextView tvShortDescription;
        private final ImageView ivThumbnail;

        public ViewHolder(View itemView) {

            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvSalesPrice = itemView.findViewById(R.id.tvSalesPrice);
            tvShortDescription = itemView.findViewById(R.id.tvShortDescription);
            ivThumbnail = itemView.findViewById(R.id.ivThumbnail);
        }
    }
}
