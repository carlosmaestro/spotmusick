package br.com.devteam.spotmusick.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import br.com.devteam.spotmusick.R
import br.com.devteam.spotmusick.domain.Track
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_track_detail.*

class TrackDetail : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track_detail)

        val track: Track = intent.getSerializableExtra("track") as Track

        tvTitle.text = track.name
        var subtitle = mutableListOf<String>()
        track.artists.map { subtitle.add(it.name!!)  }

        tvSubtitle.text = subtitle.joinToString(", ")

        Picasso.get().load(track.album.images!![0].url).into(ivAlbum);
    }

}
