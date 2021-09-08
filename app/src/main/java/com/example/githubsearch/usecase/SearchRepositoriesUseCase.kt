package com.example.githubsearch.usecase

import com.example.githubsearch.api.Order
import com.example.githubsearch.api.Sort
import com.example.githubsearch.repository.GithubSearchRepository

class SearchRepositoriesUseCase(private val githubSearchRepository: GithubSearchRepository) {

    suspend fun getRepositories(
        query: String,
        sort: Sort = Sort.STARTS,
        order: Order = Order.DESC,
        perPage: Int,
        page: Int
    ) = githubSearchRepository.getRepositories(query, sort, order, perPage, page)

}