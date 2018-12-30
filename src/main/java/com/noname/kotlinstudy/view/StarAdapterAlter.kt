package com.noname.kotlinstudy.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.noname.kotlinstudy.R
import com.noname.kotlinstudy.databinding.RecyclerStarBinding
import com.noname.kotlinstudy.model.People

class StarAdapterAlter : PagedListAdapter<People, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    private var TAG : String = "StarAdapterAlter"

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<People>() {
            override fun areItemsTheSame(
                oldModel: People, newModel: People
            ): Boolean {
                // User properties may have changed if reloaded from the DB, but ID is fixed
                return oldModel.name == newModel.name
            }

            override fun areContentsTheSame(
                oldModel: People, newModel: People
            ): Boolean {
                // NOTE: if you use equals, your object must properly override Object#equals()
                // Incorrectly returning false here will result in too many animations.
                return oldModel == newModel
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_star, parent, false)
        return StarWarsViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if(holder is StarWarsViewHolder) {
            holder.recyclerStarBinding?.data = getItem(position)
            Log.d(TAG , "아이템의 이름" + getItem(position)?.name)
            holder.recyclerStarBinding?.executePendingBindings()
        }
    }


     private class StarWarsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView.rootView) {

        val recyclerStarBinding: RecyclerStarBinding?

        init {
            recyclerStarBinding = DataBindingUtil.bind(itemView)
        }

    }
}