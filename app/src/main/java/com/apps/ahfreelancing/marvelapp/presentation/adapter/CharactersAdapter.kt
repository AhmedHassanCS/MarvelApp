package com.apps.ahfreelancing.marvelapp.presentation.adapter

import android.app.Activity
import android.content.Context
import android.opengl.Visibility
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.apps.ahfreelancing.marvelapp.R
import com.apps.ahfreelancing.marvelapp.domain.model.CharacterModel
import com.squareup.picasso.Picasso

/**
 * Created by Ahmed Hassan on 7/26/2019.
 */
class CharactersAdapter(val characters: ArrayList<CharacterModel>, private val context: Context, val itemClickCallback: ItemClickCallback) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    //Type of view code
    private val VIEW_TYPE_ITEM: Int = 0
    private val VIEW_TYPE_LOADING: Int = 1

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_ITEM)
            return CharacterHolder(LayoutInflater.from(context).inflate(R.layout.item_character, viewGroup, false))
        else
            return ViewHolderLoading(LayoutInflater.from(context).inflate(R.layout.progressbar, viewGroup, false))

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, index: Int) {
        super.onViewAttachedToWindow(holder)
        if(holder is CharacterHolder)
            holder.bind(context as Activity, characters[index], itemClickCallback)
        else if(holder is ViewHolderLoading)
            holder.progressBar.isIndeterminate = true
    }

    override fun getItemCount(): Int {
        return characters.size
    }
    override fun getItemViewType(position: Int): Int {
        //If the last element, then it must be the progress bar
        //An empty element always exits at the end of the characters array
        if(position == characters.size - 1)
            return VIEW_TYPE_LOADING
        else return VIEW_TYPE_ITEM
    }

    private inner class CharacterHolder (val view: View) : RecyclerView.ViewHolder (view){

        lateinit var thumbnailImageView: ImageView
        lateinit var titleTextView: TextView

        fun bind(activity: Activity, character: CharacterModel, callback: ItemClickCallback){
            thumbnailImageView = view.findViewById(R.id.thumbnailImageView)
            titleTextView = view.findViewById(R.id.titleTextView)

            Picasso.with(activity)
                .load(character.thumbnail)
                .into(thumbnailImageView)

            titleTextView.text = character.name

            thumbnailImageView.setOnClickListener { callback.onItemClicked(character)}
        }
    }

    private inner class ViewHolderLoading(view: View) : RecyclerView.ViewHolder(view) {
        var progressBar: ProgressBar

        init {
            progressBar = view.findViewById(R.id.charactersProgressBar) as ProgressBar
            progressBar.visibility = ProgressBar.VISIBLE
        }
    }

    interface ItemClickCallback{
        fun onItemClicked(character: CharacterModel)
    }

}