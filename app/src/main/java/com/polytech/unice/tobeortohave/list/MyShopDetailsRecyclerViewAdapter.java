package com.polytech.unice.tobeortohave.list;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.polytech.unice.tobeortohave.R;
import com.polytech.unice.tobeortohave.list.ShopDetailsFragment.OnListFragmentInteractionListener;

import java.util.List;

import static com.polytech.unice.tobeortohave.list.dummy.DummyContent.ShopDetail;

/**
 * {@link RecyclerView.Adapter} that can display a {@link ShopDetail} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyShopDetailsRecyclerViewAdapter extends RecyclerView.Adapter<MyShopDetailsRecyclerViewAdapter.ViewHolder> {

    private final List<ShopDetail> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyShopDetailsRecyclerViewAdapter(List<ShopDetail> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_shopdetails, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        Log.d("holder : ", holder.toString());
        holder.mContentName.setText(mValues.get(position).name);
        holder.mContentAdress.setText(mValues.get(position).adress);

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
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentName;
        public final TextView mContentAdress;
        public ShopDetail mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentName = (TextView) view.findViewById(R.id.name);
            mContentAdress = (TextView) view.findViewById(R.id.adress);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentName.getText() + "'";
        }
    }
}
