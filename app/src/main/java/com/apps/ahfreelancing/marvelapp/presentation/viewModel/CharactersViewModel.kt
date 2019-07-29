package com.apps.ahfreelancing.marvelapp.presentation.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.apps.ahfreelancing.marvelapp.domain.model.CharacterModel
import com.apps.ahfreelancing.marvelapp.domain.model.DataWrapperModel
import com.apps.ahfreelancing.marvelapp.domain.usecase.CharactersUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Ahmed Hassan on 7/26/2019.
 */
class CharactersViewModel @Inject constructor(private val charactersUseCase: CharactersUseCase) : ViewModel() {
    private val mutableCharactersList = MutableLiveData<DataWrapperModel<List<CharacterModel>>>()

    private val charactersList: ArrayList<CharacterModel> = ArrayList(emptyList())
    private val job = SupervisorJob()

    private val coroutineContext = Dispatchers.IO + job

    private var page: Int = 0
    //getCharacters return LiveData to be observed
    fun getCharacters() : LiveData<DataWrapperModel<List<CharacterModel>>>{
        return mutableCharactersList
    }

    //getNextPage fetch the next page from server or cache
    fun getNextPage(){
        //Using of Coroutine in fetching and processing data
        CoroutineScope(coroutineContext).launch {
            page++
            val result = charactersUseCase.execute(page)
            charactersList.addAll(result.data)
            mutableCharactersList.postValue(result)
        }
    }

    //This function is called when fragment is started or restarted
    fun restoreCharacters(){
        //If it's not the first load and LiveData value is not null
        //return previously fetched data to be restored
        if(mutableCharactersList.value != null)
            mutableCharactersList.value =  DataWrapperModel(200, "", charactersList.toList())
    }

    fun getPageNumber() : Int{
        return page
    }
}