package com.technocracy.app.aavartan.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.ui.fragments.OTPVerifyFragment;
import com.test.toggleswitchlibrary.BaseToggleSwitch;
import com.test.toggleswitchlibrary.ToggleSwitch;

public class OTPVerifyActivity extends AppCompatActivity {

    private ToggleSwitch toggleSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verify);
        initView();
        setListeners();

    }

    private void initView() {

        toggleSwitch = findViewById(R.id.toggleOTP);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container, new OTPVerifyFragment());
        fragmentTransaction.commit();
    }

    private void setListeners() {

        toggleSwitch.setOnToggleSwitchChangeListener(new BaseToggleSwitch.OnToggleSwitchChangeListener() {
            @Override
            public void onToggleSwitchChangeListener(int position, boolean isChecked) {

                if (position == 1) {
                    Intent intent = new Intent(OTPVerifyActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });

    }

}
