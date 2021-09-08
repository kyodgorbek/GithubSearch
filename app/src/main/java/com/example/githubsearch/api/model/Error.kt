package com.example.githubsearch.api.model

import com.google.gson.annotations.SerializedName

data class Error(@SerializedName("message") val message: String)