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

public class HelperJoinedAdapter extends RecyclerView.Adapter<HelperJoinedAdapter.ViewHolder> {
    List<HelperJoined> helperJoineds;
    Context context;
    OnClickHelperJoinedListener onClickHelperJoinedListener;
    public HelperJoinedAdapter(List<HelperJoined> helperJoineds, Context context) {
        this.helperJoineds = helperJoineds;
    }

    public void setOnClickHelperJoinedListener(OnClickHelperJoinedListener onClickHelperJoinedListener) {
        this.onClickHelperJoinedListener = onClickHelperJoinedListener;
    }

    @NonNull
    @Override
    public HelperJoinedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_relief_campaign, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HelperJoinedAdapter.ViewHolder holder, final int position) {
        holder.btnSeeDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickHelperJoinedListener.openDialogShowInformationReliefCampaign(helperJoineds.get(position));
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
