package com.giovanni.example.primefinderui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText et;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (EditText)findViewById(R.id.etInput);
        tv = (TextView)findViewById(R.id.tvResult);
    }

    public void DeployCheckPrime (View v) {
        long lgnNum;
        String number = et.getText().toString();
        tv.setText("Checking Wait");
        try {
            lgnNum = Long.parseLong(number);
        } catch (Exception e) {
            tv.setText("Invalid Number");
            return;
        }
        tv.setText(isPrime(lgnNum) ? "Is Prime" : "Is not Prime");
    }

    private boolean isPrime (long n) {
        if (n %2 == 0)
            return false;
        if (n%3 == 0)
            return false;

        long last = (long)Math.sqrt(n);
        for (long i = 5; i < last; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }
}