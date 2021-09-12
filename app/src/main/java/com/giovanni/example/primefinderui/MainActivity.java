package com.giovanni.example.primefinderui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends AppCompatActivity {
    EditText et;
    TextView tv;
    boolean done = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (EditText)findViewById(R.id.etInput);
        tv = (TextView)findViewById(R.id.tvResult);
    }

    public void DeployCheckPrime (View v) {
        long lgnNum;
        ((Button)v).setEnabled(false);
        String number = et.getText().toString();
        et.setEnabled(false);

        try {
            lgnNum = Long.parseLong(number);
        } catch (Exception e) {
            tv.setText("Invalid Number");
            ((Button)v).setEnabled(true);
            return;
        }

        AtomicBoolean resp = new AtomicBoolean(false);
        Thread t = new Thread(() -> resp.set(isPrime(lgnNum)));
        new Thread() {
            public void run() {
                int i = 0;
                while(t.getState() != State.TERMINATED) {
                    String base = "Checking ";
                    String var[] = {".  ", " . ", "  ."};
                    int finalI = i;
                    runOnUiThread(() -> tv.setText(base + var[finalI]));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    i = ( i + 1) % 3;
                }
                runOnUiThread(() -> {
                    tv.setText(resp.get() ? "Is Prime" : "Is not Prime");
                    ((Button)v).setEnabled(true);
                    et.setEnabled(true);
                });
            }
        }.start();
        t.start();
    }

    private boolean isPrime (long n) {
        if (n == 1)
            return false;
        if (n <= 3)
            return true;
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