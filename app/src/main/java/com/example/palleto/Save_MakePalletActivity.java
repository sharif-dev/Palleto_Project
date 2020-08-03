package com.example.palleto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class Save_MakePalletActivity extends AppCompatActivity {
    View color_1,color_2,color_3,color_4,color_5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save__make_pallet);
        color_1 = findViewById(R.id.color_1);
        color_2 = findViewById(R.id.color_2);
        color_3 = findViewById(R.id.color_3);
        color_4 = findViewById(R.id.color_4);
        color_5 = findViewById(R.id.color_5);

    }
}
