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

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    public static int usedValue, multiplier, value1, value2, value3, value4, value5, value6, counterPosition;
    public static double actualCost, totalCost, cost1, cost2, cost3, cost4, cost5, cost6, fee, dept, counterPrice;
    public static Bundle bundle = new Bundle();
    Spinner counterType;
    TextView powerUsed;
    EditText lastValue, currentValue, eDept, eMultiplier;
    double price1 = 348;
    double price2 = 414;
    double price3 = 799;
    double price4 = 880;
    double price5 = 965;
    double price6 = 999;

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
        eMultiplier = (EditText) findViewById(R.id.multiplier); multiplier = 1; eMultiplier.setText("" + multiplier);

        powerUsed.setFocusable(false);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.counterType, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        counterType.setAdapter(adapter);

        valueSync();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.next);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                valueSync2();
                powerUsed.setText("" + usedValue);
                powerUsedError(usedValue);

                if (powerUsedError(usedValue)) {
                    powerUsedError();
                } else {

                    int mode = 0;

                    if (usedValue <= 25) {
                        mode = 1;
                    } else if (usedValue >= 26 && usedValue <= 150) {
                        mode = 2;
                    } else if (usedValue >= 151 && usedValue <= 300) {
                        mode = 3;
                    } else if (usedValue >= 301 && usedValue <= 400) {
                        mode = 4;
                    } else if (usedValue >= 401 && usedValue <= 500) {
                        mode = 5;
                    } else if (usedValue > 500) {
                        mode = 6;
                    }

                    switch (mode) {
                        case 1:
                            /*lowPowerUsedValue = usedValue;
                            lowValueCost = usedValue * lowValuePrice;
                            actualCost = lowValueCost;*/
                            value1 = usedValue;
                            cost1 = cost1 * price1;

                            actualCost = cost1;
                            break;
                        case 2:
                            value1 = 25;
                            cost1 = cost1 * price1;

                            value2 = usedValue - value1;
                            cost2 = value2 * price2;

                            actualCost = cost1 + cost2;
                            break;
                        case 3:
                            value1 = 25;
                            cost1 = cost1 * price1;

                            value2 = 150 - value1;
                            cost2 = value2 * price2;

                            value3 = usedValue - 150;
                            cost3 = value3 * price3;

                            actualCost = cost1 + cost2 + cost3;
                            break;
                        case 4:
                            value1 = 25;
                            cost1 = cost1 * price1;

                            value2 = 150 - value1;
                            cost2 = value2 * price2;

                            value3 = usedValue - 150;
                            cost3 = value3 * price3;

                            value4 = usedValue - 300;
                            cost4 = value4 * price4;

                            actualCost = cost1 + cost2 + cost3 + cost4;
                            break;
                        case 5:
                            value1 = 25;
                            cost1 = cost1 * price1;

                            value2 = 150 - value1;
                            cost2 = value2 * price2;

                            value3 = usedValue - 150;
                            cost3 = value3 * price3;

                            value4 = usedValue - 300;
                            cost4 = value4 * price4;

                            value5 = usedValue - 400;
                            cost5 = value5 * price5;

                            actualCost = cost1 + cost2 + cost3 + cost4 + cost5;
                            break;
                        case 6:
                            value1 = 25;
                            cost1 = cost1 * price1;

                            value2 = 150 - value1;
                            cost2 = value2 * price2;

                            value3 = usedValue - 150;
                            cost3 = value3 * price3;

                            value4 = usedValue - 300;
                            cost4 = value4 * price4;

                            value5 = usedValue - 400;
                            cost5 = value5 * price5;

                            value6 = usedValue - 400;
                            cost6 = value6 * price6;

                            actualCost = cost1 + cost2 + cost3 + cost4 + cost5 + cost6;
                            break;
                        default:
                            powerUsedError();
                    }


                    /*if (usedValue <= 25) {
                        lowPowerUsedValue = usedValue;
                        lowValueCost = usedValue * lowValuePrice;
                        actualCost = lowValueCost;
                    }

                    if (usedValue >= 26 && usedValue <= 150) {
                        lowPowerUsedValue = 25;
                        lowValueCost = lowPowerUsedValue * lowValuePrice;
                        normalPowerUsedValue = usedValue - lowPowerUsedValue;
                        normalValueCost = (usedValue - lowPowerUsedValue) * normalValuePrice;
                        actualCost = lowValueCost + normalValueCost;
                    }

                    if (usedValue > 150) {
                        lowPowerUsedValue = 25;
                        lowValueCost = lowPowerUsedValue * lowValuePrice;
                        normalPowerUsedValue = (150 - lowPowerUsedValue);
                        normalValueCost = normalPowerUsedValue * normalValuePrice;
                        highPowerUsedValue = usedValue - 150;
                        highValueCost = highPowerUsedValue * highValuePrice;
                        actualCost = lowValueCost + normalValueCost + highValueCost;
                    }*/

                    dept = TryGetDept();
                    counterPrice = getCounterPriceByType();
                    fee = (counterPrice + actualCost) * 0.1;
                    totalCost = actualCost + fee + counterPrice + dept;
//                    showDialog(counterPrice, actualCost, fee, dept, totalCost, multiplier);
                    startViewDetailsActivity();
                }
            }
        });
    }

    private void powerUsedError() {
        Dialog d = new Dialog(this);
        d.setTitle("ເກີດຂໍ້ຜິດພາດ");
        TextView tv = new TextView(this);
        tv.setText("ກະລຸນາກວດສອບເລກທີ່ຈົດໃຫ້ຖືກຕ້ອງ");
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
        }
        else {
            return false;
        }
    }

    private void valueSync2() {
        try {
            multiplier = Integer.parseInt(eMultiplier.getText().toString());
            usedValue = ((Integer.parseInt(currentValue.getText().toString())) - Integer.parseInt(lastValue.getText().toString()))*multiplier;
            Log.i("MainActivity", "" + usedValue);
        } catch (Exception e) {

        } finally {
            if (usedValue > 0) {
                powerUsed.setText("" + usedValue);
            }
        }
    }

    private void valueSync() {

        lastValue.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (!b) {
                    try {
                        usedValue = (Integer.parseInt(currentValue.getText().toString())) - Integer.parseInt(lastValue.getText().toString());
                        Log.i("MainActivity", " " + usedValue);
                    } catch (Exception e) {

                    } finally {
//                        if (usedValue > 0) {
                            powerUsed.setText("" + usedValue);
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
                        usedValue = (Integer.parseInt(currentValue.getText().toString())) - Integer.parseInt(lastValue.getText().toString());
                        Log.i("MainActivity", " " + usedValue);
                    } catch (Exception e) {

                    } finally {
//                        if (usedValue > 0) {
                            powerUsed.setText("" + usedValue);
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
                        usedValue = (Integer.parseInt(currentValue.getText().toString())) - Integer.parseInt(lastValue.getText().toString());
                        Log.i("MainActivity", " " + usedValue);
                    } catch (Exception e) {

                    } finally {
//                        if (usedValue > 0) {
                            powerUsed.setText("" + usedValue);
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
                        usedValue = (Integer.parseInt(currentValue.getText().toString())) - Integer.parseInt(lastValue.getText().toString());
                        Log.i("MainActivity", " " + usedValue);
                    } catch (Exception e) {

                    } finally {
//                        if (usedValue > 0) {
                            powerUsed.setText("" + usedValue);
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
        tv.setText("Power " + value1 + "kW price: " + cost1 + "\n"
                + "Power " + value2 + "kW: " + cost2 + "\n"
                + "Power " + value3 + "kW: " + cost3 + "\n"
                + "Power " + value4 + "kW: " + cost4 + "\n"
                + "Power " + value5 + "kW: " + cost5 + "\n"
                + "Power " + value6 + "kW: " + cost6 + "\n"
                + "Actual price: " + actualCost + "\n"
                + "Fee: " + fee + "\n" + "Counter Price: "
                + counterPrice + "\n"
                + "Dept: " + dept + "\n"
                + "Total: " + totalCost);
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
        bundle.putString("usedValue", "" + formatNum("###,###", usedValue));

        bundle.putString("multiplier", "" + multiplier);

        bundle.putString("value1", "" + formatNum("###,###", value1));

        bundle.putString("value2", "" + formatNum("###,###", value2));

        bundle.putString("value3", "" + formatNum("###,###", value3));

        bundle.putString("value4", "" + formatNum("###,###", value4));

        bundle.putString("value5", "" + formatNum("###,###", value5));

        bundle.putString("value6", "" + formatNum("###,###", value6));

        int mCost1 = (int) cost1;
        bundle.putString("lowValueCost", "" + formatNum("###,###", mCost1));

        int mCost2 = (int) cost2;
        bundle.putString("lowValueCost", "" + formatNum("###,###", mCost2));

        int mCost3 = (int) cost3;
        bundle.putString("lowValueCost", "" + formatNum("###,###", mCost3));

        int mCost4 = (int) cost4;
        bundle.putString("lowValueCost", "" + formatNum("###,###", mCost4));

        int mCost5 = (int) cost5;
        bundle.putString("lowValueCost", "" + formatNum("###,###", mCost5));

        int mCost6 = (int) cost6;
        bundle.putString("lowValueCost", "" + formatNum("###,###", mCost6));

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

        usedValue = 0;
        value1 = 0;
        value2 = 0;
        value3 = 0;
        value4 = 0;
        value5 = 0;
        value6 = 0;
        cost1 = 0;
        cost2 = 0;
        cost3 = 0;
        cost4 = 0;
        cost5 = 0;
        cost6 = 0;
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

        usedValue = currentValue - lastValue;
        counterPrice = getCounterPriceByType();
        dept = TryGetDept();
    }

    /*
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("currentValue", "" + currentValue);
        outState.putString("lastValue", "" + lastValue);
        outState.putString("usedValue", "" + usedValue);
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
        String usedValue = inState.getString("usedValue");
        powerUsed.setText(usedValue);
        String dept = inState.getString("dept");
        eDept.setText(dept);
        int counterType = inState.getInt("counterType");
        this.counterType.setSelection(counterType);

    }*/

}
