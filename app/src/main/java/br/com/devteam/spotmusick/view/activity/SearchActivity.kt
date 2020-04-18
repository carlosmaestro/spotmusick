package br.com.devteam.spotmusick.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
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
            val adapter = SearchAdapter(tracks) {
                val intentSpotifyTrack = Intent("SPOTMUSICK_TRACK")
                intentSpotifyTrack.addCategory("SPOTMUSICK_TRACK_DETAIL")
                intentSpotifyTrack.putExtra("track", it)
                startActivity(intentSpotifyTrack)
            }
            rvTracks.adapter = adapter
        })


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.searchbar, menu)
        val searchItem = menu.findItem(R.id.app_bar_search)
        if (searchItem != null) {
            val searchView = searchItem.actionView as SearchView
//            val editext = searchView.findViewById<EditText>(android.widget.SearchView.NO_ID)

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query != null) {
                        viewModel.discovery(query)
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
//                    if(newText!!.isNotEmpty()){
//                        val search = newText.toLowerCase()
//
//                    }else{
//                    }
                    return true
                }

            })
        }

        return super.onCreateOptionsMenu(menu)
    }
}
