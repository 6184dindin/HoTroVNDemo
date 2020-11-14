package com.dindin.hotrovndemo.activity;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dindin.hotrovndemo.databinding.AdapterImageChooseBinding;
import com.dindin.hotrovndemo.utils.ImageSelectAdapterEvent;

import java.util.List;

public class ImagesSelectAdapter extends RecyclerView.Adapter<ImagesSelectAdapter.ViewHolder> {
    private static final int IMAGE_URI = 1;
    private static final int IMAGE_BITMAP = 2;
    private List<ImageChoose> list;
    private ImageSelectAdapterEvent callBack;

    public ImagesSelectAdapter(List<ImageChoose> list, ImageSelectAdapterEvent callBack) {
        this.list = list;
        this.callBack = callBack;
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).isUri()) {
            return IMAGE_URI;
        }
        return IMAGE_BITMAP;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        AdapterImageChooseBinding binding = AdapterImageChooseBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position));
        holder.binding.btnClosed.setOnClickListener(view -> {
            callBack.onDeleteItem(position);
        });
    }

    @Override
    public int getItemCount() {
        return list.size() < 5 ? list.size() : 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        AdapterImageChooseBinding binding;

        public ViewHolder(@NonNull AdapterImageChooseBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void bind(ImageChoose imageChoose) {
            if (getItemViewType() == IMAGE_URI) {
                binding.imageView.setImageURI(imageChoose.getUri());
            } else {
                binding.imageView.setImageBitmap(imageChoose.getBitmap());
            }
        }
    }


}
