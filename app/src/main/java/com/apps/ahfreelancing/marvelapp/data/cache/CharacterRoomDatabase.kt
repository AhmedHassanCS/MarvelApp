package com.apps.ahfreelancing.marvelapp.data.cache

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Created by Ahmed Hassan on 7/28/2019.
 */
@Database(entities = [RoomCharacterEntity::class], version = 1)
abstract class CharacterRoomDatabase : RoomDatabase(){
    abstract fun characterDao(): CharacterDao

    companion object {
        @Volatile
        private var INSTANCE: CharacterRoomDatabase? = null

        fun getDatabase(context: Context): CharacterRoomDatabase {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                // Create database here
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CharacterRoomDatabase::class.java,
                    "marvel_characters_db"
                ).build()
                    INSTANCE = instance
                return instance
            }
        }
    }
}