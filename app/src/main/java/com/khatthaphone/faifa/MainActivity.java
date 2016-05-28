package com.khatthaphone.faifa;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText lastValue, currentValue, powerUsed, eDept;
    Spinner counterType;
    public static int powerUsedValue, lowPowerUsedValue, normalPowerUsedValue, highPowerUsedValue, counterPosition;
    public static double actualCost, totalCost, lowValueCost, normalValueCost, highValueCost, fee, dept, counterPrice;

    double lowValuePrice = 348;
    double normalValuePrice = 414;
    double highValuePrice = 999;

    public static Bundle bundle = new Bundle();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(myToolbar);

        lastValue = (EditText) findViewById(R.id.lastValue);
        currentValue = (EditText) findViewById(R.id.currentValue);
        powerUsed = (EditText) findViewById(R.id.powerUsed);
        eDept = (EditText) findViewById(R.id.dept);
        counterType = (Spinner) findViewById(R.id.counterType);

        powerUsed.setFocusable(false);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.counterType, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        counterType.setAdapter(adapter);

        valueSync();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.next);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (powerUsedValue <= 25) {
                    lowPowerUsedValue = powerUsedValue;
                    lowValueCost = powerUsedValue * lowValuePrice;
                    actualCost = lowValueCost;
                }

                if (powerUsedValue >= 26 && powerUsedValue <= 150) {
                    lowPowerUsedValue = 25;
                    lowValueCost = lowPowerUsedValue * lowValuePrice;
                    normalValueCost = (powerUsedValue - lowPowerUsedValue) * normalValuePrice;
                    actualCost = lowValueCost + normalValueCost;
                }

                if (powerUsedValue > 150) {
                    lowPowerUsedValue = 25;
                    lowValueCost = lowPowerUsedValue * lowValuePrice;
                    normalPowerUsedValue = (150 - lowPowerUsedValue);
                    normalValueCost = normalPowerUsedValue * normalValuePrice;
                    highPowerUsedValue = powerUsedValue - 150;
                    highValueCost = highPowerUsedValue * highValuePrice;
                    actualCost = lowValueCost + normalValueCost + highValueCost;
                }

                dept = TryGetDept();
                counterPrice = getCounterPriceByType();
                fee = (counterPrice + actualCost) * 0.1;
                totalCost = actualCost + fee + counterPrice + dept;
                showDialog(counterPrice, actualCost, fee, dept, totalCost);
                startViewDetailsActivity();
            }
        });
    }

    private void valueSync() {

        lastValue.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    powerUsedValue = (Integer.parseInt(currentValue.getText().toString())) - Integer.parseInt(lastValue.getText().toString());
                    Log.i("MainActivity", " " + powerUsedValue);
                    powerUsed.setText("" + powerUsedValue);

                }
            }
        });
//        currentValue.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean b) {
//                if (!b) {
//                    powerUsedValue = (Integer.parseInt(currentValue.getText().toString())) - Integer.parseInt(lastValue.getText().toString());
//                    Log.i("MainActivity", " " + powerUsedValue);
//                    powerUsed.setText("" + powerUsedValue);
//
//                }
//            }
//        });

    }

    private double TryGetDept() {
        double DEPT = 0;
        try {
            DEPT = Double.parseDouble(eDept.getText().toString());
        } catch (Exception e) {

        } finally {
            if (!(DEPT == 0)) {
                return DEPT;
            } else {
                return 0;
            }
        }
    }

    private void showDialog(double counterPrice, double actualCost, double fee, double dept, double totalCost) {
        Dialog d = new Dialog(this);
        TextView tv = new TextView(this);
        tv.setText("Power " + lowPowerUsedValue + "kW price: " + lowValueCost + "\n" + "Power " + normalPowerUsedValue + "kW: " + normalValueCost + "\n" + "Power " + highPowerUsedValue + "kW: " + highValueCost + "\n" + "Actual price: " + (actualCost) + "\n" + "Fee: " + fee + "\n" + "Counter Price: " + counterPrice + "\n" + "Dept: " + dept + "\n" + "Total: " + totalCost);
        tv.setPadding(20, 20, 20, 20);
        d.setContentView(tv);
//        d.show();
        d.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                startViewDetailsActivity();
            }
        });
    }

    private double getCounterPriceByType() {
        counterPosition = counterType.getSelectedItemPosition();
        String counter = counterType.getSelectedItem().toString();
        double price = 0;
        Log.i("Counter Price: ", counter);
        if (counter.equals("1 Phase 3/9 A")) {
            price = 1400;
        }
        if (counter.equals("1 Phase 5/40 A")) {
            price = 4200;
        }
        if (counter.equals("1 Phase 5/80 A")) {
            price = 5200;
        }
        if (counter.equals("3 Phase ຕໍ່ກົງ")) {
            price = 14000;
        }
        if (counter.equals("3 Phase ຕໍ່ຜ່ານ 0.4kV CT")) {
            price = 84400;
        }
        if (counter.equals("3 Phase ຕໍ່ຜ່ານ 22kV PT & CT") | counter.equals("3 Phase ຕໍ່ຜ່ານ 115kV PT & CT")) {
            price = 84400;
        }
        if (counter.equals("AMR ຕໍ່ຜ່ານ 22kV PT & CT") | counter.equals("AMR ຕໍ່ຜ່ານ 115kV PT & CT")) {
            price = 125300;
        }
/*        switch (counter) {
            case "1 Phase 3/9 A":
                price = 1400;
            case "1 Phase 5/40 A":
                price = 4200;
            case "1 Phase 5/80 A":
                price = 5200;
            case "3 Phase ຕໍ່ກົງ":
                price = 14000;
            case "3 Phase ຕໍ່ຜ່ານ 0.4kV CT":
                price = 84400;
            case "3 Phase ຕໍ່ຜ່ານ 22kV PT &amp; CT":
                price = 84400;
            case "3 Phase ຕໍ່ຜ່ານ 115kV PT &amp; CT":
                price = 84400;
            case "AMR ຕໍ່ຜ່ານ 22kV PT &amp; CT":
                price = 125300;
            case "AMR ຕໍ່ຜ່ານ 115kV PT &amp; CT":
                price = 125300;
        }*/
        return price;
    }

    public void startViewDetailsActivity() {
        Intent intent = new Intent(this, ViewDetails.class);

//        powerUsed, lowPowerUsedValue, normalPowerUsedValue, highPowerUsedValue, lowValueCost, normalValueCost, highValueCost, actualCost, counterPrice, fee, dept, totalCost;

        bundle.putString("powerUsedValue", "" + powerUsedValue);
        bundle.putString("lowPowerUsedValue", "" + lowPowerUsedValue);
        bundle.putString("normalPowerUsedValue", "" + normalPowerUsedValue);
        bundle.putString("highPowerUsedValue", "" + highPowerUsedValue);
        bundle.putString("lowValueCost", "" + lowValueCost);
        bundle.putString("normalValueCost", "" + normalValueCost);
        bundle.putString("highValueCost", "" + highValueCost);
        bundle.putString("actualCost", "" + actualCost);
        bundle.putString("counterPrice", "" + counterPrice);
        bundle.putString("fee", "" + fee);
        bundle.putString("dept", "" + dept);
        bundle.putString("totalCost", "" + totalCost);

        startActivity(intent);
    }
/*
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("currentValue", "" + currentValue);
        outState.putString("lastValue", "" + lastValue);
        outState.putString("powerUsedValue", "" + powerUsedValue);
        outState.putInt("counterType", counterPosition);
        outState.putString("dept", "" + dept);

    }

    @Override
    protected void onRestoreInstanceState(Bundle inState) {
        super.onRestoreInstanceState(inState);

        String lastValue = inState.getString("lastValue");
        this.lastValue.setText(lastValue);
        Log.i("Lastest Value: ", lastValue);
        String currentValue = inState.getString("currentValue");
        this.currentValue.setText(currentValue);
        Log.i("Current Value: ", currentValue);
        String powerUsedValue = inState.getString("powerUsedValue");
        powerUsed.setText(powerUsedValue);
        String dept = inState.getString("dept");
        eDept.setText(dept);
        int counterType = inState.getInt("counterType");
        this.counterType.setSelection(counterType);

    }*/
}
