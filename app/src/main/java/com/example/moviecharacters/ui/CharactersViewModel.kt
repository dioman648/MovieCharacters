package com.example.moviecharacters.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.moviecharacters.model.EpisodesItem
import com.example.moviecharacters.model.Profile
import com.example.moviecharacters.model.Result
import com.example.moviecharacters.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class CharactersViewModel : ViewModel(){
    private val repository = CharactersRepository()

    private var currentLoad: Flow<PagingData<Result>>? = null

    val profile = MutableLiveData<Profile>()
    val episodes = MutableLiveData<List<EpisodesItem>>()

    fun loadCharacters(): Flow<PagingData<Result>> {
        val newResult: Flow<PagingData<Result>> = repository.getSearchResultStream()
            .cachedIn(viewModelScope)
        currentLoad = newResult
        return newResult
    }

    fun updateProfile(id:Int){
         viewModelScope.launch {
             profile.value = repository.getProfile(id).body()
         }
    }

    fun loadEpisodes(number:String){
        viewModelScope.launch {
            episodes.value = repository.getEpisode(number).body()
        }
    }
}