package com.yulistiana.jetpackpro.lastsubmission.ui.detail

import android.graphics.Color
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.yulistiana.jetpackpro.lastsubmission.BuildConfig
import com.yulistiana.jetpackpro.lastsubmission.R
import com.yulistiana.jetpackpro.lastsubmission.data.source.local.entity.EntityMovies
import com.yulistiana.jetpackpro.lastsubmission.data.source.local.entity.EntityTvShows
import com.yulistiana.jetpackpro.lastsubmission.utils.Helper
import com.yulistiana.jetpackpro.lastsubmission.utils.Helper.TYPE_MOVIES
import com.yulistiana.jetpackpro.lastsubmission.utils.Helper.TYPE_TVSHOWS
import com.yulistiana.jetpackpro.lastsubmission.utils.loadFromUrl
import com.yulistiana.jetpackpro.lastsubmission.viewmodel.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_detail_kuy.*
import kotlinx.android.synthetic.main.detail_content.*
import javax.inject.Inject

class DetailKuyActivity : DaggerAppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_TYPE = "extra_type"

        @StringRes
        val TAB_TITLES = intArrayOf(
            R.string.recommended_for_you
        )
    }

    private lateinit var viewModel: DetailViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_kuy)

        setupToolbar()

        viewModel = ViewModelProvider(
            this@DetailKuyActivity,
            factory
        ).get(DetailViewModel::class.java)

        val id = intent.getIntExtra(EXTRA_DATA, 0)
        val type = intent.getStringExtra(EXTRA_TYPE)

        if (type.equals(TYPE_MOVIES, ignoreCase = true)) {
            setupToolbarTitle(resources.getString(R.string.toolbar_title_movie))
            viewmodelDetailMovie(id)

        } else if (type.equals(TYPE_TVSHOWS, ignoreCase = true)) {
            setupToolbarTitle(resources.getString(R.string.toolbar_title_tvshow))
            viewmodelDetailTvShow(id)
        }

    }

    private fun viewmodelDetailMovie(movieId: Int) {
        viewModel.getMovieDetail(movieId).observe(this, Observer {
            display(it, null)
        })
    }

    private fun viewmodelDetailTvShow(tvShowId: Int) {
        viewModel.getTvShowDetail(tvShowId).observe(this, Observer {
            it?.let {
                display(null, it)
            }
        })
    }

    private fun display(movie: EntityMovies?, tvShow: EntityTvShows?) {
        val urlImage = movie?.poster ?: tvShow?.poster
        val urlHighlight = movie?.imgBackground ?: tvShow?.imgBackground
        val statusFavorite = movie?.isFavorite ?: tvShow?.isFavorite

        tv_title.text = movie?.name ?: tvShow?.name
        tv_detail.text = movie?.desc ?: tvShow?.desc
        text_rate.text = movie?.rate?.toString() ?: tvShow?.rate.toString()

        statusFavorite?.let { status ->
            setFavoriteActivity(status)
        }

        img_poster.loadFromUrl(BuildConfig.BASE_IMAGE + Helper.ENDPOINT + urlImage)
        img_detail_hightlight.loadFromUrl(BuildConfig.BASE_IMAGE + Helper.ENDPOINT_BACKGROUND + urlHighlight)

        favorite.setOnClickListener {
            setFavorite(movie, tvShow)
        }

    }

    private fun setFavoriteActivity(status: Boolean){
        if (status) {
            favorite.setImageResource(R.drawable.ic_favorite_true)
        } else {
            favorite.setImageResource(R.drawable.ic_favorite_false)
        }
    }

    private fun showSnackBar(msg: String) {
        Snackbar.make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_SHORT).show()
    }

    private fun setFavorite(movie: EntityMovies?, tvShow: EntityTvShows?) {
        if (movie != null) {
            if (movie.isFavorite){
                showSnackBar("${movie.name} Removed from favorite")
            }else {
                showSnackBar("${movie.name} Added to favorite")
            }
            viewModel.setFavoriteMovies(movie)
        } else {
            if (tvShow != null) {
                if (tvShow.isFavorite){
                    showSnackBar("${tvShow.name} Removed from favorite")
                }else {
                    showSnackBar("${tvShow.name} Added to favorite")
                }
                viewModel.setFavoriteTvShows(tvShow)
            }
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        collapsing.setExpandedTitleColor(Color.TRANSPARENT)
    }

    private fun setupToolbarTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}
