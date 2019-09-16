package com.technocracy.app.aavartan.ui.activities;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.api.data_models.ContactsData;
import com.technocracy.app.aavartan.ui.adapters.ContactsAdapter;

import java.util.ArrayList;
import java.util.List;

public class ContactsActivity extends AppCompatActivity {

    RecyclerView rvContacts;

    private List<ContactsData> contactsDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        initView();
        setUpRecyclerView();
    }

    private void initView() {
        rvContacts = findViewById(R.id.rvContacts);
    }

    private void setUpRecyclerView() {
        ContactsAdapter contactsAdapter = new ContactsAdapter(this);
        rvContacts.setLayoutManager(new GridLayoutManager(this,2));
        rvContacts.setAdapter(contactsAdapter);
        contactsAdapter.setContactsDataList(contactsDataList);
        contactsAdapter.notifyDataSetChanged();
    }

}
