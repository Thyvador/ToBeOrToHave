package com.polytech.unice.tobeortohave.list.shop;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.polytech.unice.tobeortohave.R;
import com.polytech.unice.tobeortohave.list.shop.dummy.EmployeContent;
import com.polytech.unice.tobeortohave.list.shop.dummy.ShopDetailFragment;

public class ShopDetailActivity extends AppCompatActivity implements EmployeFragment.OnListFragmentInteractionListener, ShopDetailFragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    @Override
    public void onListFragmentInteraction(EmployeContent.EmployerDetails item) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
