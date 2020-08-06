package com.example.palleto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pallete implements Serializable {

    private String date;
    private String Description;
    private Boolean is_liked;
    private String name;
    private String[] colors;

    public Pallete(String name, String[] colors , Boolean like) {

        this.name = name;
        this.colors = colors;
        this.is_liked = like;
    }

    public Boolean getIs_liked() {
        return is_liked;
    }

    public void setIs_liked(Boolean is_liked) {
        this.is_liked = is_liked;
    }

    public String[] getColors() {
        return colors;
    }

    public void setColors(String[] colors) {
        this.colors = colors;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
