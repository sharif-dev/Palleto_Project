package com.example.palleto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);

        recyclerView = findViewById(R.id.collection_lst);






    }
}
