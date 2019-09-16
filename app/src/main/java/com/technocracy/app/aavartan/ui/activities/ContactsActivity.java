package com.technocracy.app.aavartan.ui.activities;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
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
        contactsDataList.add(new ContactsData(getDrawable(R.drawable.img_aditya_swarnkar), "Aditya Swarnkar", "aditya.01swarnkar@gmail.com", "+91 73890 97623"));
        contactsDataList.add(new ContactsData(getDrawable(R.drawable.img_ayush_mishra),"Ayush Mishra", "ayumishra26@gmail.com", "+91 72238 88440"));
    }

    private void setUpRecyclerView() {
        ContactsAdapter contactsAdapter = new ContactsAdapter(this);
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
        rvContacts.setAdapter(contactsAdapter);
        contactsAdapter.setContactsDataList(contactsDataList);
        contactsAdapter.notifyDataSetChanged();
    }

}
