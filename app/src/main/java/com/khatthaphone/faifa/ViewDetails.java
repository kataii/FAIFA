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

    TextView lowPowerUsedValue, normalPowerUsedValue, highPowerUsedValue, lowValueCost, normalValueCost, highValueCost, counterPrice, fee, dept, totalCost, multiplier;
    EditText powerUsed, actualCost;
    int iPowerUsedValue, iLowPowerUsedValue, iNormalPowerUsedValue, iHighPowerUsedValue;
    Double iLowValueCost, iNormalValueCost, iHighValueCost, iActualCost, iCounterPrice, iFee, iDept, iTotalCost;
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


        String powerUsedValue = MainActivity.bundle.getString("powerUsedValue") + " kW";
        powerUsed.setText(powerUsedValue);
        String lowPowerUsedValue = MainActivity.bundle.getString("lowPowerUsedValue") + " kW";
        this.lowPowerUsedValue.setText(lowPowerUsedValue);
        String normalPowerUsedValue = MainActivity.bundle.getString("normalPowerUsedValue") + " kW";
        this.normalPowerUsedValue.setText(normalPowerUsedValue);
        String highPowerUsedValue = MainActivity.bundle.getString("highPowerUsedValue") + " kW";
        this.highPowerUsedValue.setText(highPowerUsedValue);
        String lowValueCost = MainActivity.bundle.getString("lowValueCost");
        this.lowValueCost.setText(lowValueCost);
        String normalValueCost = MainActivity.bundle.getString("normalValueCost");
        this.normalValueCost.setText(normalValueCost);
        String highValueCost = MainActivity.bundle.getString("highValueCost");
        this.highValueCost.setText(highValueCost);
        String multiplier = MainActivity.bundle.getString("multiplier");
        this.multiplier.setText(multiplier);
        String actualCost = MainActivity.bundle.getString("actualCost");
        this.actualCost.setText(actualCost);
        String counterPrice = MainActivity.bundle.getString("counterPrice");
        this.counterPrice.setText(counterPrice);
        String fee = MainActivity.bundle.getString("fee");
        this.fee.setText(fee);
        String dept = MainActivity.bundle.getString("dept");
        this.dept.setText(dept);
        String totalCost = MainActivity.bundle.getString("totalCost");
        this.totalCost.setText(totalCost);

    }

    String formatDecimal(String number) {
        DecimalFormat format = new DecimalFormat("###,###");
        String formattedText = format.format(number);
        return formattedText;
    }

    private void castViews() {
        powerUsed = (EditText) findViewById(R.id.powerUsedValue);
        lowPowerUsedValue = (TextView) findViewById(R.id.lowPowerUsedValue);
        normalPowerUsedValue = (TextView) findViewById(R.id.normalPowerUsedValue);
        highPowerUsedValue = (TextView) findViewById(R.id.highPowerUsedValue);
        lowValueCost = (TextView) findViewById(R.id.lowValueCost);
        normalValueCost = (TextView) findViewById(R.id.normalValueCost);
        highValueCost = (TextView) findViewById(R.id.highValueCost);
        multiplier = (TextView) findViewById(R.id.multiplier);
        actualCost = (EditText) findViewById(R.id.actualCost);
        counterPrice = (TextView) findViewById(R.id.counterPrice);
        fee = (TextView) findViewById(R.id.fee);
        dept = (TextView) findViewById(R.id.dept);
        totalCost = (TextView) findViewById(R.id.totalCost);

        actualCost.setFocusable(false);
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
        powerUsed.setFocusable(false);
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
