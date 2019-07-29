package com.apps.ahfreelancing.marvelapp.data.cache

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Ahmed Hassan on 7/28/2019.
 */

@Entity(tableName = "character_table")
data class RoomCharacterEntity (

    @PrimaryKey val id: Int,
    val name: String,
    val description: String,
    val imageURI: String,
    val thumbnail: String,
    val detailUrl: String,
    val wikiUrl: String,
    val comicUrl: String
)