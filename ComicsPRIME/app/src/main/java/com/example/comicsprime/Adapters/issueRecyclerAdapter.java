package com.example.comicsprime.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.comicsprime.R;

import java.util.ArrayList;

public class issueRecyclerAdapter extends RecyclerView.Adapter<issueRecyclerAdapter.ViewHolder> {


    private ArrayList<String> issueList = new ArrayList<>();
    private OnComicIssueListener mOnComicIssueListener;


    public issueRecyclerAdapter(ArrayList<String> issueList, OnComicIssueListener mOnComicIssueListener){

        this.issueList = issueList;
        this.mOnComicIssueListener = mOnComicIssueListener;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, parent, false);

        return new ViewHolder(view, mOnComicIssueListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.issue_textView.setText("Issue " + issueList.get(position));
    }

    @Override
    public int getItemCount() {
        return issueList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        TextView issue_textView;
        OnComicIssueListener onComicIssueListener;

        public ViewHolder(@NonNull View itemView, OnComicIssueListener onComicIssueListener) {
            super(itemView);
            issue_textView = (TextView) itemView.findViewById(R.id.aComic);

            this.onComicIssueListener = onComicIssueListener;

            itemView.setOnClickListener(this); //this refers to interface that we implemented (View.OnClickListener)
        }


        @Override
        public void onClick(View v) {

            onComicIssueListener.onComicIssueClick(getAdapterPosition());
        }
    }

    public interface OnComicIssueListener{
        void onComicIssueClick(int position);
    }
}
