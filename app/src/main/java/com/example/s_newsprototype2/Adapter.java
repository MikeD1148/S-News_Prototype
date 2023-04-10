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

    //Create a new object View Holder object using the design from the item_design layout file for the Recycled View
    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_design,parent, false);
        return new ViewHolder(view);
    }

    //Bind list data to correct position in View Holder
    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {
        String picture = articleList.get(position).getImage();
        String header = articleList.get(position).getTitle();
        String time = articleList.get(position).getDate();
        String about = articleList.get(position).getInfo();
        String url = articleList.get(position).getArticleButton();

        holder.setData(picture,header,time,about,url);
    }

    //Number of News Articles
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

        //Retrieve layout items Constructor
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            Image=itemView.findViewById(R.id.Image);
            Title=itemView.findViewById(R.id.Title);
            Date=itemView.findViewById(R.id.Date);
            Info=itemView.findViewById(R.id.Info);
            ArticleButton = itemView.findViewById(R.id.ArticleButton);

        }

        //Update View Holder layout with current Article
        public void setData(String picture, String header, String time, String about, String url) {
            Glide.with(itemView.getContext()).load(picture).into(Image);
            Title.setText(header);
            Date.setText(time);
            Info.setText(about);
            ArticleButton.setOnClickListener(new View.OnClickListener() {
                //Open the Article URL in browser
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
