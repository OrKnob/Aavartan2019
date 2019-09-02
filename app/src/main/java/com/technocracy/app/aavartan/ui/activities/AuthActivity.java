package com.technocracy.app.aavartan.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.technocracy.app.aavartan.R;
import com.test.toggleswitchlibrary.BaseToggleSwitch;
import com.test.toggleswitchlibrary.ToggleSwitch;

public class AuthActivity extends AppCompatActivity {

    ToggleSwitch toggleSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        initView();
    }

    private void initView(){
        toggleSwitch =  findViewById(R.id.toggleAuth);
        toggleSwitch.setOnToggleSwitchChangeListener(new BaseToggleSwitch.OnToggleSwitchChangeListener() {
            @Override
            public void onToggleSwitchChangeListener(int position, boolean isChecked) {



            }
        });
    }


}
