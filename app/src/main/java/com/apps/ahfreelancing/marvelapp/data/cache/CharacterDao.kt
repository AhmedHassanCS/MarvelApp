package com.apps.ahfreelancing.marvelapp.data.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Created by Ahmed Hassan on 7/28/2019.
 */

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<RoomCharacterEntity>)

    @Query("SELECT * FROM character_table ORDER BY name LIMIT :pageSize OFFSET :offset")
    suspend fun getAll(offset: Int, pageSize: Int) : List<RoomCharacterEntity>

    @Query("SELECT * FROM character_table WHERE name =:name ORDER BY name")
    suspend fun getByName(name: String) : List<RoomCharacterEntity>


    @Query("DELETE FROM character_table")
    fun deleteAll()
}