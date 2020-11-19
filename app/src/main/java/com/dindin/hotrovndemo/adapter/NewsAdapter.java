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
import com.dindin.hotrovndemo.utils.City;
import com.dindin.hotrovndemo.utils.District;
import com.dindin.hotrovndemo.utils.Helper;
import com.dindin.hotrovndemo.utils.InfoAddress;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    Context context;
    List<News> newses;
    int key;
    String phoneNumber;
    int field;

    List<InfoAddress> provinces;
    List<City> cities;
    List<District> districts;

    public NewsAdapter(Context context, List<News> newses, int key, String phoneNumber, int field) {
        this.context = context;
        this.newses = newses;
        this.key = key;
        this.phoneNumber = phoneNumber;
        this.field = field;
        this.provinces = Helper.getProvinces(context);
        this.cities = Helper.getCities(context);
        this.districts = Helper.getDistricts(context);
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
        String province = "";
        String city = "";
        String district = "";
        if (!news.getProvince().equals(0)) {
            province = provinces.get(news.getProvince()).getName();
            if (!news.getCity().equals(0)) {
                for (City c : cities) {
                    if (c.getId().equals(news.getProvince())) {
                        city = c.getInfoAddresses().get(news.getCity()).getName() + ", ";
                        break;
                    }
                }
                if (!news.getDistrict().equals(0)) {
                    for (District d : districts) {
                        if (d.getId().equals(news.getCity())) {
                            district = d.getInfoAddresses().get(news.getDistrict()).getName() + ", ";
                            break;
                        }
                    }
                }
            }
        }
        holder.tvAddress.setText(district + city + province);
        holder.tvRequestSupport.setText(news.getRequestSupport());
        holder.tvCountHelperJoined.setText("(" + news.getCountHelperJoined() + ")");
        String dateTime = news.getDateCreated().toString();
        holder.tvDateTime.setText(dateTime.substring(6, 8)
                + "/" + dateTime.substring(4, 6)
                + "/" + dateTime.substring(0, 4));
//                + " - " + dateTime.substring(8,10)
//                + ":" + dateTime.substring(10,12));

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
