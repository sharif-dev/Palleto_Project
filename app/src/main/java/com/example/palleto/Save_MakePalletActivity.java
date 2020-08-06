package com.example.palleto;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.room.Room;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
    public AppDatabase db;
    private String data_to_save = "";
    private String saved_name = null;
    private boolean is_saved = false;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save__make_pallet);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "pallet_db").build();
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
            colors[i].setLayoutParams(new LinearLayout.LayoutParams(100, 100));
            colors[i].setRotation(45);
            colors[i].setRadius(12);
            final String hex = String.format("#%02x%02x%02x", pallet_colores.get(i).red, pallet_colores.get(i).green, pallet_colores.get(i).blue);
            Handler handler = new Handler();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (!data_to_save.equals("")) {
                        data_to_save += "%";
                    }
                    data_to_save += hex;
                }
            });
            color_hex[i].setText(hex);
            color_hex[i].setTextSize(20);
            color_hex[i].setPadding(10, 0, 10, 0);
            color_hex[i].setTextColor(Color.rgb(pallet_colores.get(i).red, pallet_colores.get(i).green, pallet_colores.get(i).blue));
            mlinearlayout.addView(colors[i]);
            linearLayout2.addView(color_hex[i]);

        }
    }

    public void like(View view) {
        if (!is_saved) {
            Context context = getApplicationContext();
            CharSequence text = "Save Pallet first!!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            return;
        }
        try {
            Pallet pallet = db.palletDAO().loadAllByIds(this.saved_name);
            pallet.is_favorite = true;
            db.palletDAO().delete_by_name(this.saved_name);
            db.palletDAO().insert_Pallet(pallet);
        } catch (Exception e) {
        }
    }

    public void save_pallet(View view) {
        Save_Pallet_Dialog save_pallet_dialog = new Save_Pallet_Dialog();
        save_pallet_dialog.show(getSupportFragmentManager(), "SAVE DIALOG");

        // TODO: 8/6/2020 AD
    }


    public void openDialog() {

    }
}
