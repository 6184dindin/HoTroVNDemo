package com.dindin.hotrovndemo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dindin.hotrovndemo.Poco;
import com.dindin.hotrovndemo.R;

import java.util.List;

public class AdapterPoco extends RecyclerView.Adapter<AdapterPoco.ViewHolder> {
    List<Poco> pocos;

    public AdapterPoco(List<Poco> pocos) {
        this.pocos = pocos;
    }

    @NonNull
    @Override
    public AdapterPoco.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_relief, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPoco.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return pocos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
