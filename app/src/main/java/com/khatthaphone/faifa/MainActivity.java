package com.khatthaphone.faifa;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText lastValue, currentValue, powerUsed, dept;
    Button btnCaculate;
    Spinner counterType;
    int powerUsedValue;
    double actualCost, totalCost, lowValueCost, normalValueCost, highValueCost;

    double lowValuePrice = 348;
    double normalValuePrice = 414;
    double highValuePrice = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lastValue = (EditText) findViewById(R.id.lastValue);
        currentValue = (EditText) findViewById(R.id.currentValue);
        powerUsed = (EditText) findViewById(R.id.powerUsed);
        dept = (EditText) findViewById(R.id.dept);
        btnCaculate = (Button) findViewById(R.id.btnCalculate);
        counterType = (Spinner) findViewById(R.id.counterType);

        powerUsed.setFocusable(false);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.counterType, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        counterType.setAdapter(adapter);

        valueSync();
        btnCaculate.setOnClickListener(this);

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

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnCalculate) {

            if (powerUsedValue <= 25) {
                actualCost = powerUsedValue*lowValuePrice;
            }

            double counterPrice = getCounterPrice();
            showDialog(counterPrice, actualCost);

        }
    }

    private void showDialog(double counterPrice, double actualCost) {
        Dialog d = new Dialog(this);
        TextView tv = new TextView(this);
        tv.setText("Counter Price: " + counterPrice + "\n"
        + "Power <25 kW price: " + actualCost);
        tv.setPadding(20, 20, 20, 20);
        d.setContentView(tv);
        d.show();
    }

    private double getCounterPrice() {
        String counter = counterType.getSelectedItem().toString();
        double price = 0;
        switch (counter) {
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
        }
        return price;
    }
}
