package com.example.calculator;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.calculator.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btnBang, btnCham, btnPercent, btnTru, btnCong, btnNhan, btnChia, btnAC, btnDel;
    private TextView textViewHistory, textviewResult;
    private String number = null;
    private double giaTriDau = 0, giaTriSau = 0, ketqua = 0;
    boolean operator = false;
    String status = null;
    String pattern = "###, ###.#####";
    DecimalFormat decimalFormat;
    String history, result;
    boolean isEqual = false;
    boolean dot = true;
    boolean del = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn0 = this.findViewById(R.id.btn0);
        btn1 = this.findViewById(R.id.btn1);
        btn2 = this.findViewById(R.id.btn2);
        btn3 = this.findViewById(R.id.btn3);
        btn4 = this.findViewById(R.id.btn4);
        btn5 = this.findViewById(R.id.btn5);
        btn6 = this.findViewById(R.id.btn6);
        btn7 = this.findViewById(R.id.btn7);
        btn8 = this.findViewById(R.id.btn8);
        btn9 = this.findViewById(R.id.btn9);
        btnBang = this.findViewById(R.id.btnBang);
        btnCham = this.findViewById(R.id.btnCham);
        btnCong = this.findViewById(R.id.btnCong);
        btnTru = this.findViewById(R.id.btnTru);
        btnNhan = this.findViewById(R.id.btnNhan);
        btnChia = this.findViewById(R.id.btnChia);
        btnAC = this.findViewById(R.id.btnAC);
        btnDel = this.findViewById(R.id.btnDel);
        btnPercent = this.findViewById(R.id.btnPercent);

        textViewHistory = this.findViewById(R.id.textviewHistory);
        textviewResult = this.findViewById(R.id.textviewResult);

        btn0.setOnClickListener(view -> numberClick("0"));
        btn1.setOnClickListener(view -> numberClick("1"));
        btn2.setOnClickListener(view -> numberClick("2"));
        btn3.setOnClickListener(view -> numberClick("3"));
        btn4.setOnClickListener(view -> numberClick("4"));
        btn5.setOnClickListener(view -> numberClick("5"));
        btn6.setOnClickListener(view -> numberClick("6"));
        btn7.setOnClickListener(view -> numberClick("7"));
        btn8.setOnClickListener(view -> numberClick("8"));
        btn9.setOnClickListener(view -> numberClick("9"));
        btnCham.setOnClickListener(view -> {
            if(dot){
                if(number == null){
                    number = "0.";

                }else{
                    number = number +".";
                }
            }
            dot = false;
            textviewResult.setText(number);
        });

        btnCong.setOnClickListener(view -> {
            if(isEqual){
                history = textViewHistory.getText().toString();
                result = textviewResult.getText().toString();
                textViewHistory.setText(history + "+" );
            }
            else{
                history = textViewHistory.getText().toString();
                result = textviewResult.getText().toString();
                textViewHistory.setText(history + result + "+" );
            }
            if(operator){
                if(status == "nhan"){
                    Nhan();
                }
                else{
                    if(status == "chia"){
                        Chia();
                    }
                    else{
                        if(status == "tru"){
                            Tru();
                        }else{
                            Cong();
                        }
                    }
                }
            }
            operator = false;
            status = "cong";
            number = null;
            isEqual = false;
        });
        btnTru.setOnClickListener(view -> {
            if(isEqual){
                history = String.valueOf(Double.parseDouble(textViewHistory.getText().toString()));
                result = String.valueOf(Double.parseDouble(textviewResult.getText().toString()));
                textViewHistory.setText(history + "-" );
            }
            else{
                history = String.valueOf(Double.parseDouble(textViewHistory.getText().toString()));
                result = String.valueOf(Double.parseDouble(textviewResult.getText().toString()));
                textViewHistory.setText(history + result + "-" );
            }
            if(operator){
                if(status == "cong"){
                    Cong();
                }else{
                    if(status == "nhan"){
                        Nhan();
                    }else{
                        if(status == "chia"){
                            Chia();
                        }else{
                            Tru();
                        }
                    }
                }
            }
            operator = false;
            number = null;
            status = "tru";
            isEqual = false;
        });
        btnNhan.setOnClickListener(view -> {
            if(isEqual){
                history = String.valueOf(Double.parseDouble(textViewHistory.getText().toString()));
                result = String.valueOf(Double.parseDouble(textviewResult.getText().toString()));
                textViewHistory.setText(history + "×" );
            }
            else{
                history = String.valueOf(Double.parseDouble(textViewHistory.getText().toString()));
                result = String.valueOf(Double.parseDouble(textviewResult.getText().toString()));
                textViewHistory.setText(history + result + "×" );
            }
            if(operator){
                if(status == "cong"){
                    Cong();
                }else{
                    if(status == "tru"){
                        Tru();
                    }else{
                        if(status == "chia"){
                            Chia();
                        }else{
                            Nhan();
                        }
                    }
                }
            }
            operator = false;
            number = null;
            status = "nhan";
            isEqual = false;
        });
        btnChia.setOnClickListener(view -> {
            if(isEqual){
                history = String.valueOf(Double.parseDouble(textViewHistory.getText().toString()));
                result = String.valueOf(Double.parseDouble(textviewResult.getText().toString()));
                textViewHistory.setText(history + "÷" );
            }
            else{
                history = String.valueOf(Double.parseDouble(textViewHistory.getText().toString()));
                result = String.valueOf(Double.parseDouble(textviewResult.getText().toString()));
                textViewHistory.setText(history + result + "÷" );
            }
            if(operator){
                if(status == "cong"){
                    Cong();
                }else{
                    if(status == "nhan"){
                        Nhan();
                    }else{
                        if(status == "tru"){
                            Tru();
                        }else{
                            Chia();
                        }
                    }
                }
            }
            operator = false;
            number = null;
            status = "chia";
            isEqual = false;
        });
        btnBang.setOnClickListener(view -> {
            history = String.valueOf(Double.parseDouble(textViewHistory.getText().toString()));
            result = String.valueOf(Double.parseDouble(textviewResult.getText().toString()));
            textViewHistory.setText(history + result );
            if(operator){
                if(status == "cong"){
                    Cong();
                }else{
                    if(status == "nhan"){
                        Nhan();
                    }else{
                        if(status == "tru"){
                            Tru();
                        }else{
                            if(status == "chia"){
                                Chia();
                            }
                            else{
                                ketqua = Double.parseDouble(textviewResult.getText().toString());
                            }
                        }
                    }
                }
            }
            operator = false;
            isEqual = true;
            dot = false;
        });
        btnAC.setOnClickListener(view -> {
            number = null;
            textviewResult.setText("0");
            textViewHistory.setText("");
            giaTriDau = 0;
            giaTriSau = 0;
            dot = true;
            del = true;
        });
        btnDel.setOnClickListener(view -> {
            if(del){
                textviewResult.setText("0");
            }else{
                number = number.substring(0, number.length() -1);
                if(number.length() == 0){
                    btnDel.setClickable(false);
                }else{
                    if(number.contains(".")){
                        dot = false;
                    }else{
                        dot = true;
                    }
                }
            }

            textviewResult.setText(number);
        });



    }
    public void numberClick(String view){
        if(number == null){
            number = view;
        }
        else{
            number = number + view;
        }
        textviewResult.setText(number);
        operator = true;
        del = false;
        btnDel.setClickable(true);
    }
    public void Cong(){
        giaTriSau = Double.parseDouble(textviewResult.getText().toString());
        giaTriDau = giaTriDau + giaTriSau;
        textviewResult.setText(decimalFormat.format(giaTriDau));
        dot = true;
    }
    public void Tru(){
        if(giaTriDau == 0){
            giaTriDau = Double.parseDouble(textviewResult.getText().toString());
        }
        else{
            giaTriSau = Double.parseDouble(textviewResult.getText().toString());
            giaTriDau = giaTriDau - giaTriSau;

        }
        textviewResult.setText(decimalFormat.format(giaTriDau));
        dot = true;
    }
    public void Nhan(){
        if(giaTriDau == 0){
            giaTriDau = 1;
        }
        giaTriSau = Double.parseDouble(textviewResult.getText().toString());
        giaTriDau = giaTriDau*giaTriSau;
        textviewResult.setText(decimalFormat.format(giaTriDau));
        dot = true;

    }
    public void Chia(){
        if(giaTriDau==0){
            giaTriSau = Double.parseDouble(textviewResult.getText().toString());
            giaTriDau = giaTriSau;
        }
        else{
            giaTriSau = Double.parseDouble(textviewResult.getText().toString());
            giaTriDau = giaTriDau/giaTriSau;
        }
        textviewResult.setText(decimalFormat.format(giaTriDau));
        dot = true;
    }
}