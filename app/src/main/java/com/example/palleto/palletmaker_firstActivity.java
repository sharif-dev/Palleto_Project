package com.example.palleto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palletmaker_first);
        kmeanalgorithme = findViewById(R.id.k_mean);
        mediancutalgorithme = findViewById(R.id.meadian_cut);

        image_ = findViewById(R.id.image_one);
        count = findViewById(R.id.color_count);

        byte[] byteArray = this.getIntent().getByteArrayExtra("bitmapimg");
        image_btmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        image_.setImageBitmap(image_btmap);

        kmeanalgorithme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Pixel> L = GetPixelsFromImage(image_btmap);
                int k = Integer.valueOf(String.valueOf(count.getText()));

                ArrayList<Pixel> Lk = kmeans(L, k);
                Log.i("hereeeeeeee!!!!" , String.valueOf(Lk.indexOf(0)));
//
//                color_1.setBackgroundColor(Color.rgb(Lk.get(0).red , Lk.get(0).green , Lk.get(0).blue));
//                color_2.setBackgroundColor(Color.rgb(Lk.get(1).red , Lk.get(1).green , Lk.get(1).blue));
//                color_3.setBackgroundColor(Color.rgb(Lk.get(2).red , Lk.get(2).green , Lk.get(2).blue));
//                color_4.setBackgroundColor(Color.rgb(Lk.get(3).red , Lk.get(3).green , Lk.get(3).blue));
//                color_5.setBackgroundColor(Color.rgb(Lk.get(4).red , Lk.get(4).green , Lk.get(4).blue));



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
        System.out.println("Loading pixels...");
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
        System.out.println("All pixels loaded!");
        return L;
    }
    static ArrayList<Pixel> kmeans(ArrayList<Pixel> L, int K){
        ArrayList<Pixel> Lk = MakeKRandomPixels(K);
        int T = L.size();
        int i = 0;
        int counter = 0;
        while(true){
            System.out.println("Itération n°"+i);

            assigningMembership(L, Lk, K);


            ArrayList<Pixel> newPalette = newPalette(K, L, Lk);

            String a = toString(Lk);
            String b = toString(newPalette);

            if(a.equals(b)){
                System.out.println("Convergence");
                break;
            }

            Lk = newPalette;
            i++;

            if (i==1000){
                System.out.println("Limite atteinte ! ************\n Voici Lk");
                PrintPixelsList(Lk);
                break;
            }

        }
        System.out.println("***************************");
        PrintPixelsList(Lk);
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



    static void PrintPixelsList(ArrayList<Pixel> L){
        Pixel p;
//        Log.i("skjvhkdjshbkhkfj" , String.valueOf(L.size()));
        for (int i=0; i<L.size(); i++){
            p = L.get(i);
            System.out.format("Red : %d --- Green : %d --- Blue : %d\n", p.red, p.green, p.blue);
        }
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
