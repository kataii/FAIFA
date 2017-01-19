package com.khatthaphone.faifa;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class ViewDetails extends AppCompatActivity {

    TextView tValue1, tValue2, tValue3, tValue4, tValue5, tValue6, tCost1, tCost2, tCost3, tCost4, tCost5, tCost6, tCounterPrice, tFee, tDept, tTotalCost, tMultiplier;
    EditText ePowerUsed, eActualCost;
    int usedValue, value1, value2, value3, value4, value5, value6;
    Double cost1, cost2, cost3, cost4, cost5, cost6, actualCost, counterPrice, fee, dept, totalCost;
    String power;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        castViews();

        disableFocus();

        styling();

        getExtras();

//        setTexts();
    }

    private void getExtras() {


        String usedValue = MainActivity.bundle.getString("usedValue") + " kW";
        ePowerUsed.setText(usedValue);
        String value1 = MainActivity.bundle.getString("value1") + " kW";
        this.tValue1.setText(value1);
        String value2 = MainActivity.bundle.getString("value2") + " kW";
        this.tValue2.setText(value2);
        String value3 = MainActivity.bundle.getString("value3") + " kW";
        this.tValue3.setText(value3);
        String value4 = MainActivity.bundle.getString("value4") + " kW";
        this.tValue4.setText(value4);
        String value5 = MainActivity.bundle.getString("value5") + " kW";
        this.tValue5.setText(value5);
        String value6 = MainActivity.bundle.getString("value6") + " kW";
        this.tValue6.setText(value6);

//        String cost1 = MainActivity.bundle.getString("cost1");
//        this.tCost1.setText(cost1);
//        String cost2 = MainActivity.bundle.getString("cost2");
//        this.tCost2.setText(cost2);
//        String cost3 = MainActivity.bundle.getString("cost3");
//        this.tCost3.setText(cost3);
//        String cost4 = MainActivity.bundle.getString("cost4");
//        this.tCost4.setText(cost4);
//        String cost5 = MainActivity.bundle.getString("cost5");
//        this.tCost5.setText(cost5);
//        String cost6 = MainActivity.bundle.getString("cost6");
//        this.tCost6.setText(cost6);

        String multiplier = MainActivity.bundle.getString("multiplier");
        this.tMultiplier.setText(multiplier);
        String actualCost = MainActivity.bundle.getString("actualCost");
        this.eActualCost.setText(actualCost);
        String counterPrice = MainActivity.bundle.getString("counterPrice");
        this.tCounterPrice.setText(counterPrice);
        String fee = MainActivity.bundle.getString("fee");
        this.tFee.setText(fee);
        String dept = MainActivity.bundle.getString("dept");
        this.tDept.setText(dept);
        String totalCost = MainActivity.bundle.getString("totalCost");
        this.tTotalCost.setText(totalCost);

    }

    String formatDecimal(String number) {
        DecimalFormat format = new DecimalFormat("###,###");
        String formattedText = format.format(number);
        return formattedText;
    }

    private void castViews() {
        ePowerUsed = (EditText) findViewById(R.id.powerUsedValue);
        tValue1 = (TextView) findViewById(R.id.value1);
        tValue2 = (TextView) findViewById(R.id.value2);
        tValue3 = (TextView) findViewById(R.id.value3);
        tValue4 = (TextView) findViewById(R.id.value4);
        tValue5 = (TextView) findViewById(R.id.value5);
        tValue6 = (TextView) findViewById(R.id.value6);

//        cost1 = (TextView) findViewById(R.id.cost1);
//        cost2 = (TextView) findViewById(R.id.cost2);
//        cost3 = (TextView) findViewById(R.id.cost3);
//        cost4 = (TextView) findViewById(R.id.cost4);
//        cost5 = (TextView) findViewById(R.id.cost5);
//        cost6 = (TextView) findViewById(R.id.cost6);

        tMultiplier = (TextView) findViewById(R.id.multiplier);
        eActualCost = (EditText) findViewById(R.id.actualCost);
        tCounterPrice = (TextView) findViewById(R.id.counterPrice);
        tFee = (TextView) findViewById(R.id.fee);
        tDept = (TextView) findViewById(R.id.dept);
        tTotalCost = (TextView) findViewById(R.id.totalCost);

        eActualCost.setFocusable(false);
    }

    private void styling() {
        FloatingActionButton save = (FloatingActionButton) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "ໃຈເຢັນໆ, ຟີເຈີ້ການບັນທຶກຄ່າໄຟກຳລັງຢູ່ໃນຂັ້ນຕອນການພັດທະນາ", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void disableFocus() {
        ePowerUsed.setFocusable(false);
//        lowPowerUsedValue.setFocusable(false);
//        normalPowerUsedValue.setFocusable(false);
//        highPowerUsedValue.setFocusable(false);
//        lowValueCost.setFocusable(false);
//        normalValueCost.setFocusable(false);
//        highValueCost.setFocusable(false);
//        actualCost.setFocusable(false);
//        counterPrice.setFocusable(false);
//        fee.setFocusable(false);
//        dept.setFocusable(false);
//        totalCost.setFocusable(false);
    }

}
