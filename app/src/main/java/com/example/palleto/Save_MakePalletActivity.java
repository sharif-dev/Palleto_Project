package com.example.palleto;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Save_MakePalletActivity extends AppCompatActivity {
    ArrayList<Pixel> pallet_colores;
    CardView[] colors;
    TextView[] color_hex;
    LinearLayout mlinearlayout;
    LinearLayout linearLayout2;
    ImageView imageView;
    ImageView save_btn;
    ImageView like_btn;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save__make_pallet);
        mlinearlayout = findViewById(R.id.extractedcolors);
        imageView = findViewById(R.id.image);
        save_btn = findViewById(R.id.save_tocollecton);
        like_btn = findViewById(R.id.add_to_favorits);
        linearLayout2 = findViewById(R.id.extractedcolors_hex);
        pallet_colores = (ArrayList<Pixel>) getIntent().getSerializableExtra("colorlist");
        byte[] byteArray = this.getIntent().getByteArrayExtra("image_show");
        Bitmap bitmap_show = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        imageView.setImageBitmap(bitmap_show);
        colors = new CardView[pallet_colores.size()];
        color_hex = new TextView[pallet_colores.size()];

        for (int i=0; i< colors.length;i++)
        {

            colors[i] = new CardView(this);
            color_hex[i]= new TextView(this);
            colors[i].setBackgroundColor(Color.rgb(pallet_colores.get(i).red,pallet_colores.get(i).green,pallet_colores.get(i).blue));
            colors[i].setLayoutParams(new LinearLayout.LayoutParams(100 , 100));
            colors[i].setRotation(45);
            colors[i].setRadius(12);
            String hex = String.format("#%02x%02x%02x", pallet_colores.get(i).red,pallet_colores.get(i).green,pallet_colores.get(i).blue);
            color_hex[i].setText(hex);
            color_hex[i].setTextSize(20);
            color_hex[i].setPadding(10, 0 , 10 , 0);
            color_hex[i].setTextColor(Color.rgb(pallet_colores.get(i).red,pallet_colores.get(i).green,pallet_colores.get(i).blue));
            mlinearlayout.addView(colors[i]);
            linearLayout2.addView(color_hex[i]);

        }



        like_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO by nilofar
            }
        });



        save_btn .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO by nilofar
            }
        });
    }
}
