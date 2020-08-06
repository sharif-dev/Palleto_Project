package com.example.palleto;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CollectionAdaptor extends RecyclerView.Adapter<CollectionAdaptor.ViewHolder> {


    private LayoutInflater layoutInflater ;
    ArrayList<Pallete> list ;
    Context context;

    public CollectionAdaptor(Context context,ArrayList<Pallete> list_of_pallets )  {
       this.layoutInflater = LayoutInflater.from(context);
        this.list = list_of_pallets;
        this.context=context;

    }

    @NonNull
    @Override
    public CollectionAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.custom_collectionlst , parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionAdaptor.ViewHolder holder, int position) {

        holder.name.setText(list.get(position).getName());
        Date date= new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy / HH:mm:ss");
        holder.Date.setText(formatter.format(date));
        holder.Description.setText(list.get(position).getColors().length);
        holder.click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //TODO
//
//                Intent newIntent = new Intent(context, pallet_detialsActivity.class);
//                newIntent.putExtra("show" , list);
//                context.startActivity(newIntent);




            }
        });




    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView name , Date , Description;
        ImageView dislike;
        ImageView click;

        public ViewHolder(@NonNull View itemView)  {
            super(itemView);
            name = itemView.findViewById(R.id.collection_name);
            Date = itemView.findViewById(R.id.collection_date);
            Description = itemView.findViewById(R.id.collection_description);
            click = itemView.findViewById(R.id.click_pallet);
            dislike = itemView.findViewById(R.id.like_or_not);



        }


    }

}

