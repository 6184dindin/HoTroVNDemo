package com.dindin.hotrovndemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dindin.hotrovndemo.News;
import com.dindin.hotrovndemo.R;
import com.dindin.hotrovndemo.activity.ReliefInformationActivity;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    Context context;
    List<News> news;
    int key;
    String phoneNumber;
    int field;

    public NewsAdapter(Context context, List<News> news, int key, int field, String phoneNumber) {
        this.context = context;
        this.news = news;
        this.key = key;
        this.field = field;
        this.phoneNumber = phoneNumber;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_relief, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        holder.btnSeeDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReliefInformationActivity.class);
                intent.putExtra("key", key);
                intent.putExtra("phone", phoneNumber);
                intent.putExtra("field", field);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView btnSeeDetails;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btnSeeDetails = itemView.findViewById(R.id.btnSeeDetails);
        }
    }
}
