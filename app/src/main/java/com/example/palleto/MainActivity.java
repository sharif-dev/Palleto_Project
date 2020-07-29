package com.example.palleto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Boolean isfirstrun = getSharedPreferences("PEREFERENCE" , MODE_PRIVATE).getBoolean("isfirstrun" ,true );

        if (isfirstrun){

            Intent intent = new Intent(this , IntroduceActivity.class);
            startActivity(intent);
//            getSharedPreferences("PEREFERENCE" , MODE_PRIVATE).edit().putBoolean("isfirstrun" , false).commit();
        }
    }
}
