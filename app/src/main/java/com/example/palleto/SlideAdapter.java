package com.example.palleto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class SlideAdapter extends PagerAdapter {
    Context context;
    LayoutInflater layoutInflater;

    public SlideAdapter(Context context) {
        this.context  =context;
    }


    public int[] list_of_images  ={
            R.drawable.slide_img1,
            R.drawable.slide_img2,
            R.drawable.slide_img3

    };

    public String[] slide_titles={

            "PalletMaker","ColorMetter","YourPallet"
    };


    public String[] descriptions ={
            "kjshfjs" ,  "kjkjgshkj" , "kdjfhvdkjfh"
    };





    @Override
    public int getCount() {
        return slide_titles.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view  == object;
    }




    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater  = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide , container , false);

        LinearLayout linearLayout = view.findViewById(R.id.slide);
        ImageView imageView = view.findViewById(R.id.slide_img);
        TextView textView= view.findViewById(R.id.slide_title);
        TextView textView1 = view.findViewById(R.id.description);
        imageView.setImageResource(list_of_images[position]);
        textView.setText(slide_titles[position]);
        textView1.setText(descriptions[position]);

        container.addView(view);
        return view;
    }

}
