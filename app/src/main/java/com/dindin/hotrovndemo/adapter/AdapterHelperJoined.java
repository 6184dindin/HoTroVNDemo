package com.dindin.hotrovndemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dindin.hotrovndemo.HelperJoined;
import com.dindin.hotrovndemo.R;

import java.util.List;

public class AdapterHelperJoined extends RecyclerView.Adapter<AdapterHelperJoined.ViewHolder> {
    List<HelperJoined> helperJoineds;
    Context context;
    IAdapterHelperJoined iAdapterHelperJoined;
    public AdapterHelperJoined(List<HelperJoined> helperJoineds, Context context) {
        this.helperJoineds = helperJoineds;
    }

    public void setiAdapterHelperJoined(IAdapterHelperJoined iAdapterHelperJoined) {
        this.iAdapterHelperJoined = iAdapterHelperJoined;
    }

    @NonNull
    @Override
    public AdapterHelperJoined.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_relief_campaign, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHelperJoined.ViewHolder holder, final int position) {
        holder.btnSeeDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iAdapterHelperJoined.openDialogShowInformationReliefCampaign(helperJoineds.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return helperJoineds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView btnSeeDetails;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btnSeeDetails = itemView.findViewById(R.id.btnSeeDetails);
        }
    }
}
