package com.example.palleto;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

import static android.R.layout.select_dialog_item;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawelayout;
    private ActionBarDrawerToggle mToggle;
    private ImageView mcolormetet;
    final String[] dialogoptions={"Take form Camera" , "Choose form Gallery"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawelayout = findViewById(R.id.drawelayout);
        mcolormetet = findViewById(R.id.colormeter);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this , select_dialog_item , dialogoptions);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose an option");

        builder.setAdapter(adapter , new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    //Conect to the camera
                if( which == 0) {

                    Intent mIntent = new Intent();
                    mIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(mIntent , 0);
                }
                // connect to the gallery
                if(which == 1)
                {


                }
            }
        });
        final AlertDialog a = builder.create();


        mToggle = new ActionBarDrawerToggle(this , mDrawelayout ,R.string.open, R.string.close);
        mDrawelayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Boolean isfirstrun = getSharedPreferences("PEREFERENCE" , MODE_PRIVATE).getBoolean("isfirstrun" ,true );

        if (isfirstrun){

            Intent intent = new Intent(this , IntroduceActivity.class);
            startActivity(intent);
            getSharedPreferences("PEREFERENCE" , MODE_PRIVATE).edit().putBoolean("isfirstrun" , false).commit();
        }





        mcolormetet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (mToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap mbitmap = (Bitmap) data.getExtras().get("data");
        if (mbitmap != null)
        {
            ByteArrayOutputStream bStream = new ByteArrayOutputStream();
            mbitmap.compress(Bitmap.CompressFormat.PNG, 100, bStream);
            byte[] byteArray = bStream.toByteArray();


            Intent secondintent = new Intent(this , ColormeterActivity.class);
            secondintent.putExtra("bitmapimg" , byteArray);

            Log.i("BITMAPPPPPPPPPPPP///" , String.valueOf(byteArray));

            startActivity(secondintent);

        }

    }
}
