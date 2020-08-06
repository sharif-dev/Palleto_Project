package com.example.palleto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class CollectionActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CollectionAdaptor collectionAdaptor;
    List<String> names ;
    List<String> dates ;
    List<String> des ;
    ArrayList<String> items;
    public AppDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);

        recyclerView = findViewById(R.id.collection_lst);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "pallet_db").allowMainThreadQueries().build();
        List<Pallet> pallets = db.palletDAO().getAll();
        for (Pallet p : pallets) {
            names.add(p.name);
            items.add(p.code);
        }





    }
}
