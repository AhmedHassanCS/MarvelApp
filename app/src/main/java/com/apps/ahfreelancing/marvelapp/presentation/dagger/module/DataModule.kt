package com.apps.ahfreelancing.marvelapp.presentation.dagger.module

import android.app.Activity
import com.apps.ahfreelancing.marvelapp.data.repository.CharacterRepository
import com.apps.ahfreelancing.marvelapp.domain.repository.ICharacterRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Ahmed Hassan on 7/26/2019.
 */
@Module
class DataModule(val activity: Activity) {

    //A repository needs context to create room database
    @Provides
    fun provideRepository() : ICharacterRepository = CharacterRepository(activity)
}