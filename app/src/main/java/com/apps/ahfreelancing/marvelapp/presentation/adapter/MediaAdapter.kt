package com.apps.ahfreelancing.marvelapp.presentation.adapter

import android.app.Activity
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.apps.ahfreelancing.marvelapp.R
import com.apps.ahfreelancing.marvelapp.domain.model.MediaModel
import com.squareup.picasso.Picasso

/**
 * Created by Ahmed Hassan on 7/28/2019.
 */
class MediaAdapter (val media: ArrayList<MediaModel>, private val context: Context) : RecyclerView.Adapter<MediaAdapter.MediaHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MediaHolder {
        return MediaHolder(LayoutInflater.from(context).inflate(R.layout.item_media, viewGroup, false))
    }

    override fun onBindViewHolder(holder: MediaHolder, index: Int) {
        super.onViewAttachedToWindow(holder)
        holder.bind(context as Activity, media[index])
    }

    override fun getItemCount(): Int {
        return media.size
    }
    inner class MediaHolder(val view: View) : RecyclerView.ViewHolder(view) {

        lateinit var mediaImageView: ImageView
        lateinit var nameTextView: TextView
        fun bind(activity: Activity, media: MediaModel) {
            mediaImageView = view.findViewById(R.id.media_thumbnail)
            nameTextView = view.findViewById(R.id.media_name)

            Picasso.with(activity)
                .load(media.thumbnail)
                .error(R.drawable.media_thumbnail_mock)
                .into(mediaImageView)

            nameTextView.text = media.name
        }
    }
}