package com.example.s_newsprototype2;
import com.bumptech.glide.Glide;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{

    private List<ViewStructure> articleList;

    public Adapter (List<ViewStructure>articleList){this.articleList=articleList;}

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_design,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        String picture = articleList.get(position).getImage();
        String header = articleList.get(position).getTitle();
        String time = articleList.get(position).getDate();
        String about = articleList.get(position).getInfo();
        String url = articleList.get(position).getArticleButton();

        holder.setData(picture,header,time,about,url);
    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView Image;
        private TextView Title;
        private TextView Date;
        private TextView Info;
        private Button ArticleButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Image=itemView.findViewById(R.id.Image);
            Title=itemView.findViewById(R.id.Title);
            Date=itemView.findViewById(R.id.Date);
            Info=itemView.findViewById(R.id.Info);
            ArticleButton = itemView.findViewById(R.id.ArticleButton);

        }

        public void setData(String picture, String header, String time, String about, String url) {
            Glide.with(itemView.getContext()).load(picture).into(Image);
            Title.setText(header);
            Date.setText(time);
            Info.setText(about);
            ArticleButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Open the URL in a browser window
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
