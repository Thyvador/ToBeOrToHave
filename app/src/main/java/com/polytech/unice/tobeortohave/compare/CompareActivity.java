package com.polytech.unice.tobeortohave.compare;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.polytech.unice.tobeortohave.R;
import com.polytech.unice.tobeortohave.dummy.ShopContent;
import com.polytech.unice.tobeortohave.dummy.ShopContent.ShopDetail;

public class CompareActivity extends AppCompatActivity implements ListShopCompareFragment.OnListFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public void onListFragmentInteraction(ShopDetail item) {

    }
}
