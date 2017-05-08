package com.polytech.unice.tobeortohave.compare;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.polytech.unice.tobeortohave.DbHandler;
import com.polytech.unice.tobeortohave.R;
import com.polytech.unice.tobeortohave.compare.details.CompareDetailsActivity;
import com.polytech.unice.tobeortohave.dummy.ShopContent.ShopDetail;

public class CompareActivity extends AppCompatActivity implements ListShopCompareFragment.OnListFragmentInteractionListener{
    ShopDetail item1;
    ShopDetail item2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DbHandler.getInstance(getApplicationContext()).getSales();
        setContentView(R.layout.activity_compare);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frag_list,ListShopCompareFragment.newInstance(1)).commit();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frag_list2,ListShopCompareFragment.newInstance(2)).commit();


        findViewById(R.id.button_compare).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("item1 : " , item1.toString() );
                Log.d("item2 : " , item2.toString() );
                if (item1.equals(item2)){
                    Toast.makeText(getApplicationContext(), "Impossible de comparer deux fois le même magasin.", Toast.LENGTH_SHORT).show();
                }else if (item1 != null && item2 != null){
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
        Log.d("Select :", item + " : " + shopId);
        if (shopId == 1){
            item1 = item;
        }else if(shopId == 2){
            item2 = item;
        }

    }


}
