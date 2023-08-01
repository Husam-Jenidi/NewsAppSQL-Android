    package com.example.database;

    import android.content.Context;
    import android.content.Intent;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ImageView;
    import android.widget.TextView;
    import android.widget.Toast;

    import androidx.annotation.NonNull;
    import androidx.recyclerview.widget.RecyclerView;

    import com.bumptech.glide.Glide;

    import java.util.List;

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        private Context context;
        private List<ListItem> listItems;
        private String userId;
        public MyAdapter(Context context, List<ListItem> listItems, String userId) {
            this.userId = userId;
            this.listItems = listItems;
            this.context = context;


        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            ListItem listItem = listItems.get(position);
            holder.title.setText(listItem.getTitle());
            holder.category.setText(listItem.getCategory());
            holder.date.setText(listItem.getDate());
            holder.src.setText(listItem.getSrc());
            Glide.with(context).load(listItem.getImage()).into(holder.image);
        }

        @Override
        public int getItemCount() {
            return listItems.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView title, category, date, src;
            ImageView image;

            public ViewHolder(View itemView) {
                super(itemView);
                image = itemView.findViewById(R.id.imageView);
                title = itemView.findViewById(R.id.textViewTitle);
                category = itemView.findViewById(R.id.textViewCategory);
                date = itemView.findViewById(R.id.textViewDate);
                src = itemView.findViewById(R.id.textViewSrc);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Get the clicked item position
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            ListItem clickedItem = listItems.get(position);

                            Intent intent = new Intent(context, postActivity.class);
                            intent.putExtra("post_content", clickedItem.getContent());
                            intent.putExtra("post_title", clickedItem.getTitle());
                            intent.putExtra("post_source", clickedItem.getSrc());
                            intent.putExtra("post_date", clickedItem.getDate());
                            String articleIdString = String.valueOf(clickedItem.getArticle_id());
                            intent.putExtra("user_id", userId);
                            intent.putExtra("article_id", articleIdString);
                            intent.putExtra("article_image",clickedItem.getImage());
                            context.startActivity(intent);
                        }
                    }
                });
            }
        }
    }
