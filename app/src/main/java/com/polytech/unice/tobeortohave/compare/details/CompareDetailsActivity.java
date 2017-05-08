package com.polytech.unice.tobeortohave.compare.details;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.polytech.unice.tobeortohave.R;
import com.polytech.unice.tobeortohave.compare.details.dummy.SalesContent;
import com.polytech.unice.tobeortohave.dummy.ShopContent.ShopDetail;
import com.polytech.unice.tobeortohave.list.shop.ShopDetailFragment;

public class CompareDetailsActivity extends AppCompatActivity implements CompareDetailsFragment.OnListFragmentInteractionListener,
        ShopDetailFragment.OnFragmentInteractionListener {

    private ShopDetail item1;
    private ShopDetail item2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        item1 = getIntent().getParcelableExtra("item1");
        item2 = getIntent().getParcelableExtra("item2");
        getSupportFragmentManager().beginTransaction().replace(R.id.item1_frag, CompareDetailsFragment.newInstance(item1)).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.item2_frag, CompareDetailsFragment.newInstance(item2)).commit();
    }

    @Override
    public void onListFragmentInteraction(SalesContent.Sales item) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        
    }
}

// TODO: 03/05/2017 Communication entre activité
