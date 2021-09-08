package com.example.githubsearch

import androidx.paging.PagingSource
import com.example.githubsearch.data.model.GithubRepository
import com.example.githubsearch.data.source.RepositoryPagingSource
import com.example.githubsearch.repository.GithubSearchRepository
import com.example.githubsearch.usecase.SearchRepositoriesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test

class GithubRepositoryTest {
    private val repositories = mutableListOf<GithubRepository>().apply {
        for (i in 0..5) {
            add(RepositoryFactory.createRedditPost(i.toLong(), "Test"))
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun githubRepositoryPagingSourceTest() = runBlockingTest {
        val repositoryPagingSource = RepositoryPagingSource(
            SearchRepositoriesUseCase(
                GithubSearchRepository(FakeGithubApi())
            ), "Test"
        )
        val result = repositoryPagingSource.load(PagingSource.LoadParams.Refresh(null, 5, false))

        Assert.assertEquals(
            repositories,
            if (result is PagingSource.LoadResult.Page) {
                result.data
            } else listOf()
        )
    }

}