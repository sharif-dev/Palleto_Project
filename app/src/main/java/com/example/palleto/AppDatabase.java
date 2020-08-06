package com.example.palleto;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Pallet.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PalletDAO palletDAO();
}