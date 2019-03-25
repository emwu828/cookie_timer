package com.onramp.android.takehome.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.onramp.android.takehome.R;
import com.onramp.android.takehome.models.Recipe;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = "RecyclerAdapter";

    // Vars
    private List<Recipe> mRecipes;
    private onRecipeListener mOnRecipeListener;
    private Context mContext;

    // Constructor
    public RecyclerAdapter(Context context, List<Recipe> recipes, onRecipeListener onRecipeListener) {
        mRecipes = recipes;
        mOnRecipeListener = onRecipeListener;
        mContext = context;
    }

    // Interface
    public interface onRecipeListener { void onRecipeClick(int position); }

    // ViewHolder override methods
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.d(TAG, "onCreateViewHolder: starts");

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_listitem, viewGroup, false);
        return new ViewHolder(view, mOnRecipeListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        Log.d(TAG, "onBindViewHolder: starts");

        // Set title
        ((ViewHolder)viewHolder).mTitle.setText(mRecipes.get(i).getTitle());

        // Set Image
        RequestOptions defaultOptions = new RequestOptions()
                .error(R.drawable.ic_launcher_background);
        Glide.with(mContext)
                .setDefaultRequestOptions(defaultOptions)
                .load(mRecipes.get(i).getImageUrl())
                .into(((ViewHolder)viewHolder).mImage);
    }

    @Override
    public int getItemCount() { return mRecipes.size(); }


    // ViewHolder class
    private class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private static final String TAG = "ViewHolder";

        private ImageView mImage;
        private TextView mTitle;
        onRecipeListener mOnRecipeListener;

        public ViewHolder(@NonNull View itemView, onRecipeListener onRecipeListener) {
            super(itemView);

            Log.d(TAG, "ViewHolder: starts");

            mTitle = itemView.findViewById(R.id.list_item_title);
            mImage = itemView.findViewById(R.id.list_item_image);
            mOnRecipeListener = onRecipeListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick: starts");

            mOnRecipeListener.onRecipeClick(getAdapterPosition());
        }
    }
}
