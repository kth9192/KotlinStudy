package com.noname.kotlinstudy.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.noname.kotlinstudy.R;
import com.noname.kotlinstudy.databinding.RecyclerStarBinding;
import com.noname.kotlinstudy.model.People;


public class StarWarsAdapter extends PagedListAdapter<People, StarWarsAdapter.StarWarsViewHolder> {

    public StarWarsAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public StarWarsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_star, parent, false);
        return new StarWarsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StarWarsViewHolder holder, int position) {

        if (holder instanceof StarWarsViewHolder){
            holder.getRecyclerStarBinding().setData(getItem(position));
        }
    }

    private static final DiffUtil.ItemCallback<People> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<People>() {
                @Override
                public boolean areItemsTheSame(
                        @NonNull People oldModel, @NonNull People newModel) {
                    // User properties may have changed if reloaded from the DB, but ID is fixed
                    return (oldModel.getName().equals(newModel.getName()));
                }
                @Override
                public boolean areContentsTheSame(
                        @NonNull People oldModel, @NonNull People newModel) {
                    // NOTE: if you use equals, your object must properly override Object#equals()
                    // Incorrectly returning false here will result in too many animations.
                    return oldModel.equals(newModel);
                }
            };

    static class StarWarsViewHolder extends RecyclerView.ViewHolder {

        private RecyclerStarBinding recyclerStarBinding;

        public StarWarsViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerStarBinding = DataBindingUtil.bind(itemView);
        }

        public RecyclerStarBinding getRecyclerStarBinding() {
            return recyclerStarBinding;
        }

    }
}
