package br.com.devteam.spotmusick.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.devteam.spotmusick.R
import br.com.devteam.spotmusick.view.adapter.SearchAdapter
import br.com.devteam.spotmusick.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    private val viewModel: SearchViewModel by lazy {
        ViewModelProvider(this).get(SearchViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        configureRecyclerView()
        showTracks()
    }

    private fun configureRecyclerView() {
        rvTracks.layoutManager = LinearLayoutManager(this)
    }

    private fun showTracks() {
        viewModel.result.observe(this, Observer { tracks ->
            val adapter = SearchAdapter(tracks)
            rvTracks.adapter = adapter
        })

        viewModel.discovery("metallica")
    }
}
