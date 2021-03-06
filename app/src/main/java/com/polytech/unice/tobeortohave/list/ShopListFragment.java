package com.polytech.unice.tobeortohave.list;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.polytech.unice.tobeortohave.DbHandler;
import com.polytech.unice.tobeortohave.R;
import com.polytech.unice.tobeortohave.dummy.ShopContent;
import com.polytech.unice.tobeortohave.dummy.ShopContent.ShopDetail;

import java.util.Collections;
import java.util.Comparator;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ShopListFragment extends Fragment {

    private OnListFragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    private Spinner spinnerSort;
    private LinearLayout layoutFrag;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ShopListFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static ShopListFragment newInstance() {
        return new ShopListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DbHandler.getInstance(getContext()).getShops();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop_list, container, false);
        spinnerSort = (Spinner) view.findViewById(R.id.sort_choice);

        layoutFrag = (LinearLayout) view.findViewById(R.id.layout_list_shop);


        final MyShopDetailsRecyclerViewAdapter adapter = new MyShopDetailsRecyclerViewAdapter(ShopContent.ITEMS, mListener);
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(getContext(), R.array.sort_choice, R.layout.spinner_item);
        spinnerAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinnerSort.setAdapter(spinnerAdapter);
        spinnerSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Comparator<ShopDetail> comparator = null;
                if (position == 0) {
                    comparator = new Comparator<ShopDetail>() {
                        @Override
                        public int compare(ShopDetail o1, ShopDetail o2) {
                            return o2.id - o1.id;
                        }
                    };
                } else if (position == 1) {
                    comparator = new Comparator<ShopDetail>() {
                        @Override
                        public int compare(ShopDetail o1, ShopDetail o2) {
                            return o2.benefits - o1.benefits;
                        }
                    };
                }
                Collections.sort(ShopContent.ITEMS, comparator);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        recyclerView = (RecyclerView) view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
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


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        Log.d("ORIENTATION :", String.valueOf(newConfig.orientation));
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            layoutFrag.setOrientation(LinearLayout.HORIZONTAL);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            layoutFrag.setOrientation(LinearLayout.VERTICAL);
        }
        Log.d("Layout orientation :", String.valueOf(layoutFrag.getOrientation()));

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
        void onListFragmentInteraction(ShopDetail item);
    }


}
