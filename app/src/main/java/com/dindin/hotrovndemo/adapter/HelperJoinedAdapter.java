package com.dindin.hotrovndemo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dindin.hotrovndemo.api.param.response.getinfonewsresponse.Helper;
import com.dindin.hotrovndemo.R;

import java.util.List;

public class HelperJoinedAdapter extends RecyclerView.Adapter<HelperJoinedAdapter.ViewHolder> {
    List<Helper> helpers;
    Context context;
    OnClickHelperJoinedListener onClickHelperJoinedListener;

    public HelperJoinedAdapter(List<Helper> helpers, Context context) {
        this.helpers = helpers;
        this.context = context;
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

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull HelperJoinedAdapter.ViewHolder holder, final int position) {
        Helper helper = helpers.get(position);
        holder.tvOrganization.setText(helper.getOrganization() != null ? helper.getOrganization() : "");
        holder.tvSupportValue.setText(helper.getSupportValue() != null ? helper.getSupportValue() : "");
        holder.tvRolePersonHelper.setText(helper.getRolePersonHelper() != null ? helper.getRolePersonHelper() : "");

        holder.tvAdminHelperAndPhoneContact.setText((helper.getAdminHelper() != null ? helper.getAdminHelper() : "")
                + " | "
                + (helper.getPhoneContact() != null ? helper.getPhoneContact() : ""));

        if (helper.getDateCreated() != null) {
            String dateTime = helper.getDateCreated().toString();
            holder.tvDateTime.setText(dateTime.substring(6, 8)
                    + "/" + dateTime.substring(4, 6)
                    + "/" + dateTime.substring(0, 4));
//                + " - " + dateTime.substring(8,10)
//                + ":" + dateTime.substring(10,12));
        }

        holder.btnSeeDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickHelperJoinedListener.openDialogShowInformationReliefCampaign(helpers.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return helpers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvOrganization;
        TextView tvSupportValue;
        TextView tvAdminHelperAndPhoneContact;
        TextView tvRolePersonHelper;
        TextView tvDateTime;
        TextView btnSeeDetails;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvOrganization = itemView.findViewById(R.id.tvOrganization);
            tvSupportValue = itemView.findViewById(R.id.tvSupportValue);
            tvAdminHelperAndPhoneContact = itemView.findViewById(R.id.tvAdminHelperAndPhoneContact);
            tvRolePersonHelper = itemView.findViewById(R.id.tvRolePersonHelper);
            tvDateTime = itemView.findViewById(R.id.tvDateTime);
            btnSeeDetails = itemView.findViewById(R.id.btnSeeDetails);
        }
    }
}
