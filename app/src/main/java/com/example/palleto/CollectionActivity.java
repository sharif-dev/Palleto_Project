package com.example.palleto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class CollectionActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CollectionAdaptor collectionAdaptor;
    List<String> names = new ArrayList<>() ;

    ArrayList<Pallete> items_pallet=new ArrayList<Pallete>() ;

    ArrayList<String> items=new ArrayList<>();
    public AppDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);

        recyclerView = findViewById(R.id.collection_lst);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "pallet_db").allowMainThreadQueries().build();
        List<Pallet> pallets = db.palletDAO().getAll();
        for (Pallet p : pallets)
        {

            names.add(p.name);
            items.add(p.code);
            String[] hex_codes = p.code.split(" ");
            items_pallet.add(new Pallete(p.name , hex_codes , p.is_favorite));


        }

        collectionAdaptor = new CollectionAdaptor(CollectionActivity.this ,items_pallet);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(collectionAdaptor);





    }
}
