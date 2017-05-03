package com.polytech.unice.tobeortohave.compare;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.polytech.unice.tobeortohave.R;
import com.polytech.unice.tobeortohave.compare.ListShopCompareFragment.OnListFragmentInteractionListener;
import com.polytech.unice.tobeortohave.compare.dummy.DummyContent.DummyItem;
import com.polytech.unice.tobeortohave.dummy.ShopContent.ShopDetail;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyShopDetailRecyclerViewAdapter extends RecyclerView.Adapter<MyShopDetailRecyclerViewAdapter.ViewHolder> {

    private final List<ShopDetail> mValues;
    private final OnListFragmentInteractionListener mListener;
    private int fragId;
    private ViewHolder checkedHolder;

    public MyShopDetailRecyclerViewAdapter(List<ShopDetail> items, OnListFragmentInteractionListener listener, int fragId) {
        mValues = items;
        mListener = listener;
        this.fragId = fragId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_shop_compare, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.checkBox.setText(mValues.get(position).name);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(holder.mItem, fragId);
                    changeCheckBox(holder);
                }
            }
        });
    }

    public void changeCheckBox(ViewHolder holder){
        if (checkedHolder != null){
            checkedHolder.checkBox.setChecked(false);
        }
        checkedHolder = holder;
        checkedHolder.checkBox.setChecked(true);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final CheckBox checkBox;
        public ShopDetail mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            checkBox = (CheckBox) view.findViewById(R.id.check_box);
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    changeCheckBox(ViewHolder.this);
                }
            });
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
}
