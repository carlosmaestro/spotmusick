package br.com.devteam.spotmusick.view.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import br.com.devteam.spotmusick.R
import br.com.devteam.spotmusick.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val viewModel: MovieViewModel by lazy {
        ViewModelProvider(this). get(MovieViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        showMovies()
    }

    private fun showMovies() {
        viewModel.result.observe(this, Observer { movies ->
            var str = ""
            movies.forEach { m -> str = str.plus("\n\nFilme: ${m.title}\nPoster: ${m.poster}") }
            tvMovies.text = str
        })

        viewModel.discovery()
    }

}
