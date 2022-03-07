package com.yulistiana.jetpackpro.lastsubmission.ui.home.content.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.yulistiana.jetpackpro.lastsubmission.R
import com.yulistiana.jetpackpro.lastsubmission.data.source.local.entity.EntityMovies
import com.yulistiana.jetpackpro.lastsubmission.ui.detail.DetailKuyActivity
import com.yulistiana.jetpackpro.lastsubmission.ui.home.MainViewModel
import com.yulistiana.jetpackpro.lastsubmission.utils.Helper.TYPE_MOVIES
import com.yulistiana.jetpackpro.lastsubmission.viewmodel.ViewModelFactory
import com.yulistiana.jetpackpro.lastsubmission.vo.Status
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.progress_bar_theme.*
import javax.inject.Inject

class MovieFragment : DaggerFragment(), MovieClicked {

    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configRecycler()

        activity?.let { viewModel(it) }

        viewModel.getListNowPlayingMovies().observe(viewLifecycleOwner, Observer { listMovie ->
            if (listMovie != null) {
                when (listMovie.status) {
                    Status.LOADING -> progress_bar_theme.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        progress_bar_theme.visibility = View.GONE
                        rv_movies.adapter?.let { adapter ->
                            when (adapter) {
                                is MovieGridAdapter -> {
                                    adapter.submitList(listMovie.data)
                                    adapter.notifyDataSetChanged()
                                }
                            }
                        }
                    }
                    Status.ERROR -> {
                        progress_bar_theme.visibility = View.GONE
                        Toast.makeText(context, "Check your internet connection", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

    }

    private fun viewModel(fragmentActivity: FragmentActivity) {
        viewModel = ViewModelProvider(fragmentActivity, factory).get(MainViewModel::class.java)
    }

    private fun configRecycler() {
        rv_movies.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = MovieGridAdapter(this@MovieFragment)
        }
    }

    override fun onItemClicked(data: EntityMovies) {
        startActivity(
            Intent(
                context,
                DetailKuyActivity::class.java
            )
                .putExtra(DetailKuyActivity.EXTRA_DATA, data.movieId)
                .putExtra(DetailKuyActivity.EXTRA_TYPE, TYPE_MOVIES)
        )
    }

}
