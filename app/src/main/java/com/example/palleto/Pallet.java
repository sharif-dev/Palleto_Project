package com.example.palleto;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity
public class Pallet {

    @PrimaryKey @NotNull
    public String name;

    @ColumnInfo(name = "code")
    public String code = "";

    @ColumnInfo(name = "is_favorite")
    public boolean is_favorite = false;

}
