package com.technocracy.app.aavartan.ui.activities;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.api.data_models.ContactsData;
import com.technocracy.app.aavartan.ui.adapters.AppTeamAdapter;

import java.util.ArrayList;
import java.util.List;

public class AppTeamActivity extends AppCompatActivity {

    private RecyclerView rvAppTeam;

    private List<ContactsData> appTeamDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_team);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initView();
        setUpRecyclerView();

    }

    private void initView() {

        rvAppTeam = findViewById(R.id.rvAppTeam);
        appTeamDataList.add(new ContactsData(getDrawable(R.drawable.img_nisheeth_agrawal), "Nisheeth Agrawal", "Computer Sc. and Engg.",5));
        appTeamDataList.add(new ContactsData(getDrawable(R.drawable.img_hritik_tambe), "Hritik Tambe", "Computer Sc. and Engg.",5));
        appTeamDataList.add(new ContactsData(getDrawable(R.drawable.img_utkarsh_choubey), "Utkarsh Choubey", "Computer Sc. and Engg.",5));
        appTeamDataList.add(new ContactsData(getDrawable(R.drawable.img_durgeshwari_naik), "Durgeshwari Naik", "Computer Sc. and Engg.",5));
        appTeamDataList.add(new ContactsData(getDrawable(R.drawable.img_arnav_jha), "Arnav Jha", "Mechanical Engg.",3));

    }

    private void setUpRecyclerView() {
        AppTeamAdapter appTeamAdapter = new AppTeamAdapter(this);
        rvAppTeam.setLayoutManager(new LinearLayoutManager(this));
        rvAppTeam.setAdapter(appTeamAdapter);
        appTeamAdapter.setAppTeamDataList(appTeamDataList);
        appTeamAdapter.notifyDataSetChanged();
    }

}
