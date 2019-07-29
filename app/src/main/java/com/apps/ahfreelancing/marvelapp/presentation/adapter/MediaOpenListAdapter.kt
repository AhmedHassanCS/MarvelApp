package com.apps.ahfreelancing.marvelapp.presentation.adapter

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.apps.ahfreelancing.marvelapp.R
import com.apps.ahfreelancing.marvelapp.domain.model.MediaModel
import com.squareup.picasso.Picasso

/**
 * Created by Ahmed Hassan on 7/29/2019.
 */
class MediaOpenListAdapter (val media: List<MediaModel>, private val context: Context) : RecyclerView.Adapter<MediaOpenListAdapter.MediaHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MediaHolder {
        return MediaHolder(LayoutInflater.from(context).inflate(R.layout.item_media_open, viewGroup, false))
    }

    override fun onBindViewHolder(holder: MediaHolder, index: Int) {
        super.onViewAttachedToWindow(holder)
        holder.bind(context as Activity, media[index], index, media.size)
    }

    override fun getItemCount(): Int {
        return media.size
    }
    inner class MediaHolder(val view: View) : RecyclerView.ViewHolder(view) {

        lateinit var mediaImageView: ImageView
        lateinit var nameTextView: TextView
        lateinit var numberTextView: TextView
        lateinit var openMediaContainer: RelativeLayout

        fun bind(activity: Activity, media: MediaModel, index: Int, total: Int) {
            mediaImageView = view.findViewById(R.id.medialImageView)
            nameTextView = view.findViewById(R.id.titleTextView)
            numberTextView = view.findViewById(R.id.pageNumberTextView)
            openMediaContainer = view.findViewById(R.id.openMediaContainer)

            Picasso.with(activity)
                .load(media.thumbnail)
                .error(R.drawable.media_thumbnail_mock)
                .into(mediaImageView)

            nameTextView.text = media.name
            numberTextView.text = String.format("%2d/%2d", index, total)

        }

    }
}