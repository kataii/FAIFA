package com.khatthaphone.faifa;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
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

    EditText lastValue, currentValue, powerUsed, eDept;
    Button btnCaculate;
    Spinner counterType;
    int powerUsedValue, lowPowerUsedValue, normalPowerUsedValue, highPowerUsedValue;
    double actualCost, totalCost, lowValueCost, normalValueCost, highValueCost, fee, dept, counterPrice;

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
        eDept = (EditText) findViewById(R.id.dept);
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
                lowPowerUsedValue = powerUsedValue;
                lowValueCost = powerUsedValue*lowValuePrice;
                actualCost = lowValueCost;
            }

            if (powerUsedValue >= 26 && powerUsedValue <= 150) {
                lowPowerUsedValue = 25;
                lowValueCost = lowPowerUsedValue *lowValuePrice;
                normalValueCost = (powerUsedValue- lowPowerUsedValue)*normalValuePrice;
                actualCost = lowValueCost + normalValueCost;
            }

            if (powerUsedValue > 150) {
                lowPowerUsedValue = 25;
                lowValueCost = lowPowerUsedValue *lowValuePrice;
                normalPowerUsedValue = (150- lowPowerUsedValue);
                normalValueCost = normalPowerUsedValue*normalValuePrice;
                highPowerUsedValue = powerUsedValue-150;
                highValueCost = highPowerUsedValue*highValuePrice;
                actualCost = lowValueCost + normalValueCost + highValueCost;
            }

            dept = getDept();
            counterPrice = getCounterPrice();
            fee = (counterPrice+actualCost)*0.1;
            totalCost = actualCost + fee + counterPrice + dept;
            showDialog(counterPrice, actualCost, fee, dept, totalCost);
        }
    }

    private double getDept() {
        double DEPT = 0;
        try {
            DEPT = Double.parseDouble(eDept.getText().toString());
        } catch (Exception e) {

        } finally {
            if (!(DEPT==0)) {
                return DEPT;
            }
            else {
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
        d.show();
        d.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                startViewDetalsActivity();
            }
        });
    }

    private double getCounterPrice() {
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

    public void startViewDetalsActivity() {
        Intent intent = new Intent(this, ViewDetails.class);

//        powerUsed, lowPowerUsedValue, normalPowerUsedValue, highPowerUsedValue, lowValueCost, normalValueCost, highValueCost, actualCost, counterPrice, fee, dept, totalCost;

        intent.putExtra("powerUsedValue", powerUsedValue);
        intent.putExtra("lowPowerUsedValue", lowPowerUsedValue);
        intent.putExtra("normalPowerUsedValue", normalPowerUsedValue);
        intent.putExtra("highPowerUsedValue", highPowerUsedValue);
        intent.putExtra("lowValueCost", lowValueCost);
        intent.putExtra("normalValueCost", normalValueCost);
        intent.putExtra("highValueCost", highValueCost);
        intent.putExtra("actualCost", actualCost);
        intent.putExtra("counterPrice", counterPrice);
        intent.putExtra("fee", fee);
        intent.putExtra("dept", dept);
        intent.putExtra("totalCost", totalCost);

        startActivity(intent);
    }
}
