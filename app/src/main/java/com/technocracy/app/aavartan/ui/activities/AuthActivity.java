package com.technocracy.app.aavartan.ui.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.ui.fragments.LoginFragment;
import com.technocracy.app.aavartan.ui.fragments.SignupFragment;
import com.technocracy.app.aavartan.utils.AppConstants;
import com.test.toggleswitchlibrary.BaseToggleSwitch;
import com.test.toggleswitchlibrary.ToggleSwitch;

public class AuthActivity extends AppCompatActivity {

    private ToggleSwitch toggleSwitch;

    private boolean isLoginFragment = true;
    private String intentExtra = AppConstants.AUTH_INTENT_LOGIN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            intentExtra = bundle.getString(AppConstants.AUTH_INTENT_EXTRA);
        }
        initView();
        setListeners();

    }

    private void initView() {

        toggleSwitch = findViewById(R.id.toggleAuth);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (intentExtra.equals(AppConstants.AUTH_INTENT_SIGNUP)) {
            toggleSwitch.setCheckedTogglePosition(1);
            isLoginFragment = false;
            fragmentTransaction.add(R.id.container, new SignupFragment());
        } else
            fragmentTransaction.add(R.id.container, new LoginFragment());
        fragmentTransaction.commit();

    }

    private void setListeners() {

        toggleSwitch.setOnToggleSwitchChangeListener(new BaseToggleSwitch.OnToggleSwitchChangeListener() {
            @Override
            public void onToggleSwitchChangeListener(int position, boolean isChecked) {

                if (position == 0) {
                    if (!isLoginFragment) {
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.setCustomAnimations(R.anim.push_right_in_no_alpha, R.anim.push_right_out_no_alpha);
                        fragmentTransaction.replace(R.id.container, new LoginFragment());
                        fragmentTransaction.commit();
                        isLoginFragment = true;
                    }
                } else if (position == 1) {
                    if (isLoginFragment) {
                        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.setCustomAnimations(R.anim.push_left_in_no_alpha, R.anim.push_left_out_no_alpha);
                        fragmentTransaction.replace(R.id.container, new SignupFragment());
                        fragmentTransaction.commit();
                        isLoginFragment = false;
                    }
                } else {
                    Intent intent = new Intent(AuthActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });

    }

}
