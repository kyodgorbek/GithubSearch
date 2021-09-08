package com.example.githubsearch.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubRepository(
    @SerializedName("id") override val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("score") val score: Float,
    @SerializedName("description") val description: String,
    @SerializedName("html_url") val url: String,
    @SerializedName("stargazers_count") val stars: Int,
    @SerializedName("owner") val owner: Owner
) : PagingItem, Parcelable

@Parcelize
data class Owner(
    @SerializedName("login") val login: String,
    @SerializedName("avatar_url") val avatarUrl: String
) : Parcelable