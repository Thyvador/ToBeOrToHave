package com.polytech.unice.tobeortohave.compare.details;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.polytech.unice.tobeortohave.R;
import com.polytech.unice.tobeortohave.compare.details.dummy.SalesContent;
import com.polytech.unice.tobeortohave.dummy.ShopContent.ShopDetail;
import com.polytech.unice.tobeortohave.list.shop.ShopDetailFragment;

import java.util.ArrayList;

public class CompareDetailsActivity extends AppCompatActivity implements CompareDetailsFragment.OnListFragmentInteractionListener,
        ShopDetailFragment.OnFragmentInteractionListener {

    private ShopDetail item1;
    private ShopDetail item2;
    BarChart chart;
    ArrayList<BarEntry> BARENTRY;
    ArrayList<String> BarEntryLabels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        item1 = getIntent().getParcelableExtra("item1");
        item2 = getIntent().getParcelableExtra("item2");
        Log.d("item1 : ", item1.toString());
        Log.d("item2 : ", item2.toString());
        getSupportFragmentManager().beginTransaction().replace(R.id.item1_frag, CompareDetailsFragment.newInstance(item1)).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.item2_frag, CompareDetailsFragment.newInstance(item2)).commit();

        chart = (BarChart) findViewById(R.id.chart);

        BARENTRY = new ArrayList<>();

        BarEntryLabels = new ArrayList<>();
        AddValuesToBARENTRY();
        BarData data = new BarData(BarEntryLabels, getDataSet());
        data.setValueTextSize(35);

        chart.setData(data);

        chart.getAxisLeft().setTextSize(30);
        chart.getAxisRight().setEnabled(false);

        chart.getLegend().setTextSize(30);
        chart.getXAxis().setTextSize(30);


        chart.animateY(3000);

    }

    private ArrayList<IBarDataSet> getDataSet() {
        ArrayList<IBarDataSet> dataSets;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(item1.benefits, 0);
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(item2.cost, 1);
        valueSet1.add(v1e2);

        ArrayList<BarEntry> valueSet2 = new ArrayList<>();
        BarEntry v2e1 = new BarEntry(item2.benefits, 0);
        valueSet2.add(v2e1);
        BarEntry v2e2 = new BarEntry(item2.cost, 1);
        valueSet2.add(v2e2);


        BarDataSet barDataSet1 = new BarDataSet(valueSet1, item1.name + "   ");
        barDataSet1.setColor(getResources().getColor(R.color.colorAccent));
        BarDataSet barDataSet2 = new BarDataSet(valueSet2, item2.name);
        barDataSet2.setColor(getResources().getColor(R.color.colorPrimaryDark));

        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);
        return dataSets;
    }

    private void AddValuesToBARENTRY() {
        BarEntryLabels.add("Bénéfices");
        BarEntryLabels.add("Coûts");
        BarEntryLabels.add(" ");

    }

    @Override
    public void onListFragmentInteraction(SalesContent.Sales item) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

// TODO: 03/05/2017 Communication entre activité
