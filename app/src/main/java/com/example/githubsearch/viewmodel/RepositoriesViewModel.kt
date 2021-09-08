package com.example.githubsearch.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.githubsearch.data.model.GithubRepository
import com.example.githubsearch.data.source.RepositoryPagingSource
import com.example.githubsearch.usecase.SearchRepositoriesUseCase
import kotlinx.coroutines.flow.Flow

class RepositoriesViewModel(
    context: Context,
    private val searchRepositoriesUseCase: SearchRepositoriesUseCase
) : AndroidViewModel(context.applicationContext as Application) {

    fun search(searchString: String, pageSize: Int = 10): Flow<PagingData<GithubRepository>> {
        return Pager(PagingConfig(pageSize)) {
            RepositoryPagingSource(searchRepositoriesUseCase, searchString)
        }.flow.cachedIn(viewModelScope)
    }

}