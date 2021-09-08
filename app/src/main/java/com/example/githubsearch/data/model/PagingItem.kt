package com.example.githubsearch.data.model

interface PagingItem {
    val id: Long
    override fun equals(other: Any?): Boolean
    override fun hashCode(): Int
}