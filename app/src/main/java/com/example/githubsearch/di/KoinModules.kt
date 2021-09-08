package com.example.githubsearch.di

import com.example.githubsearch.BuildConfig
import com.example.githubsearch.api.GithubApi
import com.example.githubsearch.repository.GithubSearchRepository
import com.example.githubsearch.usecase.SearchRepositoriesUseCase
import com.example.githubsearch.viewmodel.RepositoriesViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val koinNetworkModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { provideGithubApi(get()) }
}

val koinRepositoriesModules = module {
    single { GithubSearchRepository(get()) }
}

val koinUseCaseModules = module {
    single { SearchRepositoriesUseCase(get()) }
}

val koinViewModelModules = module {
    viewModel { RepositoriesViewModel(get(), get()) }
}

private fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder().apply {
        addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
    }.build()
}

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BuildConfig.BASE_GITHUB_URL)
        .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()
}

private fun provideGithubApi(retrofit: Retrofit): GithubApi = retrofit.create(GithubApi::class.java)

