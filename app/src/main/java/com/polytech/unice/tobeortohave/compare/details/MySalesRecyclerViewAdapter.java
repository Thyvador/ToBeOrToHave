package com.polytech.unice.tobeortohave.compare.details;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.polytech.unice.tobeortohave.R;
import com.polytech.unice.tobeortohave.compare.details.CompareDetailsFragment.OnListFragmentInteractionListener;
import com.polytech.unice.tobeortohave.compare.details.dummy.SalesContent;
import com.polytech.unice.tobeortohave.compare.details.dummy.SalesContent.Sales;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Sales} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class MySalesRecyclerViewAdapter extends RecyclerView.Adapter<MySalesRecyclerViewAdapter.ViewHolder> {

    private final List<Sales> mValues;
    private List<Sales> displayValues;
    private final OnListFragmentInteractionListener mListener;

    public MySalesRecyclerViewAdapter(List<SalesContent.Sales> items, OnListFragmentInteractionListener listener) {
        Collections.sort(items);
        mValues = items;
        displayValues = new ArrayList<>(mValues);
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_sales, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Log.d("list ", displayValues.toString());
        holder.mItem = displayValues.get(position);
        holder.mIdView.setText(displayValues.get(position).sale);
        holder.mContentView.setText(String.valueOf(displayValues.get(position).number));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return displayValues.size();
    }

    public void filter(String str) {
        displayValues.clear();
        for (Sales sales : mValues){
            if (sales.sale.toLowerCase().contains(str.toLowerCase())) displayValues.add(sales);
        }
        notifyDataSetChanged();
    }

    public void unfilter() {
        displayValues = new ArrayList<>(mValues);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public SalesContent.Sales mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.name);
            mContentView = (TextView) view.findViewById(R.id.number);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }

}
