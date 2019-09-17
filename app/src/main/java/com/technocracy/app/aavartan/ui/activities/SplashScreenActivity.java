package com.technocracy.app.aavartan.ui.activities;

import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.play.core.appupdate.AppUpdateInfo;
import com.google.android.play.core.appupdate.AppUpdateManager;
import com.google.android.play.core.appupdate.AppUpdateManagerFactory;
import com.google.android.play.core.install.model.AppUpdateType;
import com.google.android.play.core.install.model.UpdateAvailability;
import com.google.android.play.core.tasks.OnSuccessListener;
import com.google.android.play.core.tasks.Task;
import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.utils.SessionManager;

import es.dmoral.toasty.Toasty;

public class SplashScreenActivity extends AppCompatActivity {

    private VideoView videoView;

    private AppUpdateManager appUpdateManager;
    private Task<AppUpdateInfo> appUpdateInfoTask;
    private int REQUEST_CODE = 2019;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initView();
        setListeners();
        loadSplashVideo();
    }

    private void initView() {

        videoView = findViewById(R.id.videoView);
        appUpdateManager = AppUpdateManagerFactory.create(SplashScreenActivity.this);
        appUpdateInfoTask = appUpdateManager.getAppUpdateInfo();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode != RESULT_OK) {
                Toasty.error(SplashScreenActivity.this, "Update Failed! Trying Again", Toasty.LENGTH_LONG).show();
                checkAppUpdate();
            }
        }

    }

    private void setListeners() {

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                checkAppUpdate();
            }
        });

    }

    private void loadSplashVideo() {

        Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.splash_screen);
        videoView.setVideoURI(video);
        videoView.start();

    }

    private void checkAppUpdate() {

        appUpdateInfoTask.addOnSuccessListener(new OnSuccessListener<AppUpdateInfo>() {
            @Override
            public void onSuccess(AppUpdateInfo appUpdateInfo) {
                if (appUpdateInfo.updateAvailability() == UpdateAvailability.UPDATE_AVAILABLE
                        && appUpdateInfo.isUpdateTypeAllowed(AppUpdateType.IMMEDIATE)) {
                    try {
                        appUpdateManager.startUpdateFlowForResult(
                                appUpdateInfo,
                                AppUpdateType.IMMEDIATE,
                                SplashScreenActivity.this,
                                REQUEST_CODE);
                    } catch (IntentSender.SendIntentException e) {
                        e.printStackTrace();
                    }

                } else {

                    if (SessionManager.getIsLoggedIn()) {
                        Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
                        startActivity(i);
                        finish();
                    } else {
                        Intent i = new Intent(SplashScreenActivity.this, AuthActivity.class);
                        startActivity(i);
                        finish();
                    }

                }

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        appUpdateManager.getAppUpdateInfo()
                .addOnSuccessListener(new OnSuccessListener<AppUpdateInfo>() {
                    @Override
                    public void onSuccess(AppUpdateInfo appUpdateInfo) {
                        if (appUpdateInfo.updateAvailability()
                                == UpdateAvailability.DEVELOPER_TRIGGERED_UPDATE_IN_PROGRESS) {
                            try {
                                appUpdateManager.startUpdateFlowForResult(
                                        appUpdateInfo,
                                        AppUpdateType.IMMEDIATE,
                                        SplashScreenActivity.this,
                                        REQUEST_CODE);
                            } catch (IntentSender.SendIntentException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }


}
