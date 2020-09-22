package com.example.comicsprime.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comicsprime.AddComicsActivity;
import com.example.comicsprime.R;
import com.example.comicsprime.VolumesActivity;

import java.util.ArrayList;

public class titleRecyclerAdapter extends RecyclerView.Adapter<titleRecyclerAdapter.ViewHolder> {

    private ArrayList<String> titleList = new ArrayList<>();
    private OnComicListener mOnComicListener;

    public titleRecyclerAdapter(ArrayList<String> titleList, OnComicListener mOnComicListener){

        this.mOnComicListener = mOnComicListener;
        this.titleList = titleList;
    }


    @NonNull
    @Override
    public titleRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, parent, false);

        return new ViewHolder(view, mOnComicListener);
    }

    @Override
    public void onBindViewHolder(@NonNull titleRecyclerAdapter.ViewHolder holder, int position) {

        holder.title_textView.setText(titleList.get(position));

    }

    @Override
    public int getItemCount() {
        return titleList.size();
    }


    //VIEW HOLDER CLASS

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView title_textView;
        OnComicListener onComicListener;

        public ViewHolder(@NonNull View itemView, OnComicListener onComicListener) {
            super(itemView);
            title_textView = (TextView) itemView.findViewById(R.id.aComic);

            this.onComicListener = onComicListener;

            itemView.setOnClickListener(this); //this refers to interface that we implemented (View.OnClickListener)
        }


        @Override
        public void onClick(View v) {

            onComicListener.onComicClick(getAdapterPosition());
        }
    }

    public interface OnComicListener{
        void onComicClick(int position);
    }
}
