package com.technocracy.app.aavartan.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.technocracy.app.aavartan.R;

public class OTPTextWatcher implements TextWatcher {

    private View view;
    private EditText etOTPVerify1, etOTPVerify2, etOTPVerify3, etOTPVerify4, etOTPVerify5, etOTPVerify6;

    public OTPTextWatcher(View parentView, View view) {
        this.view = view;
        initView(parentView);
    }

    private void initView(View parentView) {

        etOTPVerify1 = parentView.findViewById(R.id.etOTPVerify1);
        etOTPVerify2 = parentView.findViewById(R.id.etOTPVerify2);
        etOTPVerify3 = parentView.findViewById(R.id.etOTPVerify3);
        etOTPVerify4 = parentView.findViewById(R.id.etOTPVerify4);
        etOTPVerify5 = parentView.findViewById(R.id.etOTPVerify5);
        etOTPVerify6 = parentView.findViewById(R.id.etOTPVerify6);

    }

    @Override
    public void afterTextChanged(Editable editable) {
        String text = editable.toString();
        switch (view.getId()) {

            case R.id.etOTPVerify1:
                if (text.length() == 1)
                    etOTPVerify2.requestFocus();
                break;
            case R.id.etOTPVerify2:
                if (text.length() == 1)
                    etOTPVerify3.requestFocus();
                else if (text.length() == 0)
                    etOTPVerify1.requestFocus();
                break;
            case R.id.etOTPVerify3:
                if (text.length() == 1)
                    etOTPVerify4.requestFocus();
                else if (text.length() == 0)
                    etOTPVerify2.requestFocus();
                break;
            case R.id.etOTPVerify4:
                if (text.length() == 1)
                    etOTPVerify5.requestFocus();
                else if (text.length() == 0)
                    etOTPVerify3.requestFocus();
                break;
            case R.id.etOTPVerify5:
                if (text.length() == 1)
                    etOTPVerify6.requestFocus();
                else if (text.length() == 0)
                    etOTPVerify4.requestFocus();
                break;
            case R.id.etOTPVerify6:
                if (text.length() == 0)
                    etOTPVerify5.requestFocus();
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

    }

    @Override
    public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

    }
}
