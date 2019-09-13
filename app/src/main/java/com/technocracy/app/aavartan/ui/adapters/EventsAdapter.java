package com.technocracy.app.aavartan.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.technocracy.app.aavartan.R;
import com.technocracy.app.aavartan.api.data_models.EventsData;
import com.technocracy.app.aavartan.ui.activities.EventDetailsActivity;
import com.technocracy.app.aavartan.utils.AppConstants;

import java.util.List;

public class EventsAdapter extends PagerAdapter {

    private List<EventsData> eventsData;
    private Context context;

    public EventsAdapter(List<EventsData> eventsData, Context context) {
        this.eventsData = eventsData;
        this.context = context;
    }

    @Override
    public int getCount() {
        if (eventsData.size() >= 27)
            return 27;
        else
            return eventsData.size();
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

        ivPoster = view.findViewById(R.id.ivPoster);
        tvTitle = view.findViewById(R.id.tvTitle);
        tvDescription = view.findViewById(R.id.tvDescription);

        Glide.with(context).load(eventsData.get(position).getThumbnail_img()).into(ivPoster);
        tvTitle.setText(eventsData.get(position).getTitle());
        tvDescription.setText(eventsData.get(position).getDescription());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EventDetailsActivity.class);
                intent.putExtra(AppConstants.EVENT_INTENT_EXTRA, eventsData.get(position));
                context.startActivity(intent);
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
