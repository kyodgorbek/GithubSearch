package com.example.githubsearch

import com.example.githubsearch.api.GithubApi
import com.example.githubsearch.api.model.RepositoriesAnswer
import com.example.githubsearch.data.model.GithubRepository

class FakeGithubApi : GithubApi {
    override suspend fun getGithubRepositories(
        query: String,
        sort: String,
        order: String,
        perPage: Int,
        page: Int
    ): RepositoriesAnswer {
        val items = mutableListOf<GithubRepository>().apply {
            for (i in 0..perPage) {
                add(RepositoryFactory.createRedditPost(i.toLong(), "Test"))
            }
        }
        return RepositoriesAnswer(items.count(), false, items)
    }

}