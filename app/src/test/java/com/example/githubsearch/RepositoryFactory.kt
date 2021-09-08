package com.example.githubsearch

import com.example.githubsearch.data.model.GithubRepository
import com.example.githubsearch.data.model.Owner

object RepositoryFactory {
    fun createRedditPost(id: Long, name: String): GithubRepository {
        return GithubRepository(
            id,
            "$name $id",
            2.0f,
            "Description $id",
            "",
            100,
            Owner("User $id", "")
        )
    }
}