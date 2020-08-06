package com.example.palleto;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class Save_MakePalletActivity extends AppCompatActivity implements Save_Pallet_Dialog.SaveDialogListener {
    ArrayList<Pixel> pallet_colores;
    CardView[] colors;
    TextView[] color_hex;

    LinearLayout mlinearlayout;
    LinearLayout linearLayout2;
    ImageView imageView;
    ImageView save_btn;
    ImageView like_btn;

    public AppDatabase db;
    public boolean is_saved = false;
    public boolean is_liked = false;
    public String file_name;
    public String file_hex_code = "";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "pallet_db").allowMainThreadQueries().build();

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


        for (int i = 0; i < colors.length; i++) {

            colors[i] = new CardView(this);
            color_hex[i] = new TextView(this);
            colors[i].setBackgroundColor(Color.rgb(pallet_colores.get(i).red, pallet_colores.get(i).green, pallet_colores.get(i).blue));
            colors[i].setLayoutParams(new LinearLayout.LayoutParams(90, 90));
            colors[i].setRotation(45);
            colors[i].setRadius(12);
            final String hex = String.format("#%02x%02x%02x", pallet_colores.get(i).red, pallet_colores.get(i).green, pallet_colores.get(i).blue);
            Handler handler = new Handler();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (file_hex_code.length() != 0) {
                        file_hex_code += " ";
                    }
                    file_hex_code += hex;
                }
            });
            color_hex[i].setText(hex);
            color_hex[i].setTextSize(15);
            color_hex[i].setPadding(10, 0, 10, 0);
            color_hex[i].setTextColor(Color.rgb(pallet_colores.get(i).red, pallet_colores.get(i).green, pallet_colores.get(i).blue));
            mlinearlayout.addView(colors[i]);
            linearLayout2.addView(color_hex[i]);

        }
    }

    public void like(View view) {
        if (!is_saved) {
            Context context = getApplicationContext();
            CharSequence text = "Save Palet First!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }
        Pallet pallet = db.palletDAO().loadByName(file_name);
        pallet.is_favorite = !is_liked;
        db.palletDAO().delete_by_name(file_name);
        db.palletDAO().insert_Pallet(pallet);
        is_liked = !is_liked;
    }

    public void save_pallet(View view) {
        openDialog();
        is_saved = true;
    }

    public void openDialog() {
        Save_Pallet_Dialog exampleDialog = new Save_Pallet_Dialog(file_hex_code);
        exampleDialog.show(getSupportFragmentManager(), "save dialog");
    }

    @Override
    public void applyTexts(String name) {
//        System.out.println("\n\n\n&&&&&&&&&&&&&&&&\n\n\n");
        file_name = name;
    }
}
