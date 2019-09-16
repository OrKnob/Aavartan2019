package com.technocracy.app.aavartan.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.api.data_models.ContactsData;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.MyViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<ContactsData> contactsDataList;

    public ContactsAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public void setContactsDataList(List<ContactsData> contactsDataList){
        this.contactsDataList = contactsDataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.item_contacts, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final ContactsData contactsData = contactsDataList.get(i);

        Glide.with(context).load(contactsData.getImage()).into(myViewHolder.ivProfile);
        myViewHolder.tvName.setText(contactsData.getName());
        myViewHolder.tvEmail.setText(contactsData.getEmail());
        myViewHolder.tvMobileNumber.setText(contactsData.getMobileNumber());

    }

    @Override
    public int getItemCount() {
        return contactsDataList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProfile;
        TextView tvName, tvEmail, tvMobileNumber;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ivProfile = itemView.findViewById(R.id.ivProfile);
            tvName = itemView.findViewById(R.id.tvName);
            tvEmail = itemView.findViewById(R.id.tvEmail);
            tvMobileNumber = itemView.findViewById(R.id.tvMobileNumber);

        }
    }

}

