package com.mcs.android.calculatex;

import android.util.Log;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Stack;
import java.util.StringTokenizer;

public class LexicalAnalyser {
    private static Stack<String> operatorStack = new Stack<String>();
    private static Stack<BigDecimal> operandStack = new Stack<BigDecimal>();


    private static String[] convertInfixToPostfix(String infixExpression) {
        String[] tokenisedExpression = tokeniseExpression(infixExpression);
        String postfixExpression[] = new String[tokenisedExpression.length];

        int pos = 0;

        for (String token : tokenisedExpression) {
            if (isOperator(token)) {
                if (!operatorStack.empty() && getPriorityValue(token) <= getPriorityValue(operatorStack.peek())) {
                    postfixExpression[pos++] = operatorStack.pop();
                }
                operatorStack.push(token);
            }
            else {
                postfixExpression[pos++] = token;
            }
        }

        while (!operatorStack.empty()) {
            postfixExpression[pos++] = operatorStack.pop();
        }

        for (String token : postfixExpression) {
            Log.i("token", token);
        }

        return postfixExpression;
    }

    public static BigDecimal evaluatePostfixExpression(String infixExpression) {
        String postfixExpression[] = convertInfixToPostfix(infixExpression);

        for (String token : postfixExpression) {
            if (isOperator(token)) {
                 BigDecimal b = operandStack.pop();
                 BigDecimal a = operandStack.pop();

                 BigDecimal result = new BigDecimal("0");

                 switch (token) {
                     case "÷" : result = a.divide(b, 8, RoundingMode.HALF_UP);
                                break;
                     case "×" : result = a.multiply(b);
                                break;
                     case "+" : result = a.add(b);
                                break;
                     case "–" : result = a.subtract(b);
                                break;
                 }

                 operandStack.push(result);
            }
            else {
                operandStack.push(new BigDecimal(token));
            }
        }

        return operandStack.pop();
    }

    public static String[] tokeniseExpression(String infixExpression) {
        StringTokenizer st = new StringTokenizer(infixExpression, "÷×+–", true);
        String tokens[] = new String[st.countTokens()];

        for (int i = 0; i < tokens.length; i++) {
            tokens[i] = st.nextToken();
        }

        return tokens;
    }

    private static int getPriorityValue(String operator) {

        switch (operator) {

            case "÷":
            case "×": return 2;

            case "+":
            case "–": return 1;

            default: return -1;
        }
    }

    public static boolean isOperator(String token) {
        if (token != null && token.length() == 1) {
            char charToken = token.charAt(0);
            if (charToken == '÷' || charToken == '×' || charToken == '+' || charToken == '–'){
                return true;
            }
            return false;
        }
        return false;
    }
}
