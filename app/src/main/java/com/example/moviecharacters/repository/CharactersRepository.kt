package com.example.moviecharacters.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.moviecharacters.api.RetrifitInstance
import com.example.moviecharacters.model.Episodes
import com.example.moviecharacters.model.EpisodesItem
import com.example.moviecharacters.model.Profile
import com.example.moviecharacters.model.Result
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Response

class CharactersRepository {

    fun getSearchResultStream(): Flow<PagingData<Result>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = true
            ),
            pagingSourceFactory = { CharacterPagingSource() }
        ).flow
    }

    suspend fun getProfile(id:Int): Response<Profile?> {
        return RetrifitInstance.api.getProfile(id)
    }

    suspend fun getEpisode(number:String): Response<Episodes> {
        return RetrifitInstance.api.getEpisode(number)
    }
}