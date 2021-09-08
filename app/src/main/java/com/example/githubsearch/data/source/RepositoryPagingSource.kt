package com.example.githubsearch.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.githubsearch.data.model.GithubRepository
import com.example.githubsearch.usecase.SearchRepositoriesUseCase

class RepositoryPagingSource(
    private val repositoriesUseCase: SearchRepositoriesUseCase,
    private val query: String
) : PagingSource<Int, GithubRepository>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GithubRepository> {
        return try {
            val pageNumber = params.key ?: 1
            val perPage = params.loadSize
            val response = repositoriesUseCase.getRepositories(
                query = query,
                perPage = perPage,
                page = pageNumber
            )
            if (response.totalCount == 0) {
                LoadResult.Error(Throwable("No results"))
            } else {
                LoadResult.Page(
                    data = response.items,
                    prevKey = if (pageNumber == 1) null else pageNumber - 1,
                    nextKey = pageNumber + 1
                )
            }
        } catch (e: Throwable) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GithubRepository>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }


}