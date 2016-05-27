package com.khatthaphone.faifa;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class ViewDetails extends AppCompatActivity {

    EditText powerUsed, lowPowerUsedValue, normalPowerUsedValue, highPowerUsedValue, lowValueCost, normalValueCost, highValueCost, actualCost, counterPrice, fee, dept, totalCost;
    int iPowerUsedValue, iLowPowerUsedValue, iNormalPowerUsedValue, iHighPowerUsedValue;
    Double iLowValueCost, iNormalValueCost, iHighValueCost, iActualCost, iCounterPrice, iFee, iDept, iTotalCost;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        powerUsed = (EditText) findViewById(R.id.powerUsed);
        lowPowerUsedValue = (EditText) findViewById(R.id.lowPowerUsedValue);
        normalPowerUsedValue = (EditText) findViewById(R.id.normalPowerUsedValue);
        highPowerUsedValue = (EditText) findViewById(R.id.highPowerUsedValue);
        lowValueCost = (EditText) findViewById(R.id.normalValueCost);
        normalValueCost = (EditText) findViewById(R.id.highValueCost);
        highValueCost = (EditText) findViewById(R.id.highValueCost);
        actualCost = (EditText) findViewById(R.id.actualCost);
        counterPrice = (EditText) findViewById(R.id.counterPrice);
        fee = (EditText) findViewById(R.id.fee);
        dept = (EditText) findViewById(R.id.dept);
        totalCost = (EditText) findViewById(R.id.totalCost);

        disableFocus();

        bundle = getIntent().getExtras();
        iPowerUsedValue = bundle.getInt("powerUsedValue");
        iLowPowerUsedValue = bundle.getInt("lowPowerUsedValue");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.save);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void disableFocus() {
        powerUsed.setFocusable(false);
        lowPowerUsedValue.setFocusable(false);
        normalPowerUsedValue.setFocusable(false);
        highPowerUsedValue.setFocusable(false);
        lowValueCost.setFocusable(false);
        normalValueCost.setFocusable(false);
        highValueCost.setFocusable(false);
        actualCost.setFocusable(false);
        counterPrice.setFocusable(false);
        fee.setFocusable(false);
        dept.setFocusable(false);
        totalCost.setFocusable(false);
    }

}
