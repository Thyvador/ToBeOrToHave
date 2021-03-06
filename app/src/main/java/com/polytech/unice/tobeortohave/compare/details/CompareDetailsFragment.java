package com.polytech.unice.tobeortohave.compare.details;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.polytech.unice.tobeortohave.R;
import com.polytech.unice.tobeortohave.compare.details.dummy.SalesContent;
import com.polytech.unice.tobeortohave.dummy.ShopContent.ShopDetail;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class CompareDetailsFragment extends Fragment {

    // TODO: Customize parameters
    private OnListFragmentInteractionListener mListener;

    private ShopDetail shopDetail;
    private MySalesRecyclerViewAdapter adapter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CompareDetailsFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static CompareDetailsFragment newInstance(ShopDetail shopDetail) {
        CompareDetailsFragment fragment = new CompareDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable("shop", shopDetail);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        shopDetail = getArguments().getParcelable("shop");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_sales_list, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Log.d("pfff", String.valueOf(shopDetail.id));
        adapter = new MySalesRecyclerViewAdapter(SalesContent.LIST_MAP.get(shopDetail.id), mListener);
        recyclerView.setAdapter(adapter);
        ((TextView) view.findViewById(R.id.name_text_view)).setText(shopDetail.name);

        final EditText filterEdit = ((EditText) view.findViewById(R.id.edit_filter));

        // TODO: 17/05/2017 Régler probleme communication avec l'adapter
        filterEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        ((Switch) view.findViewById(R.id.switch_filter)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    filterEdit.setVisibility(View.VISIBLE);
                    filterEdit.requestFocus();
                } else {
                    filterEdit.setVisibility(View.GONE);
                    filterEdit.setText("");
                    adapter.unfilter();
                }
            }
        });

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(SalesContent.Sales item);
    }


}
