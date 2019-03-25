package com.onramp.android.takehome;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.onramp.android.takehome.models.Recipe;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = "DetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: starts");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.detail_toolbar);
        setSupportActionBar(toolbar);

        // Instantiate new recipe object from passed intent extra
        Recipe recipe = getIntent().getParcelableExtra("recipe");

        // Inflate layout and UI components if recipe object is not null
        if (recipe != null) {

            String recipeTitle = recipe.getTitle();
            String recipeIngredients = recipe.getIngredients();
            String recipeInstructions = recipe.getInstructions();
            String imageUrl = recipe.getImageUrl();

            TextView title = findViewById(R.id.detail_title);
            title.setText(recipeTitle);

            TextView ingredients = findViewById(R.id.detail_ingredients);
            ingredients.setText(recipeIngredients);

            TextView instructions = findViewById(R.id.detail_instructions);
            instructions.setText(recipeInstructions);

            ImageView image = findViewById(R.id.detail_image);
            Glide.with(this)
                    .asBitmap()
                    .load(imageUrl)
                    .into(image);
        }

        // Pass new intent to fab icon's clickListener to start TimerActivity
        FloatingActionButton fab = findViewById(R.id.detail_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), TimerActivity.class));
            }
        });

        // Enable back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
