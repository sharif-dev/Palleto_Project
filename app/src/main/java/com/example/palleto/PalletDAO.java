package com.example.palleto;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface PalletDAO {
    @Query("SELECT * FROM pallet")
    List<Pallet> getAll();

    @Query("SELECT * FROM pallet WHERE name = (:name)")
    Pallet loadByName(String name);

    @Query("SELECT * FROM pallet WHERE is_favorite")
    List<Pallet> loadFavorites();

    @Insert
    void insert_Pallet(Pallet pallet);

    @Query("INSERT INTO pallet (name, code, is_favorite) VALUES ((:name), (:code), (:is_favorite))")
    void insert_my_pallet(String name,String code, boolean is_favorite);

    @Delete
    void delete(Pallet pallet);

    @Query("DELETE FROM pallet WHERE name IN (:name) ")
    void delete_by_name(String name);
}