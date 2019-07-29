package com.apps.ahfreelancing.marvelapp.presentation.viewModel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.apps.ahfreelancing.marvelapp.domain.model.CharacterMediaModel
import com.apps.ahfreelancing.marvelapp.domain.model.CharacterModel
import com.apps.ahfreelancing.marvelapp.domain.model.DataWrapperModel
import com.apps.ahfreelancing.marvelapp.domain.usecase.CharacterDetailsUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Ahmed Hassan on 7/28/2019.
 */
class CharacterDetailsViewModel @Inject constructor(private val characterUseCase: CharacterDetailsUseCase) : ViewModel() {

    private val mediaContainerLiveData = MutableLiveData<DataWrapperModel<CharacterMediaModel>>()

    private val job = SupervisorJob()

    private val coroutineContext = Dispatchers.IO + job

    fun getMedia(id: Int) : LiveData<DataWrapperModel<CharacterMediaModel>> {
        updateSearch(id)
        return mediaContainerLiveData
    }

    fun updateSearch(id: Int){

        CoroutineScope(coroutineContext).launch {
            mediaContainerLiveData.postValue(characterUseCase.execute(id))
        }
    }
}