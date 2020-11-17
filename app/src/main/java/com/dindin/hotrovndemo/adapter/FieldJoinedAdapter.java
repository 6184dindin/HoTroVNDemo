package com.dindin.hotrovndemo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dindin.hotrovndemo.R;
import com.dindin.hotrovndemo.utils.Field;

import java.util.List;

public class FieldJoinedAdapter extends RecyclerView.Adapter<FieldJoinedAdapter.ViewHolder> {
    List<Field> fields;
    OnClickFieldJoinedListener listener;

    public FieldJoinedAdapter(List<Field> fields) {
        this.fields = fields;
    }

    public void setListener(OnClickFieldJoinedListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public FieldJoinedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_field_joined, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FieldJoinedAdapter.ViewHolder holder, int position) {
        Field field = fields.get(position);
        holder.tvFiled.setText(field.getName());
        if (position == fields.size() - 1) {
            holder.view.setVisibility(View.GONE);
        }
        holder.layoutFiled.setOnClickListener(view -> {
            listener.onClickField(field.getId());
        });
    }

    @Override
    public int getItemCount() {
        return fields.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout layoutFiled;
        TextView tvFiled;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutFiled = itemView.findViewById(R.id.layoutField);
            tvFiled = itemView.findViewById(R.id.tvFiled);
            view = itemView.findViewById(R.id.view);
        }
    }
}
