package com.sekrab.paginationwithsearch;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class Recycleradapter extends RecyclerView.Adapter<Recycleradapter.Viewholder> implements Filterable {

    private List<modelpojo> data;
    private List<modelpojo> datafiltered;
    private modelpojo model;
    private Context context;


    boolean isLoading = false,
            isMoreDataAvailable = true;
    Recycleradapter.OnLoadMoreListener loadMoreListener;


    public Recycleradapter(List<modelpojo> data, Context context) {
        this.data = data;
        datafiltered = new ArrayList<>(data);
        this.context = context;

    }

    @Override
    public Filter getFilter() {
        return exaplefilter;
    }

    private Filter exaplefilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<modelpojo> filterdlist = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filterdlist.addAll(data);

            } else {
                String filterstring = constraint.toString().toLowerCase().trim();
                for (modelpojo ndh : data) {
                    if (ndh.getName().toLowerCase().contains(filterstring)) {
                        filterdlist.add(ndh);

                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filterdlist;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            data.clear();
            data.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public static class Viewholder extends RecyclerView.ViewHolder {

        // 1. Declare your Views here

        public ImageView imag;
        public TextView name;
        public TextView description;


        public Viewholder(View itemView) {
            super(itemView);

            // 2. Define your Views here

            imag = (ImageView) itemView.findViewById(R.id.imag);
            name = (TextView) itemView.findViewById(R.id.name);
            description = (TextView) itemView.findViewById(R.id.description);

        }
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.listitem, parent, false);

        return new Viewholder(itemView);
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {
        if (position >= getItemCount() - 1 && isMoreDataAvailable && !isLoading && loadMoreListener != null) {


            isLoading = true;
            loadMoreListener.onLoadMore();


        }
        model = data.get(position);
        holder.name.setText(model.getName());
        holder.description.setText(model.getLatitude() + model.getLongitude());

        // 3. set the data to your Views here

        /**
         *
         The format to set data should be like this example:
         --------------------------------------
         holder.txvName.setText(model.getName());
         *
         */
        Glide.with(context)
                .load(model.getProfile_picture())
                .into(holder.imag);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void notifyDataChanged() {
        notifyDataSetChanged();
        isLoading = false;
    }

    public static class OnLoadMoreListener {
        public void onLoadMore() {
        }
    }


    public void setLoadMoreListener(Recycleradapter.OnLoadMoreListener loadMoreListener) {
        this.loadMoreListener = loadMoreListener;
        Log.d("Success", "Comes setLoadMoreListener");
    }


    public interface ContactsAdapterListener {
        void onContactSelected(List<modelpojo> contact);
    }

}
