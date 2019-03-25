package com.onramp.android.takehome.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;
import android.util.Log;

import com.onramp.android.takehome.models.Recipe;
import com.onramp.android.takehome.repositories.RecipeRepository;

import java.util.List;

public class RecipeViewModel extends ViewModel {
    private static final String TAG = "RecipeViewModel";

    // Repository
    private RecipeRepository mRepo;

    //MutableLiveData Objects
    private MutableLiveData<List<Recipe>> mRecipes;
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();

    //LiveData Objects
    public LiveData<List<Recipe>> getRecipes() { return mRecipes; }
    public LiveData<Boolean> getIsUpdating() { return mIsUpdating; }

    public void init() {
        Log.d(TAG, "init: starts");

        if (mRecipes != null) {
            return;
        }
        mRepo = RecipeRepository.getInstance();
        mRecipes = mRepo.getRecipes();
    }

    // Pretending to add a new recipe to display progressbar widget
    public void addNewValue(final Recipe recipe) {
        Log.d(TAG, "addNewValue: starts");

        mIsUpdating.setValue(true);

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                List<Recipe> currRecipes = mRecipes.getValue();
                currRecipes.add(recipe);
                mRecipes.postValue(currRecipes);
                mIsUpdating.postValue(false);
            }

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }
}
