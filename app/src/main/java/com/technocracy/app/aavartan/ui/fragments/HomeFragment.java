package com.technocracy.app.aavartan.ui.fragments;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.ui.activities.AboutUsActivity;
import com.technocracy.app.aavartan.ui.activities.AppTeamActivity;
import com.technocracy.app.aavartan.ui.activities.ContactsActivity;
import com.technocracy.app.aavartan.ui.activities.GalleryActivity;
import com.technocracy.app.aavartan.ui.activities.SponsorsActivity;
import com.technocracy.app.aavartan.ui.activities.VigyaanActivity;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import yanzhikai.textpath.AsyncTextPathView;

public class HomeFragment extends Fragment {

    //    Views
    private AsyncTextPathView textPathView;
    private BoomMenuButton boomMenuButton;

    //    Variables
    private String[] title;
    private int[] icons;

    private Timer timerPause,timerResume;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        textPathView.startAnimation(0, 1);
        timerPause.scheduleAtFixedRate(new pauseTask(),2000,4500);
        timerResume.scheduleAtFixedRate(new resumeTask(),2500,4500);
        boomMenuButtonClick();
        return view;
    }

    private void initView(View view) {

        textPathView = view.findViewById(R.id.asyncTextPathView);
        boomMenuButton = view.findViewById(R.id.boomMenuButton);
        timerPause = new Timer();
        timerResume = new Timer();
        title = getResources().getStringArray(R.array.boomMenuButtonOptions);
        icons = new int[]{R.drawable.icon_gallery, R.drawable.icon_sponsors, R.drawable.icon_contacts,
                R.drawable.icon_app_team, R.drawable.icon_about_us, R.drawable.icon_vigyaan};
    }

    private void boomMenuButtonClick() {

        int imagePadding = (int) getResources().getDimension(R.dimen.boomMenuImagePadding);
        int textPadding = (int) getResources().getDimension(R.dimen.boomMenuTextPadding);
        int textSize = (int) getResources().getDimension(R.dimen.boomMenuTextSize);

        for (int i = 0; i < boomMenuButton.getPiecePlaceEnum().pieceNumber(); i++) {
            TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder()
                    .listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            Intent intent;
                            if (index == 0) {
                                intent = new Intent(getContext(), GalleryActivity.class);
                                startActivity(intent);
                            } else if (index == 1) {
                                intent = new Intent(getContext(), SponsorsActivity.class);
                                startActivity(intent);
                            } else if (index == 2) {
                                intent = new Intent(getContext(), ContactsActivity.class);
                                startActivity(intent);
                            } else if (index == 3) {
                                intent = new Intent(getContext(), AppTeamActivity.class);
                                startActivity(intent);
                            } else if (index == 4) {
                                intent = new Intent(getContext(), AboutUsActivity.class);
                                startActivity(intent);
                            } else if (index == 5) {
                                intent = new Intent(getContext(), VigyaanActivity.class);
                                startActivity(intent);
                            }
                        }
                    })
                    .normalImageRes(icons[i])
                    .normalText(title[i])
                    .rotateImage(true)
                    .imagePadding(new Rect(imagePadding, imagePadding, imagePadding, imagePadding))
                    .textGravity(Gravity.CENTER)
                    .rippleEffect(true)
                    .normalColor(R.color.boomMenuButtonCategories)
                    .textGravity(Gravity.CENTER)
                    .textSize(textSize).maxLines(1)
                    .textPadding(new Rect(textPadding, textPadding, textPadding, textPadding));

            boomMenuButton.addBuilder(builder);
        }
    }

    public class pauseTask extends TimerTask{

        @Override
        public void run() {
            Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    textPathView.pauseAnimation();

                }
            });
        }
    }

    public class resumeTask extends TimerTask{

        @Override
        public void run() {

            Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    textPathView.resumeAnimation();

                }
            });

        }
    }

    @Override
    public void onPause() {
        super.onPause();
        timerPause.cancel();
        timerResume.cancel();
    }

}