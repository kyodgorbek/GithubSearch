package com.example.githubsearch.api.model

import com.example.githubsearch.data.model.GithubRepository
import com.google.gson.annotations.SerializedName

data class RepositoriesAnswer(
    @SerializedName("total_count") val totalCount: Int,
    @SerializedName("incomplete_results") val incompleteResults: Boolean,
    @SerializedName("items") val items: List<GithubRepository>
)