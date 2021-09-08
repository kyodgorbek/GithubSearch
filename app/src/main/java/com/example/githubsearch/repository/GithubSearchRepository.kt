package com.example.githubsearch.repository

import com.example.githubsearch.api.GithubApi
import com.example.githubsearch.api.Order
import com.example.githubsearch.api.Sort

class GithubSearchRepository(private val githubApi: GithubApi) {

    suspend fun getRepositories(query: String, sort: Sort, order: Order, perPage: Int, page: Int) =
        githubApi.getGithubRepositories(query, sort.value, order.value, perPage, page)

}