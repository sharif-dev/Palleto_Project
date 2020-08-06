package com.example.palleto;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.room.Room;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static android.R.layout.select_dialog_item;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawelayout;
    private NavigationView navigationView;
    private ActionBarDrawerToggle mToggle;
    private ImageView mcolormeter;
    private ImageView mpalletmaker;
    private ImageView mpopularpallets;
    final String[] dialogoptions={"Take form Camera" , "Choose form Gallery"};
    private static final int PICK_IMAGE = 100;
    private String option = "";
    private int chosen_feature = 0;
    Uri gallery_image;
    public AppDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "pallet_db").build();
        mDrawelayout = findViewById(R.id.drawelayout);
        navigationView = findViewById(R.id.navigation_view);
        navigationView.bringToFront();

        mcolormeter = findViewById(R.id.colormeter);
        mpalletmaker = findViewById(R.id.palletmaker);
        mpopularpallets = findViewById(R.id.owncolor);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this , select_dialog_item , dialogoptions);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose an option");

        builder.setAdapter(adapter , new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    //////////Connect to the camera
                if( which == 0) {
                    option = "camera";
                    Intent mIntent = new Intent();
                    mIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(mIntent , 0);
                }
                /////// connect to the gallery
                if(which == 1)
                {
                option="gallery";

                    Intent gallery = new Intent(Intent.ACTION_PICK , MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                    startActivityForResult(gallery , PICK_IMAGE);

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





        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {


                switch (menuItem.getItemId()) {
                    case R.id.populars_p: {
                        Intent intents = new Intent(MainActivity.this, PopularpalletesActivity.class);
                        startActivity(intents);
                        break;
                    }

                    case R.id.collection: {
                        Intent intent2 = new Intent(MainActivity.this, CollectionActivity.class);
                        startActivity(intent2);
                        break;
                    }


                }
                        return true;

            }
        });

        mcolormeter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chosen_feature = 2;
                a.show();
            }
        });
        mpalletmaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chosen_feature=1;
                a.show();
            }
        });

        mpopularpallets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent third=new Intent(MainActivity.this,Make_ownpalletActivity.class);
                startActivity(third);

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
        Log.i("option is ::::" , option);
        if (option == "camera") {


            Bitmap mbitmap = (Bitmap) data.getExtras().get("data");
            if (mbitmap != null)
            {

                ByteArrayOutputStream bStream = new ByteArrayOutputStream();
                mbitmap.compress(Bitmap.CompressFormat.PNG, 100, bStream);
                byte[] byteArray = bStream.toByteArray();
                if (chosen_feature == 2)
                {
                    Intent secondintent = new Intent(this, ColormeterActivity.class);
                    secondintent.putExtra("bitmapimg", byteArray);
                    startActivity(secondintent);
                }
                if (chosen_feature ==1)
                {
                    Intent msecondintent = new Intent(this, palletmaker_firstActivity.class);
                    msecondintent.putExtra("bitmapimg", byteArray);
                    startActivity(msecondintent);
                }


            }

        }

        if (option == "gallery")
        {
            if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
                gallery_image = data.getData();
                Bitmap photoBmp = null;
                if (gallery_image != null) {
                    try {
                        photoBmp = MediaStore.Images.Media.getBitmap(this.getContentResolver(), gallery_image);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                ByteArrayOutputStream bStream = new ByteArrayOutputStream();
                photoBmp.compress(Bitmap.CompressFormat.PNG, 100, bStream);
                byte[] byteArray = bStream.toByteArray();
                if (chosen_feature == 2)
                {

                    Intent msecondintent = new Intent(this, ColormeterActivity.class);
                    msecondintent.putExtra("bitmapimg", byteArray);
                    startActivity(msecondintent);
                }
                if (chosen_feature ==1)
                {
                    Intent msecondintent = new Intent(this, palletmaker_firstActivity.class);
                    msecondintent.putExtra("bitmapimg", byteArray);
                    startActivity(msecondintent);
                }
            }
    }


    }
}
