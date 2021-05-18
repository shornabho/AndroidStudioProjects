package com.vogella.android.testapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String TAG = "MainActivity";
    TextView textView;
    Button showDate,submitButton, showLocation, dialPhone, checkOutWebsite, callSohinee, callAnotherActivity, viewIntent, letsGetPro;
    RadioGroup radioGroupYearOfStudy;
    RadioButton radioButtonYearOfStudy;
    EditText nameField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_linear);

        textView = (TextView)findViewById(R.id.textView4);
        showDate = (Button) findViewById(R.id.showDate);
        submitButton = (Button) findViewById(R.id.submitButton);
        radioGroupYearOfStudy = (RadioGroup) findViewById(R.id.radioGroupYearOfStudy);
        nameField = (EditText) findViewById(R.id.nameField);
        showLocation = (Button) findViewById(R.id.showLocation);
        dialPhone = (Button) findViewById(R.id.dialPhone);
        checkOutWebsite = (Button) findViewById(R.id.checkOutWebsite);
        callSohinee = (Button) findViewById(R.id.callSohinee);
        callAnotherActivity = (Button) findViewById(R.id.callAnotherActivity);
        viewIntent = (Button) findViewById(R.id.viewIntent);
        letsGetPro = (Button) findViewById(R.id.letsGetPro);


        showDate.setOnClickListener(this);
        submitButton.setOnClickListener(this);
        showLocation.setOnClickListener(this);
        dialPhone.setOnClickListener(this);
        checkOutWebsite.setOnClickListener(this);
        callSohinee.setOnClickListener(this);
        callAnotherActivity.setOnClickListener(this);
        viewIntent.setOnClickListener(this);
        letsGetPro.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart(){
        super.onRestart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onClick(View view) {
        String showText;
        switch(view.getId()){
            case R.id.showDate:
                Date date = new Date();
                showText = "Today is " + date.toString() + "!";

                Toast.makeText(this, showText, Toast.LENGTH_SHORT).show();
                break;
            case R.id.submitButton:
                int selectedId = radioGroupYearOfStudy.getCheckedRadioButtonId();
                radioButtonYearOfStudy = (RadioButton)findViewById(selectedId);
                String name = nameField.getText().toString();

                if(selectedId == -1 || name.isEmpty()){
                    Toast.makeText(this,"Please fill out the required fields!",Toast.LENGTH_SHORT).show();

                }
                else{
                    showText = "Name: "+name+"\nYear of Study: "+radioButtonYearOfStudy.getText().toString();
                    textView.setText(showText);
                    Toast.makeText(this, "Form submitted!", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.showLocation:
                Intent showLocationIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:22.5578858,88.3489381,17"));
                startActivity(showLocationIntent);
                break;
            case R.id.dialPhone:
                Intent dialPhoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+919874482004"));
                startActivity(dialPhoneIntent);
                break;
            case R.id.checkOutWebsite:
                Intent checkOutWebsiteIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.photoscenethesis.com/brand-strategy/"));
                startActivity(checkOutWebsiteIntent);
                break;
            case R.id.callSohinee:
                Intent callSohineeIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:+919038415988"));
                startActivity(callSohineeIntent);
                break;
            case R.id.callAnotherActivity:
                Intent callAnotherActivityIntent = new Intent(this, otherActivity.class);
                startActivity(callAnotherActivityIntent);
                break;
            case R.id.viewIntent:
                Intent viewIntentIntent = new Intent(Intent.ACTION_VIEW);
                startActivity(viewIntentIntent);
                break;
            case R.id.letsGetPro:
                Intent letsGetProIntent = new Intent(Intent.ACTION_VIEW);
                letsGetProIntent.putExtra("KEY","Sent from main activity!");
                startActivity(letsGetProIntent);
                break;
        }
    }
}
