package com.example.palleto;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.palette.graphics.Palette;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class palletmaker_firstActivity extends AppCompatActivity {


    ImageView image_ ;
    Button kmeanalgorithme;
    Button mediancutalgorithme;
    Bitmap image_btmap;
    EditText count;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palletmaker_first);
        kmeanalgorithme = findViewById(R.id.k_mean);
        mediancutalgorithme = findViewById(R.id.meadian_cut);
        progressBar=findViewById(R.id.progressbar);
        progressBar.setVisibility(View.INVISIBLE);
        image_ = findViewById(R.id.image_one);
        count = findViewById(R.id.color_count);

        byte[] byteArray = this.getIntent().getByteArrayExtra("bitmapimg");
        image_btmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        image_.setImageBitmap(image_btmap);

        kmeanalgorithme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ArrayList<Pixel> L = GetPixelsFromImage(image_btmap);
                final int k = Integer.valueOf(String.valueOf(count.getText()));
                if (k >= 0 && k <= 6)
                {
                    progressBar.setVisibility(View.VISIBLE);

                Thread t2 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ArrayList<Pixel> Lk = kmeans(L, k);
                        Intent intent = new Intent(palletmaker_firstActivity.this, Save_MakePalletActivity.class);
                        intent.putExtra("colorlist", Lk);

                        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
                        image_btmap.compress(Bitmap.CompressFormat.PNG, 100, bStream);
                        byte[] byteArray = bStream.toByteArray();
                        intent.putExtra("image_show" , byteArray);
                        startActivity(intent);
                    }
                });
                t2.start();


            }
                else
                {
                    AlertDialog.Builder alert = new AlertDialog.Builder(palletmaker_firstActivity.this);
                    alert.setTitle("Notice");
                    alert.setMessage("Number is not valid!\nPlease enter a number less that 7");
                    alert.create().show();

                }
            }
        });
        progressBar.setVisibility(View.INVISIBLE);




        mediancutalgorithme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Palette palette = Palette.generate(image_btmap);
                ArrayList<Pixel> colors = new ArrayList<Pixel>();
                int default_ = 0x000000;
                int vibrant = palette.getVibrantColor(default_);
                int vibrantLight = palette.getLightVibrantColor(default_);
                int vibrantDark = palette.getDarkVibrantColor(default_);
                int muted = palette.getMutedColor(default_);
                int mutedLight = palette.getLightMutedColor(default_);
                int mutedDark = palette.getDarkMutedColor(default_);
                if (vibrant != 0)
                     colors.add(new Pixel(Color.red(vibrant) , Color.green(vibrant) , Color.blue(vibrant)));
                if (vibrantLight != 0)
                    colors.add(new Pixel(Color.red(vibrantLight) , Color.green(vibrantLight) , Color.blue(vibrantLight)));
                if (vibrantDark != 0)
                    colors.add(new Pixel(Color.red(vibrantDark), Color.green(vibrantDark) , Color.blue(vibrantDark)));
                if (muted != 0)
                    colors.add(new Pixel(Color.red(muted) , Color.green(muted) , Color.blue(muted)));
                if (mutedLight != 0)
                    colors.add(new Pixel(Color.red(mutedLight) , Color.green(mutedLight) , Color.blue(mutedLight)));
                if (mutedDark != 0)
                    colors.add(new Pixel(Color.red(mutedDark) , Color.green(mutedDark) , Color.blue(mutedDark)));

                Intent intent = new Intent(palletmaker_firstActivity.this, Save_MakePalletActivity.class);
                intent.putExtra("colorlist", colors);
                ByteArrayOutputStream bStream = new ByteArrayOutputStream();
                image_btmap.compress(Bitmap.CompressFormat.PNG, 100, bStream);
                byte[] byteArray = bStream.toByteArray();
                intent.putExtra("image_show" , byteArray);
                startActivity(intent);


            }
        });
    }


    static ArrayList<Pixel> GetPixelsFromImage(Bitmap image){
        int width = image.getWidth();
        int height = image.getHeight();
        ArrayList<Pixel> L = new ArrayList();
        int red;
        int green;
        int blue;
        Pixel p;
        for (int i=0; i<height; i++){
            for (int j=0; j<width; j++){
                int pixel =image.getPixel(j , i);
                red = Color.red(pixel);
                green = Color.green(pixel);
                blue = Color.blue(pixel);
                p = new Pixel(red, green, blue);
                L.add(p);
            }
        }
        return L;
    }
    ArrayList<Pixel> kmeans(ArrayList<Pixel> L, int K){

        ArrayList<Pixel> Lk = MakeKRandomPixels(K);
        int T = L.size();
        int i = 0;
        int counter = 0;
        while(true){

            assigningMembership(L, Lk, K);


            ArrayList<Pixel> newPalette = newPalette(K, L, Lk);

            String a = toString(Lk);
            String b = toString(newPalette);

            if(a.equals(b)){
                break;
            }

            Lk = newPalette;
            i++;

            if (i==1000){

                break;
            }

        }
        return Lk;
    }
    static ArrayList<Pixel> MakeKRandomPixels(int k){
        ArrayList<Pixel> Rk = new ArrayList<Pixel>();
        Pixel rp = new Pixel(0,0,0);
        for (int i=0; i<k; i++){
            rp.red = (int) (Math.random()*255);
            rp.green = (int) (Math.random()*255);
            rp.blue = (int) (Math.random()*255);
            Rk.add(rp);
            rp = new Pixel(0,0,0);
        }
        return Rk;
    }



    static void assigningMembership(ArrayList<Pixel> L, ArrayList<Pixel> Lk, int K){
        int T = L.size();
        for (int t=0; t<T; t++){
            L.get(t).membership = Lk.get(0);
            for (int k=0; k<K; k++){
                if(Pixel.EuclideanDistance(L.get(t), L.get(t).membership) > Pixel.EuclideanDistance(L.get(t), Lk.get(k))){
                    L.get(t).membership = Lk.get(k);
                }
            }
        }
    }

    static ArrayList<Pixel> newPalette(int K, ArrayList<Pixel> L, ArrayList<Pixel> Lk){
        ArrayList<Pixel> R = MakeKRandomPixels(K);
        int T = L.size();
        for (int k=0; k<K; k++){
            Pixel Avg = new Pixel(0,0,0);
            int avgR = 0;
            int avgG = 0;
            int avgB = 0;
            int total = 0;
            for (int t=0; t<T; t++){
                if (L.get(t).membership==Lk.get(k)){
                    total++;
                    avgR += L.get(t).red;
                    avgG += L.get(t).green;
                    avgB += L.get(t).blue;
                }
            }
            if (total!=0){
                Avg.red = avgR/total;
                Avg.green = avgG/total;
                Avg.blue = avgB/total;

                R.set(k, Avg);
            }
        }
        return R;
    }

    static String toString(ArrayList<Pixel> L){
        String s = "";
        for(int i=0; i<L.size(); i++){
            s+= L.get(i).red + L.get(i).green + L.get(i).blue;
        }
        return s;
    }

}
