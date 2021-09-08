package com.example.githubsearch.api

const val GITHUB_SEARCH_REPOSITORIES = "/search/repositories"

enum class Sort(val value: String) {
    STARTS("stars"), FORKS("forks")
}

enum class Order(val value: String) {
    DESC("desc"), ASC("asc")
}