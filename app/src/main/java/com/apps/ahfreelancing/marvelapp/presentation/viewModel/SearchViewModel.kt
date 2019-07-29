package com.apps.ahfreelancing.marvelapp.presentation.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.apps.ahfreelancing.marvelapp.domain.model.CharacterModel
import com.apps.ahfreelancing.marvelapp.domain.model.DataWrapperModel
import com.apps.ahfreelancing.marvelapp.domain.usecase.SearchUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Ahmed Hassan on 7/27/2019.
 */
class SearchViewModel @Inject constructor(private val searchUseCase: SearchUseCase) : ViewModel() {
    private val mutableCharactersList = MutableLiveData<DataWrapperModel<List<CharacterModel>>>()

    private val job = SupervisorJob()

    private val coroutineContext = Dispatchers.IO + job

    fun getCharacters(name: String) : LiveData<DataWrapperModel<List<CharacterModel>>> {
        return mutableCharactersList
    }

    fun updateSearch(name: String){

        CoroutineScope(coroutineContext).launch {
            mutableCharactersList.postValue(searchUseCase.execute(name))
        }
    }
}