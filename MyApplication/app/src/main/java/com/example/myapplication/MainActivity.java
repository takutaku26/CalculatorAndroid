package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
    TextView textView;
    EditText editText;
    boolean isOperatorKeyPushed;
    int recentOperator = R.id.equals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.formula);
        editText = findViewById(R.id.edit);
        editText.setInputType(InputType.TYPE_NUMBER_VARIATION_NORMAL);
        editText.setText("0");

        //ボタンの宣言
        findViewById(R.id.one).setOnClickListener(buttonNumberListener);
        findViewById(R.id.two).setOnClickListener(buttonNumberListener);
        findViewById(R.id.three).setOnClickListener(buttonNumberListener);
        findViewById(R.id.four).setOnClickListener(buttonNumberListener);
        findViewById(R.id.five).setOnClickListener(buttonNumberListener);
        findViewById(R.id.six).setOnClickListener(buttonNumberListener);
        findViewById(R.id.seven).setOnClickListener(buttonNumberListener);
        findViewById(R.id.eight).setOnClickListener(buttonNumberListener);
        findViewById(R.id.nine).setOnClickListener(buttonNumberListener);
        findViewById(R.id.zero).setOnClickListener(buttonNumberListener);
        findViewById(R.id.plus).setOnClickListener(buttonOperatorListener);
        findViewById(R.id.minus).setOnClickListener(buttonOperatorListener);
        findViewById(R.id.multiply).setOnClickListener(buttonOperatorListener);
        findViewById(R.id.divide).setOnClickListener(buttonOperatorListener);
        findViewById(R.id.equals).setOnClickListener(buttonOperatorListener);
        findViewById(R.id.clear_text).setOnClickListener(buttonListener);
    }
    View.OnClickListener buttonNumberListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {

            Button button = (Button) view;
            String check1 = editText.getText().toString();

            //＋などの記号が押されていたらまたは数字が先頭が0なら0と押したボタンの数字を置き換え
            if (isOperatorKeyPushed == true || check1.equals("0")) {
                editText.setText(button.getText());
            }
            //上記以外なら文字を追加
            else {
                editText.append(button.getText());
            }
            isOperatorKeyPushed = false;
        }
    };
    View.OnClickListener buttonOperatorListener = new View.OnClickListener() {
        double result = 0;
        @Override
        public void onClick(View view) {

            Button operatorButton = (Button) view;
            double value = Double.parseDouble(editText.getText().toString());

            if (recentOperator == R.id.equals) {
                result = value;
            } else {
                result = calc(recentOperator, result, value);//計算するメソッド呼び出し
            }
            editText.setText(String.valueOf(result));
            recentOperator = operatorButton.getId();
            textView.setText(operatorButton.getText());
            isOperatorKeyPushed = true;
        }
    };
    View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            double val = Double.parseDouble(editText.getText().toString());
            Button operatorButton = (Button) view;
            int thisId = operatorButton.getId();
            if (thisId == R.id.clear_text) {
                isOperatorKeyPushed = false;
                recentOperator = R.id.equals;
                textView.setText(" ");
                editText.setText("0");
            }
        }
    };
    //計算するメソッド
    double calc(int operator, double value1, double value2) {
        if (operator == R.id.plus) {
            return value1 + value2;
        } else if (operator == R.id.minus) {
            return value1 - value2;
        }else if (operator == R.id.multiply) {
            return value1 * value2;
        }else if (operator == R.id.divide) {
            return value1 / value2;
        }else {
            return value1;
        }
    }

}