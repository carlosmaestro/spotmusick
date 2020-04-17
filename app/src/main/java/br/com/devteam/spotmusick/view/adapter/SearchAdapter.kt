package br.com.devteam.spotmusick.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.devteam.spotmusick.R
import br.com.devteam.spotmusick.domain.Track
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.track_card_list_item.view.ivPoster
import kotlinx.android.synthetic.main.track_card_list_item.view.tvTitle
import kotlinx.android.synthetic.main.track_list_lateral_item.view.*

class SearchAdapter(private val dataSet: List<Track>) :

    RecyclerView.Adapter<SearchAdapter.TrackViewHolderCard>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolderCard {
        var view: View? = null
        if (viewType == 1) {
            view =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.track_card_list_item, parent, false)
        } else {
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.track_list_lateral_item, parent, false)
        }

        var viewHolder: TrackViewHolderCard? = null

        if (viewType == 1) {
            viewHolder = TrackViewHolderCard(view)
        } else {
            viewHolder = TrackViewHolder(view)
        }

        return viewHolder
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) {
            // TODO: Remove after test
            return 2
        } else {
            return 2
        }
    }

    override fun onBindViewHolder(holder: TrackViewHolderCard, position: Int) {
        val track = dataSet[position]

        holder.title.text = track.name

        if (holder is TrackViewHolder) {
            holder.overview.text = track.type
        }

        if (track.album.images?.get(0)?.url != null) {
            Picasso.get().load(track.album.images!![0].url).into(holder.poster);
        }
    }

    open class TrackViewHolderCard(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val poster: ImageView = itemView.ivPoster
        val title: TextView = itemView.tvTitle
    }

    class TrackViewHolder(itemView: View) : TrackViewHolderCard(itemView) {
        val overview: TextView = itemView.tvOverview
    }

}