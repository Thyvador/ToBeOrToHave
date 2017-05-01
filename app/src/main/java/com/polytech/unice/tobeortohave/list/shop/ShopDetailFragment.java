package com.polytech.unice.tobeortohave.list.shop;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.polytech.unice.tobeortohave.R;
import com.polytech.unice.tobeortohave.dummy.ShopContent;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ShopDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ShopDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShopDetailFragment extends Fragment {

    private static int shopId;
    private OnFragmentInteractionListener mListener;

    public ShopDetailFragment() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ListShopCompareFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShopDetailFragment newInstance(int shopId) {
        ShopDetailFragment.shopId = shopId;
        return new ShopDetailFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_shop_detail, container, false);
        ((TextView) view.findViewById(R.id.shop_adress)).setText(String.format("Adresse : %s", ShopContent.ITEM_MAP.get(shopId).adress));
        ((TextView) view.findViewById(R.id.shop_benef)).setText(String.format("Bénéfices : %s", ShopContent.ITEM_MAP.get(shopId).benefits));
        ((TextView) view.findViewById(R.id.shop_cost)).setText(String.format("Coûts : %s", ShopContent.ITEM_MAP.get(shopId).cost));
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
