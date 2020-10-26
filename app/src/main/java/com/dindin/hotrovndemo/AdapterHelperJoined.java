package com.dindin.hotrovndemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdapterHelperJoined extends RecyclerView.Adapter<AdapterHelperJoined.ViewHolder> {
    List<HelperJoined> helperJoineds;
    @NonNull
    @Override
    public AdapterHelperJoined.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_relief_campaign, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHelperJoined.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return helperJoineds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
