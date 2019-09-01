package com.technocracy.app.aavartan.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.ui.activities.EventDetailsActivity;
import com.technocracy.app.aavartan.ui.modals.EventsViewModel;

import java.util.List;

public class EventsAdapter extends PagerAdapter {

    private List<EventsViewModel> models;
    private Context context;

    public EventsAdapter(List<EventsViewModel> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_event, container, false);

        ImageView ivPoster;
        TextView tvTitle, tvDescription;

        ivPoster = view.findViewById(R.id.image);
        tvTitle = view.findViewById(R.id.title);
        tvDescription = view.findViewById(R.id.desc);

        ivPoster.setImageResource(models.get(position).getImage());
        tvTitle.setText(models.get(position).getTitle());
        tvDescription.setText(models.get(position).getDesc());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EventDetailsActivity.class);
                Log.d("Position", String.valueOf(position));
                intent.putExtra("ID", position + 1);
                context.startActivity(intent);
                // finish();
            }
        });

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
