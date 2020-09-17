package com.example.moviecharacters.repository

import androidx.paging.PagingSource
import com.bumptech.glide.load.HttpException
import com.example.moviecharacters.api.RetrifitInstance
import com.example.moviecharacters.model.Result
import java.io.IOException
import java.lang.Exception
import java.lang.reflect.Executable

class CharacterPagingSource : PagingSource<Int, Result>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val api = RetrifitInstance.api
        val currentPage = params.key ?: 1
        return try {
            val characters = api.getCharacters(currentPage).body()?.results ?: emptyList()
            LoadResult.Page(
                data = characters,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = currentPage + 1
            )

        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}