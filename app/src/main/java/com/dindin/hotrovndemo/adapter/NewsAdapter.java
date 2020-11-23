package com.dindin.hotrovndemo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dindin.hotrovndemo.R;
import com.dindin.hotrovndemo.activity.ReliefInformationActivity;
import com.dindin.hotrovndemo.api.param.response.News;
import com.dindin.hotrovndemo.utils.Define;
import com.dindin.hotrovndemo.utils.InfoAddress;

import java.util.Date;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    Context context;
    List<News> newses;
    int key;
    String phoneNumber;
    int field;

    List<InfoAddress> provinces;
    List<InfoAddress> cities;
    List<InfoAddress> districts;

    public NewsAdapter(Context context, List<News> newses, int key, String phoneNumber, int field) {
        this.context = context;
        this.newses = newses;
        this.key = key;
        this.phoneNumber = phoneNumber;
        this.field = field;
        this.provinces = Define.getProvinces(context);
        this.cities = Define.getListCity(context);
        this.districts = Define.getListDistrict(context);
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_relief, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        News news = newses.get(position);
        String provinceString = "";
        String cityString = "";
        String districtString = "";

        for (InfoAddress p : provinces) {
            if (p.getId().equals(news.getProvince())) {
                provinceString = p.getName();
                break;
            }
        }
        if (!provinceString.isEmpty()) {
            for (InfoAddress c : cities) {
                if (c.getId().equals(news.getCity())) {
                    cityString = c.getName() + ", ";
                    break;
                }
            }
        }
        if (!cityString.isEmpty()) {
            for (InfoAddress d : districts) {
                if (d.getId().equals(news.getProvince())) {
                    districtString = d.getName() + ", ";
                    break;
                }
            }
        }

        holder.tvAddress.setText(districtString + cityString + provinceString);
        holder.tvRequestSupport.setText(news.getRequestSupport() != null ? news.getRequestSupport() : "");
        holder.tvCountHelperJoined.setText("(" + news.getCountHelperJoined() + ")");

        Date date = new Date(news.getDateCreated());
        holder.tvDateTime.setText(Define.dfDateTime.format(date));

        holder.btnSeeDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ReliefInformationActivity.class);
                intent.putExtra("key", key);
                intent.putExtra("phone", phoneNumber);
                intent.putExtra("field", field);
                intent.putExtra("newsId", news.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvAddress;
        TextView tvRequestSupport;
        TextView tvCountHelperJoined;
        TextView tvDateTime;
        TextView btnSeeDetails;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAddress = itemView.findViewById(R.id.tvAddress);
            tvRequestSupport = itemView.findViewById(R.id.tvRequestSupport);
            tvCountHelperJoined = itemView.findViewById(R.id.tvCountHelperJoined);
            tvDateTime = itemView.findViewById(R.id.tvDateTime);
            btnSeeDetails = itemView.findViewById(R.id.btnSeeDetails);
        }
    }
}
