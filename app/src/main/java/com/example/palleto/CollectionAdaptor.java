package com.example.palleto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CollectionAdaptor extends RecyclerView.Adapter<CollectionAdaptor.ViewHolder> {


    private LayoutInflater layoutInflater ;
    ArrayList<Pallete> list ;

    public CollectionAdaptor(Context context , ArrayList<Pallete> list_of_pallets )  {
        this.layoutInflater = LayoutInflater.from(context);
        this.list = list_of_pallets;

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
        holder.Date.setText(list.get(position).getDate());
        holder.Description.setText(list.get(position).getDescription());
        holder.click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
        LinearLayout l1,l2,l3,l4,l5,l6;
        ImageView click;

        public ViewHolder(@NonNull View itemView)  {
            super(itemView);
            name = itemView.findViewById(R.id.collection_name);
            Date = itemView.findViewById(R.id.collection_date);
            Description = itemView.findViewById(R.id.collection_description);
            click = itemView.findViewById(R.id.click_pallet);
//            l1=itemView.findViewById(R.id.first_color);
//            l2=itemView.findViewById(R.id.second_color);
//            l3=itemView.findViewById(R.id.third_color);
//            l4=itemView.findViewById(R.id.forth_color);
//            l5=itemView.findViewById(R.id.fifth_color);
//            l6=itemView.findViewById(R.id.six_color);





        }


    }

}

