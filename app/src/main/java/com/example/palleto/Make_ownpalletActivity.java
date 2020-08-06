package com.example.palleto;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

public class Make_ownpalletActivity extends AppCompatActivity {

    private CardView c1,c2,c3,c4;
    private TextView h1,h2,h3,h4;//hex
    private LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_ownpallet);
        c1 = findViewById(R.id.first_diamond);
        c2 = findViewById(R.id.second_diamond);
        c3 = findViewById(R.id.third_diamond);
        c4 = findViewById(R.id.forth_diamond);
        h1 = findViewById(R.id.first_diamond_hex);
        h2 = findViewById(R.id.second_diamond_hex);
        h3 = findViewById(R.id.third_diamond_hex);
        h4 = findViewById(R.id.forth_diamond_hex);
        layout = findViewById(R.id.dynamic_bg);

        c1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                ColorPickerDialogBuilder
                        .with(Make_ownpalletActivity.this)
                        .setTitle("Choose color")
                        .initialColor(getApplicationContext().getColor(R.color.colorPrimary))
                        .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                        .density(17)
                        .showColorPreview(true)
                        .setOnColorSelectedListener(new OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int selectedColor) {
                            }
                        })
                        .setPositiveButton("ok", new ColorPickerClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                                c1.setCardBackgroundColor(selectedColor);
                                int r = Color.red(selectedColor);
                                int g = Color.green(selectedColor);
                                int b = Color.blue(selectedColor);
                                h1.setText(String.format("#%02x%02x%02x", r,g,b));
                            }
                        })
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .build()
                        .show();
            }
        });


        c2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                ColorPickerDialogBuilder
                        .with(Make_ownpalletActivity.this)
                        .setTitle("Choose color")
                        .initialColor(getApplicationContext().getColor(R.color.colorPrimary))
                        .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                        .density(17)
                        .showColorPreview(true)
                        .setOnColorSelectedListener(new OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int selectedColor) {
                            }
                        })
                        .setPositiveButton("ok", new ColorPickerClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                                c2.setCardBackgroundColor(selectedColor);
                                int r = Color.red(selectedColor);
                                int g = Color.green(selectedColor);
                                int b = Color.blue(selectedColor);
                                h2.setText(String.format("#%02x%02x%02x", r,g,b));
                            }
                        })
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .build()
                        .show();
            }
        });


        c3.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                ColorPickerDialogBuilder
                        .with(Make_ownpalletActivity.this)
                        .setTitle("Choose color")
                        .initialColor(getApplicationContext().getColor(R.color.colorPrimary))
                        .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                        .density(17)
                        .showColorPreview(true)
                        .setOnColorSelectedListener(new OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int selectedColor) {
                            }
                        })
                        .setPositiveButton("ok", new ColorPickerClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                                c3.setCardBackgroundColor(selectedColor);
                                int r = Color.red(selectedColor);
                                int g = Color.green(selectedColor);
                                int b = Color.blue(selectedColor);
                                h3.setText(String.format("#%02x%02x%02x", r,g,b));
                            }
                        })
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .build()
                        .show();
            }
        });



        c4.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                ColorPickerDialogBuilder
                        .with(Make_ownpalletActivity.this)
                        .setTitle("Choose color")
                        .initialColor(getApplicationContext().getColor(R.color.colorPrimary))
                        .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                        .density(17)
                        .showColorPreview(true)
                        .setOnColorSelectedListener(new OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int selectedColor) {
                            }
                        })
                        .setPositiveButton("ok", new ColorPickerClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                                c4.setCardBackgroundColor(selectedColor);
                                int r = Color.red(selectedColor);
                                int g = Color.green(selectedColor);
                                int b = Color.blue(selectedColor);
                                h4.setText(String.format("#%02x%02x%02x", r,g,b));
                            }
                        })
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .build()
                        .show();
            }
        });
    }
}
