package com.mcs.android.p1_custom_hello_toast;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button toastButton;
    Button countButton;
    Button resetCounterButton;
    TextView counterTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toastButton = (Button) findViewById(R.id.toastButton);
        countButton = (Button) findViewById(R.id.countButton);
        counterTextView = (TextView) findViewById(R.id.counterTextView);
        resetCounterButton = (Button) findViewById(R.id.resetCounterButton);

        resetCounter();

        toastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast();
            }
        });

        countButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementCounter();
            }
        });

        resetCounterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetCounter();
            }
        });
    }

    private void resetCounter() {
        counterTextView.setText("0");
    }

    private void incrementCounter() {
        int counterValue = Integer.parseInt(counterTextView.getText().toString());
        counterValue++;
        counterTextView.setText(Integer.toString(counterValue));
    }

    private void showToast() {

        int counterValue = Integer.parseInt(counterTextView.getText().toString());

        Toast toast = Toast.makeText(this, "The current count value is " + counterValue, Toast.LENGTH_LONG);

        View view = toast.getView();
        view.getBackground().setColorFilter(Color.parseColor("#E1FF00"), PorterDuff.Mode.SRC_IN);

        TextView textView = (TextView) view.findViewById(android.R.id.message);
        textView.setTextColor(Color.parseColor("#333333"));

        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 200);
        toast.show();
    }
}