package com.edsusantoo.bismillah.barvolume;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtWidth, edtHeight, edtLength;
    Button btnCalculate;
    TextView tvResult;

    private static final String STATE_RESULT = "state_result";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtWidth = findViewById(R.id.edt_width);
        edtHeight = findViewById(R.id.edt_height);
        edtLength = findViewById(R.id.edt_length);
        btnCalculate = findViewById(R.id.btn_calculate);
        tvResult = findViewById(R.id.tv_result);

        btnCalculate.setOnClickListener(this);

        //ini mengecek apakah di state terdapat data dari method onSaveInstanceState, jika ada maka tampilkan
        if(savedInstanceState!=null){
            String result = savedInstanceState.getString(STATE_RESULT);
            tvResult.setText(result);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_calculate:
                String inputLength = edtLength.getText().toString().trim();
                String inputHeight = edtHeight.getText().toString().trim();
                String inputWidth = edtWidth.getText().toString().trim();

                boolean isEmptyFields = false;
                boolean isInvalidDouble = false;

                if (TextUtils.isEmpty(inputLength)) {
                    isEmptyFields = true;
                    edtLength.setError("Field ini tidak boleh kosong");
                }

                if (TextUtils.isEmpty(inputHeight)) {
                    isEmptyFields = true;
                    edtHeight.setError("Field ini tidak boleh kosong");
                }

                if (TextUtils.isEmpty(inputWidth)) {
                    isEmptyFields = true;
                    edtWidth.setError("Field ini tidak boleh kosong");
                }

                Double lenght = toDouble(inputLength);
                Double heigth = toDouble(inputHeight);
                Double widht = toDouble(inputWidth);

                if (lenght == null) {
                    isInvalidDouble = true;
                    edtLength.setError("Field ini harus berupa nomor yang valid");
                }
                if (widht == null) {
                    isInvalidDouble = true;
                    edtWidth.setError("Field ini harus berupa nomor yang valid");
                }
                if (heigth == null) {
                    isInvalidDouble = true;
                    edtHeight.setError("Field ini harus berupa nomor yang valid");
                }

                if (!isEmptyFields && !isInvalidDouble) {
                    double volume = lenght * widht * heigth;
                    tvResult.setText(String.valueOf(volume));
                }

                break;
        }
    }

    Double toDouble(String s) {
        try {
            return Double.valueOf(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }


    //ini untuk mengatasi jika screen nya di rotasi, maka akan menyimpan ke state/bundle. jadi nilai resultnya tidak berubah
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_RESULT,tvResult.getText().toString());
    }
}
