package com.example.charuni.codefest_calculator;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

    private TextView screen;
    private Button btn;
    private String display = "0";
    private String num_1, num_2, operator;
    private double num;
    int flag = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        screen = findViewById(R.id.textView);
        screen.setText(display);
        num_1 = "0";
        num_2 = "0";
        operator = "";
    }

    public void onNumClick(View v) {
        String btn_val;

        if (flag == 1) {
            screen.setText("");
        }

        if (v.getId() == R.id.btn_dot) {
            if (checkDecimal(screen.getText().toString())) {
                return;
            }
        }

        btn = findViewById(v.getId());
        btn_val = btn.getText().toString();
        display = screen.getText().toString();
        screen.setText(display.concat(btn_val));
        flag = 0;
    }

    public void onOpertClick(View v) {

        num_1 = screen.getText().toString();

        btn = findViewById(v.getId());
        operator = btn.getText().toString();
        flag = 1;
    }

    public void onEqualClick(View v) {

        num_2 = screen.getText().toString();

        switch (operator) {
            case "/":
                num = Double.parseDouble(num_1) / Double.parseDouble(num_2);
                break;
            case "*":
                num = Double.parseDouble(num_1) * Double.parseDouble(num_2);
                break;
            case "-":
                num = Double.parseDouble(num_1) - Double.parseDouble(num_2);
                break;
            case "+":
                num = Double.parseDouble(num_1) + Double.parseDouble(num_2);
                break;
            default:
                if(screen.getText().toString().equals(".")){
                    num=0;
                }else {
                    num = Double.parseDouble(screen.getText().toString());
                }
        }

        int scale = 6;
        BigDecimal tempBig = new BigDecimal(Double.toString(num));
        tempBig = tempBig.setScale(scale, BigDecimal.ROUND_HALF_EVEN);
        String strValue = tempBig.stripTrailingZeros().toPlainString();

        if (num == 0) {
            screen.setText("0");
        } else {
            screen.setText(String.valueOf(strValue));
        }

        num_1 = String.valueOf(num);
        flag = 1;
    }

    public void onClearClick(View v) {
        screen.setText("0");
        num_1 = "0";
        num_2 = "0";
        flag = 1;
    }

    private boolean checkDecimal(String s) {
        return (s.contains(".")) && (s.indexOf(".") < s.length());
    }

}
