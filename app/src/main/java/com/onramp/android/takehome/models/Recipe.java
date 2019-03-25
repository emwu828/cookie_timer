package com.onramp.android.takehome.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Recipe implements Parcelable {

    // Vars
    private String title;
    private String imageUrl;
    private String ingredients;
    private String instructions;

    public Recipe(String title, String imageUrl, String ingredients, String instructions) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    protected Recipe(Parcel in) {
        title = in.readString();
        imageUrl = in.readString();
        ingredients = in.readString();
        instructions = in.readString();
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    public String getTitle() { return title; }
    public String getImageUrl() { return imageUrl; }
    public String getIngredients() { return ingredients; }
    public String getInstructions() { return instructions; }

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(imageUrl);
        dest.writeString(ingredients);
        dest.writeString(instructions);
    }
}
