package com.example.currencycoverter;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity
        implements AdapterView.OnItemSelectedListener{
    // GUI objects
    TextView txtMsg;
    Spinner fromSpinner, toSpinner;
    EditText edtFrom, edtTo;
    // options to be offered by the spinner
    String[] fromItems = { "VND", "USD", "EUR", "JPY", "CNY", "THB", "LAK"};
    String[] toItems = { "VND", "USD","EUR", "JPY", "CNY", "THB", "LAK"};

    float[][] rates = new float[7][7];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitializeRateArray();
        txtMsg = (TextView) findViewById(R.id.txtMsg);
        fromSpinner = (Spinner) findViewById(R.id.spinnerFrom);
        toSpinner = (Spinner) findViewById(R.id.spinnerTo);

        edtFrom = (EditText)findViewById(R.id.edtFrom);
        edtTo = (EditText)findViewById(R.id.edtTo);
        // use adapter to bind items array to GUI layout
        ArrayAdapter<String> adapterFrom = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                fromItems);

        ArrayAdapter<String> adapterTo = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                toItems);
        // bind everything together
        fromSpinner.setAdapter(adapterFrom);

        toSpinner.setAdapter(adapterTo);
        // add spinner a listener so user can meake selections by tapping an item
        fromSpinner.setOnItemSelectedListener(this);

        toSpinner.setOnItemSelectedListener(this);

        edtFrom.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s == ""){
                    edtTo.setText(0);
                }
                else{
                    int fromValue = Integer.parseInt(s.toString());

                    int fromCurrency = fromSpinner.getSelectedItemPosition();
                    int toCurrency = toSpinner.getSelectedItemPosition();

                    float toValue = fromValue * rates[fromCurrency][toCurrency];

                    edtTo.setText(String.valueOf(toValue));
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    // next two methods implement the spinner's listener
    @Override
    public void onItemSelected(AdapterView<?> parent, View v, int position,
                               long id) {

    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO do nothing – needed by the interface
    }

    public void InitializeRateArray(){
        for(int i=0; i<7; i++){
            for(int j=0; j<7; j++){
                if(i == j){
                    rates[i][j] = 1;
                }
                else{
                    rates[i][j] =0;
                }
            }
        }
        rates[0][1] = 0.000043f;
        rates[1][0] = 23175.50f;
        rates[0][2] = 0.000036f;
        rates[2][0] = 27400.72f;
        rates[0][3] = 0.000039f;
        rates[3][0] = 25405.21f;

    }
}
