package org.example.project.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import org.example.project.data.remote.ApiService
import org.example.project.data.remote.response.toDomain
import org.example.project.domain.model.CharacterModel

class CharactersPagingSource(private val apiService: ApiService) :
    PagingSource<Int, CharacterModel>() {

    override fun getRefreshKey(state: PagingState<Int, CharacterModel>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterModel> {
         return try {
            val page = params.key ?: 1
            val response = apiService.getAllCharacters(page)
            val characters = response.results

            val prev = if (page > 1) -1 else null // 0
            val next = if (response.info.next != null) page + 1 else null

            LoadResult.Page(
                data = characters.map { characterResponse -> characterResponse.toDomain() },
                prevKey = prev,
                nextKey = next
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}