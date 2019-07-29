package com.apps.ahfreelancing.marvelapp.presentation.dagger.component

import com.apps.ahfreelancing.marvelapp.domain.repository.ICharacterRepository
import com.apps.ahfreelancing.marvelapp.presentation.dagger.module.DataModule
import com.apps.ahfreelancing.marvelapp.presentation.view.fragment.BaseFragment
import com.apps.ahfreelancing.marvelapp.presentation.view.fragment.CharacterDetailsFragment
import com.apps.ahfreelancing.marvelapp.presentation.view.fragment.CharactersFragment
import com.apps.ahfreelancing.marvelapp.presentation.view.fragment.SearchFragment
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Ahmed Hassan on 7/26/2019.
 */
@Singleton
@Component(modules = [DataModule::class])
interface FragmentComponent {
    fun inject(searchFragment: SearchFragment)
    fun inject(charactersFragment: CharactersFragment)
    fun inject(characterDetailsFragment: CharacterDetailsFragment)

    //dependencies
    fun characterRepository() : ICharacterRepository
}