package com.yulistiana.jetpackpro.lastsubmission.ui.home.content.tvshow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.yulistiana.jetpackpro.lastsubmission.BuildConfig
import com.yulistiana.jetpackpro.lastsubmission.R
import com.yulistiana.jetpackpro.lastsubmission.data.source.local.entity.EntityTvShows
import com.yulistiana.jetpackpro.lastsubmission.utils.Helper
import com.yulistiana.jetpackpro.lastsubmission.utils.loadFromUrl
import kotlinx.android.synthetic.main.item_row.view.*

class TvShowGridAdapter (private val clicked: TvShowClicked) :
    PagedListAdapter<EntityTvShows, TvShowGridAdapter.GridViewHolder>(
        DIFF_CALLBACK
    ) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<EntityTvShows>() {
            override fun areItemsTheSame(oldItem: EntityTvShows, newItem: EntityTvShows): Boolean {
                return oldItem.tvShowId == newItem.tvShowId
            }

            override fun areContentsTheSame(oldItem: EntityTvShows, newItem: EntityTvShows): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: EntityTvShows) {
            with(itemView) {
                data.poster?.let {
                    img_poster.loadFromUrl(BuildConfig.BASE_IMAGE + Helper.ENDPOINT + it)
                }
                titles.text = data.name
                clicked.setOnClickListener {
                    this@TvShowGridAdapter.clicked.onItemClicked(data)
                }

            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder =
        GridViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
        )

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) {
            holder.bind(tvShow)
        }
    }

}