package com.polytech.unice.tobeortohave.compare;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.polytech.unice.tobeortohave.R;
import com.polytech.unice.tobeortohave.dummy.ShopContent;
import com.polytech.unice.tobeortohave.dummy.ShopContent.ShopDetail;

public class CompareActivity extends AppCompatActivity implements ListShopCompareFragment.OnListFragmentInteractionListener{
    ShopDetail item1;
    ShopDetail item2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frag_list,ListShopCompareFragment.newInstance(1)).addToBackStack(null).commit();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frag_list2,ListShopCompareFragment.newInstance(2)).addToBackStack(null).commit();


        findViewById(R.id.button_compare).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (item1.compareTo(item2) == 0){
                    Toast.makeText(getApplicationContext(), "Impossible de comparer deux fois le même magasin.", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(getApplicationContext(), CompareDetailsActivity.class);
                    intent.putExtra("item1", item1);
                    intent.putExtra("item2", item2);
                    startActivity(intent);
                }
            }
        });


    }

    @Override
    public void onListFragmentInteraction(ShopDetail item, int shopId) {
        if (shopId == 1){
            item1 = item;
        }else if(shopId == 2){
            item2 = item;
        }

    }
}
