package com.example.palleto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class pallet_detialsActivity extends AppCompatActivity {


    LinearLayout show_color;
    LinearLayout show_color_text;
    CardView[] colors;
    TextView[] color_hex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pallet_detials);
        Pallete pallets = (Pallete) getIntent().getSerializableExtra("show");
        colors = new CardView[pallets.getColors().length];
        color_hex = new TextView[pallets.getColors().length];
        show_color= findViewById(R.id.show_colors);
        show_color_text = findViewById(R.id.show_colors_text);
        for (int i=0;i<pallets.getColors().length; i++)
        {

            colors[i] = new CardView(this);
            color_hex[i] = new TextView(this);

            colors[i].setBackgroundColor(Color.parseColor(pallets.getColors()[i]));
            colors[i].setLayoutParams(new LinearLayout.LayoutParams(90, 90));
            colors[i].setRotation(45);
            colors[i].setRadius(12);
            color_hex[i].setText(pallets.getColors()[i]);
            color_hex[i].setTextColor(Color.parseColor(pallets.getColors()[i]));
            color_hex[i].setTextSize(30);
            color_hex[i].setGravity(Gravity.CENTER);
            color_hex[i].setPadding(2, 0 , 20 ,0);
            show_color.addView(colors[i]);
            show_color_text.addView(color_hex[i]);

        }


    }
}
