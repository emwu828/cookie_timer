package com.onramp.android.takehome;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.onramp.android.takehome.adapters.RecyclerAdapter;
import com.onramp.android.takehome.models.Recipe;
import com.onramp.android.takehome.viewmodels.RecipeViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.onRecipeListener {

    private static final String TAG = "MainActivity";

    // UI Components
    private FloatingActionButton mFab;
    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;

    // Vars
    private RecyclerAdapter mAdapter;

    // ViewModels
    private RecipeViewModel mRecipeViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: starts");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        // Inflate UI components
        mFab = findViewById(R.id.main_fab);
        mRecyclerView = findViewById(R.id.main_recycler_view);
        mProgressBar = findViewById(R.id.main_progress_bar);

        // Instantiate new RecipeViewModel
        mRecipeViewModel = ViewModelProviders.of(this).get(RecipeViewModel.class);
        mRecipeViewModel.init();

        // Observe Recipe dataSet state
        mRecipeViewModel.getRecipes().observe(this, new Observer<List<Recipe>>() {
            @Override
            public void onChanged(@Nullable List<Recipe> recipes) {
                mAdapter.notifyDataSetChanged();
            }
        });

        // Observe progress bar state
        mRecipeViewModel.getIsUpdating().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if(aBoolean){ showProgressBar(); }
                else{ hideProgressBar(); }
            }
        });

        // Pretend to add new cookie recipe item
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mRecipeViewModel.addNewValue(
                        new Recipe(
                                "New Cookie Recipe",
                                "https://assets.bonappetit.com/photos/57add7e5f1c801a1038bcc44/16:9/w_2056,c_limit/11112015-WEB-SHOOT-037.jpg",
                                "New Ingredients",
                                "New Instructions"
                        )
                );
            }
        });

        // Initialize RecyclerView
        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: starts");

        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new RecyclerAdapter(this, mRecipeViewModel.getRecipes().getValue(), this);
        mRecyclerView.setAdapter(mAdapter);
    }

    // Progress bar visibility methods
    private void showProgressBar(){
        mProgressBar.setVisibility(View.VISIBLE);
    }
    private void hideProgressBar(){
        mProgressBar.setVisibility(View.GONE);
    }

    // onRecipeClick method
    @Override
    public void onRecipeClick(int position) {
        Log.d(TAG, "onRecipeClick: clicked");

        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("recipe", mRecipeViewModel.getRecipes().getValue().get(position));
        startActivity(intent);
    }

    // Menu override methods
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) { return true; }
        return super.onOptionsItemSelected(item);
    }
}
