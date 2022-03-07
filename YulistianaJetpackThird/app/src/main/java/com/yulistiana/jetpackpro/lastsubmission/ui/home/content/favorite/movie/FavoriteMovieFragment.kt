package com.yulistiana.jetpackpro.lastsubmission.ui.home.content.favorite.movie

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
import com.yulistiana.jetpackpro.lastsubmission.data.source.local.entity.EntityMovies
import com.yulistiana.jetpackpro.lastsubmission.ui.detail.DetailKuyActivity
import com.yulistiana.jetpackpro.lastsubmission.ui.home.MainViewModel
import com.yulistiana.jetpackpro.lastsubmission.ui.home.content.favorite.FavoriteViewModel
import com.yulistiana.jetpackpro.lastsubmission.ui.home.content.movie.MovieGridAdapter
import com.yulistiana.jetpackpro.lastsubmission.ui.home.content.movie.MovieClicked
import com.yulistiana.jetpackpro.lastsubmission.utils.Helper
import com.yulistiana.jetpackpro.lastsubmission.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.empty.*
import kotlinx.android.synthetic.main.fragment_favorite_movie.*
import javax.inject.Inject

class FavoriteMovieFragment : DaggerFragment(), MovieClicked {

    private lateinit var viewModel: FavoriteViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configRecycler()

        parentFragment?.let {
            viewModel = ViewModelProvider(it, factory).get(FavoriteViewModel::class.java)
        }
        viewModel.getListFavoriteMovie().observe(viewLifecycleOwner, Observer {
            if (it != null){
                rv_movies_favorite.adapter?.let {adapter ->
                    when (adapter) {
                        is MovieGridAdapter -> {
                            if (it.isNullOrEmpty()){
                                rv_movies_favorite.visibility = GONE
                                EmptyFavoriteMovie()
                            } else {
                                rv_movies_favorite.visibility = VISIBLE
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
        rv_movies_favorite.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = MovieGridAdapter(this@FavoriteMovieFragment)
        }
    }

    private fun EmptyFavoriteMovie() {
        img_empty.setImageResource(R.drawable.ic_baseline_warning_24)
        img_empty.contentDescription =
            resources.getString(R.string.empty_no_favorite_item_movie)
        title_empty.text = resources.getString(R.string.empty_no_favorite_item)
        desc_empty.text =
            resources.getString(R.string.empty_no_favorite_item_movie)
        favorite_movie_empty.visibility = VISIBLE
    }

    override fun onItemClicked(data: EntityMovies) {
        startActivity(
            Intent(
                context,
                DetailKuyActivity::class.java
            )
                .putExtra(DetailKuyActivity.EXTRA_DATA, data.movieId)
                .putExtra(DetailKuyActivity.EXTRA_TYPE, Helper.TYPE_MOVIES)
        )
    }

}
