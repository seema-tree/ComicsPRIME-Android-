package com.example.comicsprime.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comicsprime.R;

import java.util.ArrayList;

public class volumeRecyclerAdapter extends RecyclerView.Adapter<volumeRecyclerAdapter.ViewHolder> {


    private ArrayList<String> volumeList = new ArrayList<>();
    private OnComicVolumeListener mOnComicVolumeListener;

    public volumeRecyclerAdapter(ArrayList<String> volumeList, OnComicVolumeListener mOnComicVolumeListener){

        this.mOnComicVolumeListener = mOnComicVolumeListener;
        this.volumeList = volumeList;
    }



    @NonNull
    @Override
    public volumeRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, parent, false);

        return new ViewHolder(view, mOnComicVolumeListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.volume_textView.setText("Volume " + volumeList.get(position));

    }

    @Override
    public int getItemCount() {
        return volumeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView volume_textView;
        OnComicVolumeListener onComicVolumeListener;

        public ViewHolder(@NonNull View itemView, OnComicVolumeListener onComicVolumeListener) {
            super(itemView);
            volume_textView = (TextView) itemView.findViewById(R.id.aComic);

            this.onComicVolumeListener = onComicVolumeListener;

            itemView.setOnClickListener(this); //this refers to interface that we implemented (View.OnClickListener)
        }


        @Override
        public void onClick(View v) {

            onComicVolumeListener.onComicVolumeClick(getAdapterPosition());
        }
    }

    public interface OnComicVolumeListener{
        void onComicVolumeClick(int position);
    }
}
