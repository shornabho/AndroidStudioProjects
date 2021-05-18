package com.mcs.android.calculatex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;

    private Button clearButton;
    private Button deleteButton;
    private Button percentageButton;
    private Button divideButton;
    private Button multiplyButton;
    private Button minusButton;
    private Button plusButton;
    private Button equalsButton;
    private Button decimalButton;

    private Button digitButtons[] = new Button[10];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = (TextView) findViewById(R.id.resultTextView);

        clearButton = (Button) findViewById(R.id.clearButton);
        deleteButton = (Button) findViewById(R.id.deleteButton);
        percentageButton = (Button) findViewById(R.id.percentageButton);
        divideButton = (Button) findViewById(R.id.divideButton);
        multiplyButton = (Button) findViewById(R.id.multiplyButton);
        minusButton = (Button) findViewById(R.id.minusButton);
        plusButton = (Button) findViewById(R.id.plusButton);
        equalsButton = (Button) findViewById(R.id.equalsButton);
        decimalButton = (Button) findViewById(R.id.decimalButton);

        digitButtons[0] = (Button) findViewById(R.id.digit0Button);
        digitButtons[1] = (Button) findViewById(R.id.digit1Button);
        digitButtons[2] = (Button) findViewById(R.id.digit2Button);
        digitButtons[3] = (Button) findViewById(R.id.digit3Button);
        digitButtons[4] = (Button) findViewById(R.id.digit4Button);
        digitButtons[5] = (Button) findViewById(R.id.digit5Button);
        digitButtons[6] = (Button) findViewById(R.id.digit6Button);
        digitButtons[7] = (Button) findViewById(R.id.digit7Button);
        digitButtons[8] = (Button) findViewById(R.id.digit8Button);
        digitButtons[9] = (Button) findViewById(R.id.digit9Button);

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultTextView.setText("0");
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentOutput = resultTextView.getText().toString();
                if (currentOutput != null && currentOutput.length() > 1) {
                    resultTextView.setText(currentOutput.substring(0, currentOutput.length() - 1));
                }
                else {
                    resultTextView.setText("0");
                }
            }
        });

        deleteButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                resultTextView.setText("0");
                return false;
            }
        });

        divideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentOutput = resultTextView.getText().toString();
                char lastCharacter = currentOutput.charAt(currentOutput.length() - 1);
                boolean isLastCharacterOperator = lastCharacter == '+' || lastCharacter == '–' || lastCharacter == '×' || lastCharacter == '÷';
                if (currentOutput != null && currentOutput.length() > 0 && !isLastCharacterOperator && currentOutput != "0")
                    resultTextView.setText(resultTextView.getText().toString() + "÷");
            }
        });

        multiplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentOutput = resultTextView.getText().toString();
                char lastCharacter = currentOutput.charAt(currentOutput.length() - 1);
                boolean isLastCharacterOperator = lastCharacter == '+' || lastCharacter == '–' || lastCharacter == '×' || lastCharacter == '÷';
                if (currentOutput != null && currentOutput.length() > 0 && !isLastCharacterOperator && currentOutput != "0")
                    resultTextView.setText(resultTextView.getText().toString() + "×");
            }
        });

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentOutput = resultTextView.getText().toString();
                char lastCharacter = currentOutput.charAt(currentOutput.length() - 1);
                boolean isLastCharacterOperator = lastCharacter == '+' || lastCharacter == '–' || lastCharacter == '×' || lastCharacter == '÷';
                if (currentOutput != null && currentOutput.length() > 0 && !isLastCharacterOperator)
                    resultTextView.setText(resultTextView.getText().toString() + "–");

            }
        });


        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentOutput = resultTextView.getText().toString();
                char lastCharacter = currentOutput.charAt(currentOutput.length() - 1);
                boolean isLastCharacterOperator = lastCharacter == '+' || lastCharacter == '–' || lastCharacter == '×' || lastCharacter == '÷';
                if (currentOutput != null && currentOutput.length() > 0 && !isLastCharacterOperator && currentOutput != "0")
                    resultTextView.setText(resultTextView.getText().toString() + "+");
            }
        });


        for (int i = 0; i < digitButtons.length; i++) {
            String finalI = Integer.toString(i);
            digitButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String currentOutput = resultTextView.getText().toString();
                    if (currentOutput.equals("0")) {
                        resultTextView.setText(finalI);
                    }
                    else {
                        resultTextView.setText(currentOutput + finalI);
                    }
                }
            });
        }

        equalsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentOutput = resultTextView.getText().toString();
                String lastCharacter = currentOutput.substring(currentOutput.length() - 1);

                if (!LexicalAnalyser.isOperator(lastCharacter)) {
                    BigDecimal evaluatedResult = LexicalAnalyser.evaluatePostfixExpression(currentOutput);

                    evaluatedResult = evaluatedResult.setScale(5, RoundingMode.HALF_UP).stripTrailingZeros();

                    resultTextView.setText(evaluatedResult.toString());
                }
            }
        });

        decimalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentOutput = resultTextView.getText().toString();
                String tokenisedExpression[] = LexicalAnalyser.tokeniseExpression(currentOutput);

                String lastToken = tokenisedExpression[tokenisedExpression.length - 1];

                if (currentOutput != null && !LexicalAnalyser.isOperator(lastToken) && !lastToken.contains(".")) {
                    resultTextView.setText(currentOutput + ".");
                }
            }
        });

        percentageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentOutput = resultTextView.getText().toString();

                String tokenisedExpression[] = LexicalAnalyser.tokeniseExpression(currentOutput);

                String lastToken = tokenisedExpression[tokenisedExpression.length - 1];

                if (currentOutput != null && !LexicalAnalyser.isOperator(lastToken)) {
                    BigDecimal lastNumber = new BigDecimal(lastToken);

                    if (!lastNumber.equals(new BigDecimal(0)))
                        tokenisedExpression[tokenisedExpression.length - 1] = lastNumber.divide(new BigDecimal(100)).toString();
                }

                String resultExpression = "";

                for (String token : tokenisedExpression) {
                    resultExpression += token;
                }

                resultTextView.setText(resultExpression);
            }
        });



    }
}
