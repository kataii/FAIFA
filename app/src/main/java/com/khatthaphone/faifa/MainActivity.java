package com.khatthaphone.faifa;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    public static int powerUsedValue, multiplier, lowPowerUsedValue, normalPowerUsedValue, highPowerUsedValue, counterPosition;
    public static double actualCost, totalCost, lowValueCost, normalValueCost, highValueCost, fee, dept, counterPrice;
    public static Bundle bundle = new Bundle();
    Spinner counterType;
    TextView powerUsed;
    EditText lastValue, currentValue, eDept, eMultiplier;
    Button calculate;
    double lowValuePrice = 348;
    double normalValuePrice = 414;
    double highValuePrice = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.mytoolbar);
        setSupportActionBar(myToolbar);

        lastValue = (EditText) findViewById(R.id.lastValue);
        currentValue = (EditText) findViewById(R.id.currentValue);
        powerUsed = (TextView) findViewById(R.id.powerUsed);
        eDept = (EditText) findViewById(R.id.dept);
        counterType = (Spinner) findViewById(R.id.counterType);
        eMultiplier = (EditText) findViewById(R.id.multiplier);
        multiplier = 1;
        eMultiplier.setText("" + multiplier);
        calculate = (Button) findViewById(R.id.calculate);

        powerUsed.setFocusable(false);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.counterType, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        counterType.setAdapter(adapter);

        valueSync();

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valueSync2();
                powerUsed.setText("" + powerUsedValue);
                powerUsedError(powerUsedValue);

                if (powerUsedError(powerUsedValue)) {
                    powerUsedError();
                } else {
                    if (powerUsedValue <= 25) {
                        lowPowerUsedValue = powerUsedValue;
                        lowValueCost = powerUsedValue * lowValuePrice;
                        actualCost = lowValueCost;
                    }

                    if (powerUsedValue >= 26 && powerUsedValue <= 150) {
                        lowPowerUsedValue = 25;
                        lowValueCost = lowPowerUsedValue * lowValuePrice;
                        normalPowerUsedValue = powerUsedValue - lowPowerUsedValue;
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
                    showDialog(counterPrice, actualCost, fee, dept, totalCost, multiplier);
                    startViewDetailsActivity();
                }
            }
        });

/*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.next);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                valueSync2();
                powerUsed.setText("" + powerUsedValue);
                powerUsedError(powerUsedValue);

                if (powerUsedError(powerUsedValue)) {
                    powerUsedError();
                } else {
                    if (powerUsedValue <= 25) {
                        lowPowerUsedValue = powerUsedValue;
                        lowValueCost = powerUsedValue * lowValuePrice;
                        actualCost = lowValueCost;
                    }

                    if (powerUsedValue >= 26 && powerUsedValue <= 150) {
                        lowPowerUsedValue = 25;
                        lowValueCost = lowPowerUsedValue * lowValuePrice;
                        normalPowerUsedValue = powerUsedValue - lowPowerUsedValue;
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
                    showDialog(counterPrice, actualCost, fee, dept, totalCost, multiplier);
                    startViewDetailsActivity();
                }
            }
        });*/
    }

    private void powerUsedError() {
        Dialog d = new Dialog(this);
        d.setTitle("ເກີດຂໍ້ຜິດພາດ");
        TextView tv = new TextView(this);
        tv.setText("ເລັກຈົດຄັ້ງນີ້ຕ້ອງຫຼາຍກວ່າເລກຈົດຄັ້ງກ່ອນ!");
        tv.setPadding(20, 20, 20, 20);
        d.setContentView(tv);
        d.show();
        d.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                lastValue.requestFocus();
            }
        });
    }

    private boolean powerUsedError(int powerUsedValue) {
        if (powerUsedValue < 0) {
            return true;
        } else {
            return false;
        }
    }

    private void valueSync2() {
        try {
            multiplier = Integer.parseInt(eMultiplier.getText().toString());
            powerUsedValue = ((Integer.parseInt(currentValue.getText().toString())) - Integer.parseInt(lastValue.getText().toString())) * multiplier;
            Log.i("MainActivity", "" + powerUsedValue);
        } catch (Exception e) {

        } finally {
            if (powerUsedValue > 0) {
                powerUsed.setText("" + powerUsedValue);
            }
        }
    }

    private void valueSync() {

        lastValue.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    try {
                        powerUsedValue = (Integer.parseInt(currentValue.getText().toString())) - Integer.parseInt(lastValue.getText().toString());
                        Log.i("MainActivity", " " + powerUsedValue);
                    } catch (Exception e) {

                    } finally {
//                        if (powerUsedValue > 0) {
                        powerUsed.setText("" + powerUsedValue);
//                        }
                    }
                }
            }
        });
        currentValue.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    try {
                        powerUsedValue = (Integer.parseInt(currentValue.getText().toString())) - Integer.parseInt(lastValue.getText().toString());
                        Log.i("MainActivity", " " + powerUsedValue);
                    } catch (Exception e) {

                    } finally {
//                        if (powerUsedValue > 0) {
                        powerUsed.setText("" + powerUsedValue);
//                        }
                    }
                }
            }
        });

        eDept.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    try {
                        powerUsedValue = (Integer.parseInt(currentValue.getText().toString())) - Integer.parseInt(lastValue.getText().toString());
                        Log.i("MainActivity", " " + powerUsedValue);
                    } catch (Exception e) {

                    } finally {
//                        if (powerUsedValue > 0) {
                        powerUsed.setText("" + powerUsedValue);
//                        }
                    }
                }
            }
        });
        eMultiplier.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    try {
                        powerUsedValue = (Integer.parseInt(currentValue.getText().toString())) - Integer.parseInt(lastValue.getText().toString());
                        Log.i("MainActivity", " " + powerUsedValue);
                    } catch (Exception e) {

                    } finally {
//                        if (powerUsedValue > 0) {
                        powerUsed.setText("" + powerUsedValue);
//                        }
                    }
                }
            }
        });
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

    private void showDialog(double counterPrice, double actualCost, double fee, double dept, double totalCost, int multiplier) {
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

    private static String formatNum(String pattern, double num) {
        DecimalFormat format = new DecimalFormat(pattern);
        String output = format.format(num);
        return output;
    }

    private static String formatNum(String pattern, int num) {
        DecimalFormat format = new DecimalFormat(pattern);
        String output = format.format(num);
        return output;
    }

    public void startViewDetailsActivity() {
        Intent intent = new Intent(this, ViewDetails.class);

//        powerUsed, lowPowerUsedValue, normalPowerUsedValue, highPowerUsedValue, lowValueCost, normalValueCost, highValueCost, actualCost, counterPrice, fee, dept, totalCost;
        bundle.putString("powerUsedValue", "" + formatNum("###,###", powerUsedValue));

        bundle.putString("multiplier", "" + multiplier);

        bundle.putString("lowPowerUsedValue", "" + formatNum("###,###", lowPowerUsedValue));

        bundle.putString("normalPowerUsedValue", "" + formatNum("###,###", normalPowerUsedValue));

        int hpuv = (int) highPowerUsedValue;
        bundle.putString("highPowerUsedValue", "" + formatNum("###,###", hpuv));

        int lvc = (int) lowValueCost;
        bundle.putString("lowValueCost", "" + formatNum("###,###", lvc));

        int nvc = (int) normalValueCost;
        bundle.putString("normalValueCost", "" + formatNum("###,###", nvc));

        int hvc = (int) highValueCost;
        bundle.putString("highValueCost", "" + formatNum("###,###", hvc));

        int ac = (int) actualCost;
        bundle.putString("actualCost", "" + formatNum("###,###", ac));

        int cp = (int) counterPrice;
        bundle.putString("counterPrice", "" + formatNum("###,###", cp));

        bundle.putString("fee", "" + formatNum("###,###.##", fee));

        int d = (int) dept;
        bundle.putString("dept", "" + formatNum("###,###", d));

        bundle.putString("totalCost", "" + formatNum("###,###.##", totalCost));

        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        powerUsedValue = 0;
        lowPowerUsedValue = 0;
        normalPowerUsedValue = 0;
        highPowerUsedValue = 0;
        lowValueCost = 0;
        normalValueCost = 0;
        highValueCost = 0;
        counterPrice = 0;
        fee = 0;
        dept = 0;
        totalCost = 0;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        int currentValue = 0;
        int lastValue = 0;

        int c = 0;
        int l = 0;
        try {
            c = Integer.parseInt(this.currentValue.getText().toString());
            l = Integer.parseInt(this.lastValue.getText().toString());
        } catch (Exception e) {

        } finally {
            if (c > 0 && l > 0) {
                currentValue = c;
                lastValue = l;
            }
        }

        powerUsedValue = currentValue - lastValue;
        counterPrice = getCounterPriceByType();
        dept = TryGetDept();
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
