package com.example.githubsearch.api

import com.example.githubsearch.api.model.RepositoriesAnswer
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {

    @GET(GITHUB_SEARCH_REPOSITORIES)
    suspend fun getGithubRepositories(
        @Query("q") query: String,
        @Query("sort") sort: String,
        @Query("order") order: String,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): RepositoriesAnswer

}