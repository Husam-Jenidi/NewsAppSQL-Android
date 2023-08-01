package com.example.database;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MyAdapter2 extends RecyclerView.Adapter<MyAdapter2.ViewHolder> {

    private Context context;
    private List<ListComment> listComments;
    ImageView imageUser;
    public MyAdapter2(Context context, List<ListComment> listComments) {
        this.context = context;
        this.listComments = listComments;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.list_comment, parent, false);

        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ListComment listComment = listComments.get(position);
        holder.user.setText(listComment.getUser());
        holder.comment.setText(listComment.getComment());
        Glide.with(holder.image)
                .load(listComment.getImg_url())
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return listComments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView user, comment;
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageUser);
            user = itemView.findViewById(R.id.userName);
            comment = itemView.findViewById(R.id.text_comment);
        }
    }
}
