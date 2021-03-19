package com.example.victortang.ichirin.models;

import java.io.Serializable;

public class Drink implements Serializable {

    private String drinkTitle;
    private String drinkInstruction;
    private String drinkImage;

    @Override
    public String toString() {
        return "Drink{" +
                "drinkTitle='" + drinkTitle + '\'' +
                ", drinkInstruction='" + drinkInstruction + '\'' +
                ", drinkImage='" + drinkImage + '\'' +
                '}';
    }

    public Drink(){

    }

    public Drink(String drinkTitle, String drinkInstruction, String drinkImage) {
        this.drinkTitle = drinkTitle;
        this.drinkInstruction = drinkInstruction;
        this.drinkImage = drinkImage;
    }

    public String getDrinkTitle() {
        return drinkTitle;
    }

    public String getDrinkInstruction() {
        return drinkInstruction;
    }

    public String getDrinkImage() {
        return drinkImage;
    }

    public void setDrinkTitle(String drinkTitle) {
        this.drinkTitle = drinkTitle;
    }

    public void setDrinkInstruction(String drinkInstruction) {
        this.drinkInstruction = drinkInstruction;
    }

    public void setDrinkImage(String drinkImage) {
        this.drinkImage = drinkImage;
    }
}