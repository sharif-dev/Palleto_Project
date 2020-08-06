package com.example.palleto;

import java.util.ArrayList;

public class Pallete {

    private String date;
    private String name;
    private ArrayList<String> colors;
    private String Description;

    public Pallete(String name, String date, ArrayList<String> colors, String Description) {
        this.date = date;
        this.name = name;
        this.colors = colors;
        this.Description=Description;
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

    public ArrayList<String> getColors() {
        return colors;
    }

    public void setColors(ArrayList<String> colors) {
        this.colors = colors;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
