package com.dindin.hotrovndemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dindin.hotrovndemo.Poco;
import com.dindin.hotrovndemo.R;
import com.dindin.hotrovndemo.activity.ReliefInformationActivity;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    Context context;
    List<Poco> pocos;
    int key;

    public NewsAdapter(Context context, List<Poco> pocos, int key) {
        this.context = context;
        this.pocos = pocos;
        this.key = key;
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
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pocos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView btnSeeDetails;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btnSeeDetails = itemView.findViewById(R.id.btnSeeDetails);
        }
    }
}
