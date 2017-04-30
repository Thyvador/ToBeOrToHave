package com.polytech.unice.tobeortohave.list.shop;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.polytech.unice.tobeortohave.list.shop.EmployeFragment.OnListFragmentInteractionListener;
import com.polytech.unice.tobeortohave.list.shop.dummy.EmployeContent.EmployerDetails;
import com.polytech.unice.tobeortohave.R;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link EmployerDetails} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyEmployeRecyclerViewAdapter extends RecyclerView.Adapter<MyEmployeRecyclerViewAdapter.ViewHolder> {

    private final List<EmployerDetails> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyEmployeRecyclerViewAdapter(List<EmployerDetails> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_employe, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mContentName.setText(mValues.get(position).name);
        holder.mContentName.setText(mValues.get(position).role);

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
        public final TextView mContentRole;
        public final TextView mContentName;
        public EmployerDetails mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentName = (TextView) view.findViewById(R.id.name);
            mContentRole = (TextView) view.findViewById(R.id.role);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentName.getText() + "'";
        }
    }
}
