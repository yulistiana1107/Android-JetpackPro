package com.yulistiana.jetpackpro.lastsubmission.ui.home.content.favorite.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.yulistiana.jetpackpro.lastsubmission.R
import com.yulistiana.jetpackpro.lastsubmission.data.source.local.entity.EntityTvShows
import com.yulistiana.jetpackpro.lastsubmission.ui.detail.DetailKuyActivity
import com.yulistiana.jetpackpro.lastsubmission.ui.home.content.favorite.FavoriteViewModel
import com.yulistiana.jetpackpro.lastsubmission.ui.home.content.tvshow.TvShowGridAdapter
import com.yulistiana.jetpackpro.lastsubmission.ui.home.content.tvshow.TvShowClicked
import com.yulistiana.jetpackpro.lastsubmission.utils.Helper
import com.yulistiana.jetpackpro.lastsubmission.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.empty.*
import kotlinx.android.synthetic.main.fragment_favorite_tv_show.*
import javax.inject.Inject

class FavoriteTvShowFragment : DaggerFragment(), TvShowClicked {

    private lateinit var viewModel: FavoriteViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_tv_show, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configRecycler()

        parentFragment?.let {
            viewModel = ViewModelProvider(it, factory).get(FavoriteViewModel::class.java)
        }
        viewModel.getListFavoriteTvShow().observe(viewLifecycleOwner, Observer {
            if (it != null){
                rv_tvshows_favorite.adapter?.let {adapter ->
                    when (adapter) {
                        is TvShowGridAdapter -> {
                            if (it.isNullOrEmpty()){
                                rv_tvshows_favorite.visibility = GONE
                                EmptyFavoriteTvShow()
                            } else {
                                rv_tvshows_favorite.visibility = VISIBLE
                                adapter.submitList(it)
                                adapter.notifyDataSetChanged()
                            }
                        }
                    }
                }
            }
        })
    }
    private fun configRecycler() {
        rv_tvshows_favorite.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = TvShowGridAdapter(this@FavoriteTvShowFragment)
        }
    }

    private fun EmptyFavoriteTvShow() {
        img_empty.setImageResource(R.drawable.ic_baseline_warning_24)
        img_empty.contentDescription =
            resources.getString(R.string.empty_no_favorite_item_tvshow)
        title_empty.text = resources.getString(R.string.empty_no_favorite_item)
        desc_empty.text =
            resources.getString(R.string.empty_no_favorite_item_tvshow)
        rv_tvshows_favorite_state.visibility = VISIBLE
    }

    override fun onItemClicked(data: EntityTvShows) {
        startActivity(
            Intent(
                context,
                DetailKuyActivity::class.java
            )
                .putExtra(DetailKuyActivity.EXTRA_DATA, data.tvShowId)
                .putExtra(DetailKuyActivity.EXTRA_TYPE, Helper.TYPE_TVSHOWS)
        )
    }

}
