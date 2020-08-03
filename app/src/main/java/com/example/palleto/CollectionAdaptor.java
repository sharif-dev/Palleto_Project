package com.example.palleto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CollectionAdaptor extends RecyclerView.Adapter<CollectionAdaptor.ViewHolder> {


    private LayoutInflater layoutInflater ;
    private List<String> mnames;
//    private List<String> mcolors;
    private List<String> mdescriptions;
    private List<String> mdates;

    public CollectionAdaptor(Context context , List<String> name  ,List<String> descriptions  , List<String> dates)  {
        this.layoutInflater = LayoutInflater.from(context);
        this.mnames = name;
//        this.mcolors = colors;
        this.mdescriptions = descriptions;
        this.mdates = dates;
    }

    @NonNull
    @Override
    public CollectionAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.custom_collectionlst , parent , false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionAdaptor.ViewHolder holder, int position) {
            holder.name.setText(mnames.get(position));
            holder.Date.setText(mdates.get(position));
            holder.Description.setText(mdescriptions.get(position));
//            holder.first_color.setBackgroundColor(Integer.parseInt(mcolors.get(position)));
//            holder.second_color.setBackgroundColor(Integer.parseInt(mcolors.get(position)));
//             holder.third_color.setBackgroundColor(Integer.parseInt(mcolors.get(position)));
//            holder.forth_color.setBackgroundColor(Integer.parseInt(mcolors.get(position)));
//            holder.fifth_color.setBackgroundColor(Integer.parseInt(mcolors.get(position)));
    }
    @Override
    public int getItemCount() {
        return mnames.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView name , Date , Description;
        LinearLayout first_color  ,second_color , third_color , forth_color , fifth_color;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.collection_name);
            Date = itemView.findViewById(R.id.collection_date);
            Description = itemView.findViewById(R.id.collection_description);
//            first_color = itemView.findViewById(R.id.first_color);
//            second_color = itemView.findViewById(R.id.second_color);
//            third_color = itemView.findViewById(R.id.third_color);
//            forth_color= itemView.findViewById(R.id.forth_color);
//            fifth_color= itemView.findViewById(R.id.fifth_color);

        }
    }

}

