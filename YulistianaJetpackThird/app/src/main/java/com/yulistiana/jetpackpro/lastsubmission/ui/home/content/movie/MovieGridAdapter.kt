package com.yulistiana.jetpackpro.lastsubmission.ui.home.content.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.yulistiana.jetpackpro.lastsubmission.BuildConfig
import com.yulistiana.jetpackpro.lastsubmission.R
import com.yulistiana.jetpackpro.lastsubmission.data.source.local.entity.EntityMovies
import com.yulistiana.jetpackpro.lastsubmission.utils.Helper
import com.yulistiana.jetpackpro.lastsubmission.utils.loadFromUrl
import kotlinx.android.synthetic.main.item_row.view.*

class MovieGridAdapter (private val clicked: MovieClicked) :
    PagedListAdapter<EntityMovies, MovieGridAdapter.GridViewHolder>(
        DIFF_CALLBACK
    ) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<EntityMovies>() {
            override fun areItemsTheSame(oldItem: EntityMovies, newItem: EntityMovies): Boolean {
                return oldItem.movieId == newItem.movieId
            }
            override fun areContentsTheSame(oldItem: EntityMovies, newItem: EntityMovies): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: EntityMovies) {
            with(itemView) {
                data.poster?.let {
                    img_poster.loadFromUrl(BuildConfig.BASE_IMAGE + Helper.ENDPOINT + it)
                }
                titles.text = data.name
                clicked.setOnClickListener {
                    this@MovieGridAdapter.clicked.onItemClicked(data)
                }

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder =
        GridViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        )

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }

}