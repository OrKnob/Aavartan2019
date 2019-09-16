package com.technocracy.app.aavartan.ui.adapters;

import android.annotation.SuppressLint;
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

public class AppTeamAdapter extends RecyclerView.Adapter<AppTeamAdapter.MyViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    private List<ContactsData> contactsDataList;

    public AppTeamAdapter(Context context ) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
    }

    public void setAppTeamDataList(List<ContactsData> contactsDataList){

        this.contactsDataList = contactsDataList;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.item_app_team, viewGroup, false);
        return new MyViewHolder(view);
    }

    @SuppressLint({"DefaultLocale"})
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        final ContactsData contactsData = contactsDataList.get(i);

        Glide.with(context).load(contactsData.getImage()).into(myViewHolder.ivProfile   );
        myViewHolder.tvName.setText(contactsData.getName());
        myViewHolder.tvBranch.setText(contactsData.getBranch());
        if (contactsData.getSemester() == 5) {
            myViewHolder.tvSemester.setText(String.format("%dth Semester", contactsData.getSemester()));
        } else {
            myViewHolder.tvSemester.setText(String.format("%drd Semester", contactsData.getSemester()));
        }

    }

    @Override
    public int getItemCount() {
        return contactsDataList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProfile;
        TextView tvName, tvBranch, tvSemester;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ivProfile = itemView.findViewById(R.id.ivProfile);
            tvName = itemView.findViewById(R.id.tvName);
            tvBranch = itemView.findViewById(R.id.tvBranch);
            tvSemester = itemView.findViewById(R.id.tvSemester);

        }
    }

}