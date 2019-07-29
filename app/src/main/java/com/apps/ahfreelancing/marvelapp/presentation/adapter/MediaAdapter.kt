package com.apps.ahfreelancing.marvelapp.presentation.adapter

import android.app.Activity
import android.content.Context
import android.support.constraint.ConstraintLayout
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
class MediaAdapter (val media: ArrayList<MediaModel>, private val context: Context, val itemCallback: ItemClickCallback)
    : RecyclerView.Adapter<MediaAdapter.MediaHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MediaHolder {
        return MediaHolder(LayoutInflater.from(context).inflate(
            R.layout.item_media, viewGroup, false), itemCallback)
    }

    override fun onBindViewHolder(holder: MediaHolder, index: Int) {
        super.onViewAttachedToWindow(holder)
        holder.bind(context as Activity, media[index], index)
    }

    override fun getItemCount(): Int {
        return media.size
    }
    inner class MediaHolder(val view: View, val callback: ItemClickCallback) : RecyclerView.ViewHolder(view) {

        lateinit var mediaImageView: ImageView
        lateinit var nameTextView: TextView
        lateinit var containerConstraintLayout: ConstraintLayout

        fun bind(activity: Activity, mediaModel: MediaModel, position: Int) {

            mediaImageView = view.findViewById(R.id.media_thumbnail)
            nameTextView = view.findViewById(R.id.media_name)
            containerConstraintLayout = view.findViewById(R.id.mediaContainer)

            Picasso.with(activity)
                .load(mediaModel.thumbnail)
                .error(R.drawable.media_thumbnail_mock)
                .into(mediaImageView)

            nameTextView.text = mediaModel.name

            containerConstraintLayout.setOnClickListener { callback.onItemClicked(media, position) }
        }
    }

    interface ItemClickCallback{
        fun onItemClicked(media: ArrayList<MediaModel>, position: Int)
    }
}