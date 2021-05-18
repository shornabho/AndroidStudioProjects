package com.mcs.android.inputcontrols;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ThankYouActivity extends AppCompatActivity {

    private TextView tvName, tvEmail, tvCountry, tvGender, tvRating, tvTnC;
    private Button btnDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thank_you);

        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");
        String email = bundle.getString("email");
        String sex = bundle.getString("sex");
        String country = bundle.getString("country");
        float rating = bundle.getFloat("rating");
        boolean termsAndConditions = bundle.getBoolean("termsAndConditions");


        tvName = (TextView) findViewById(R.id.tvName);
        tvCountry = (TextView) findViewById(R.id.tvCountry);
        tvEmail = (TextView) findViewById(R.id.tvEmail);
        tvGender = (TextView) findViewById(R.id.tvGender);
        tvRating = (TextView) findViewById(R.id.tvRating);
        tvTnC = (TextView) findViewById(R.id.tvTnC);
        btnDone = (Button) findViewById(R.id.btnDone);

        tvName.setText(name);
        tvEmail.setText(email);
        tvGender.setText(sex);
        tvCountry.setText(country);
        tvRating.setText(Float.toString(rating));
        tvTnC.setText(Boolean.toString(termsAndConditions));

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}