package com.example.palleto;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.room.Room;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;

public class Make_ownpalletActivity extends AppCompatActivity  implements Save_Pallet_Dialog.SaveDialogListener {

    private CardView c1, c2, c3, c4;
    private TextView h1, h2, h3, h4;//hex
    private LinearLayout layout;
    private ImageView like, save;

    public AppDatabase db;
    public boolean is_saved = false;
    public boolean is_liked = false;
    public String file_name;
    public String file_hex_code = "";

    @Override
    protected void onCreate(Bundle savedInstanceState){
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
        like = findViewById(R.id.favorit_pallet);
        save = findViewById(R.id.save_pallet);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "pallet_db").allowMainThreadQueries().build();

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
                                final String hex1 = String.format("#%02x%02x%02x", r, g, b);
                                h1.setText(hex1);
                                Handler handler = new Handler();
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (file_hex_code.length() != 0) {
                                            file_hex_code += " ";
                                        }
                                        file_hex_code += hex1;
                                    }
                                });
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
                                final String hex2 = String.format("#%02x%02x%02x", r, g, b);
                                h2.setText(hex2);
                                Handler handler = new Handler();
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (file_hex_code.length() != 0) {
                                            file_hex_code += " ";
                                        }
                                        file_hex_code += hex2;
                                    }
                                });
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
                                final String hex3 = String.format("#%02x%02x%02x", r, g, b);
                                h3.setText(hex3);
                                Handler handler = new Handler();
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (file_hex_code.length() != 0) {
                                            file_hex_code += " ";
                                        }
                                        file_hex_code += hex3;
                                    }
                                });
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
                                final String hex4 = String.format("#%02x%02x%02x", r, g, b);
                                h4.setText(hex4);
                                Handler handler = new Handler();
                                handler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (file_hex_code.length() != 0) {
                                            file_hex_code += " ";
                                        }
                                        file_hex_code += hex4;
                                    }
                                });
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


        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Save_Pallet_Dialog exampleDialog = new Save_Pallet_Dialog(file_hex_code);
                exampleDialog.show(getSupportFragmentManager(), "save dialog");
            }
        });
    }

    @Override
    public void applyTexts(String name) {
        file_name = name;
    }
}
