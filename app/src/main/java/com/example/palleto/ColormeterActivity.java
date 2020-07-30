package com.example.palleto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ColormeterActivity extends AppCompatActivity {

    private ImageView imageView;
    private CardView showcolor;
    private TextView show_rgb;
    private TextView show_hex;
    private TextView show_hsv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.colormeter_activity);

        imageView = findViewById(R.id.img_meter);
        showcolor = findViewById(R.id.color_selected);
        show_hsv = findViewById(R.id.show_HSV);
        show_hex = findViewById(R.id.show_hex);
        show_rgb = findViewById(R.id.show_rgb);


        byte[] byteArray = this.getIntent().getByteArrayExtra("bitmapimg");
        final Bitmap image_btmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);




        imageView.setImageBitmap(image_btmap);
        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache(true);
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == event.ACTION_DOWN || event.getAction() == event.ACTION_MOVE)
                {
                        int pixel = image_btmap.getPixel((int) event.getX(), (int) event.getY());

                        int a = Color.alpha(pixel);
                        int r = Color.red(pixel);
                        int g = Color.green(pixel);
                        int b = Color.blue(pixel);
                        float[] hsv = new float[3];
                        Color.RGBToHSV(r, g, b, hsv);
                        int outputColor = Color.HSVToColor(hsv);

                        show_hsv.setText("hsv:\n" + outputColor);
                        show_rgb.setText("rgb:\n" + "," + r + "," + g + "," + b);
                        show_hex.setText("Hex:\n" + "#" + Integer.toHexString(pixel));
                        showcolor.setCardBackgroundColor(Color.rgb(r, g, b));


                }
                return true;
            }
        });
    }


}
