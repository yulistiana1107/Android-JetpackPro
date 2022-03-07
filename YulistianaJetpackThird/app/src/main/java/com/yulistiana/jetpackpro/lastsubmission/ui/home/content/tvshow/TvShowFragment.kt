package com.yulistiana.jetpackpro.lastsubmission.ui.home.content.tvshow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.yulistiana.jetpackpro.lastsubmission.R
import com.yulistiana.jetpackpro.lastsubmission.data.source.local.entity.EntityTvShows
import com.yulistiana.jetpackpro.lastsubmission.ui.detail.DetailKuyActivity
import com.yulistiana.jetpackpro.lastsubmission.ui.home.MainViewModel
import com.yulistiana.jetpackpro.lastsubmission.utils.Helper.TYPE_TVSHOWS
import com.yulistiana.jetpackpro.lastsubmission.viewmodel.ViewModelFactory
import com.yulistiana.jetpackpro.lastsubmission.vo.Status
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_tv_show.*
import kotlinx.android.synthetic.main.progress_bar_theme.*
import javax.inject.Inject

class TvShowFragment : DaggerFragment(), TvShowClicked {

    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var factory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configRecycler()

        activity?.let { viewModel(it) }

        viewModel.getListOnTheAirTvShows().observe(viewLifecycleOwner, Observer { listTvShow ->
            if (listTvShow != null) {
                when (listTvShow.status) {
                    Status.LOADING -> progress_bar_theme.visibility = View.VISIBLE
                    Status.SUCCESS -> {
                        progress_bar_theme.visibility = View.GONE
                        rv_tvshows.adapter?.let { adapter ->
                            when (adapter) {
                                is TvShowGridAdapter -> {
                                    adapter.submitList(listTvShow.data)
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

    private fun configRecycler() {
        rv_tvshows.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = TvShowGridAdapter(this@TvShowFragment)
        }
    }

    private fun viewModel(fragmentActivity: FragmentActivity) {
        fragmentActivity.let {
            viewModel = ViewModelProvider(
                it,
                factory
            ).get(MainViewModel::class.java)
        }
    }

    override fun onItemClicked(data: EntityTvShows) {
        startActivity(
            Intent(
                context,
                DetailKuyActivity::class.java
            )
                .putExtra(DetailKuyActivity.EXTRA_DATA, data.tvShowId)
                .putExtra(DetailKuyActivity.EXTRA_TYPE, TYPE_TVSHOWS)
        )
    }

}
