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
import com.apps.ahfreelancing.marvelapp.domain.model.CharacterModel
import com.squareup.picasso.Picasso

/**
 * Created by Ahmed Hassan on 7/27/2019.
 */
class ResultsAdapter (val characters: ArrayList<CharacterModel>,
                      private val context: Context,
                      private val itemClickCallback: ItemClickCallback) : RecyclerView.Adapter<ResultsAdapter.CharacterHolder>() {


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CharacterHolder {
        return CharacterHolder(LayoutInflater.from(context).inflate(R.layout.item_search_character,
            viewGroup, false), itemClickCallback)

    }

    override fun onBindViewHolder(holder: CharacterHolder, index: Int) {
        super.onViewAttachedToWindow(holder)
        holder.bind(context as Activity, characters[index])
    }

    override fun getItemCount(): Int {
        return characters.size
    }
    inner class CharacterHolder(val view: View, val itemClickCallback: ItemClickCallback) : RecyclerView.ViewHolder(view) {

        lateinit var characterImageView: ImageView
        lateinit var nameTextView: TextView
        lateinit var itemContainer: ConstraintLayout
        fun bind(activity: Activity, character: CharacterModel) {
            characterImageView = view.findViewById(R.id.characterImage)
            nameTextView = view.findViewById(R.id.characterName)
            itemContainer = view.findViewById(R.id.itemSearchContainer)

            Picasso.with(activity)
                .load(character.thumbnail)
                .into(characterImageView)

            nameTextView.text = character.name

            itemContainer.setOnClickListener { itemClickCallback.onItemClicked(character) }
        }
    }

    interface ItemClickCallback{
        fun onItemClicked(character: CharacterModel)
    }
}