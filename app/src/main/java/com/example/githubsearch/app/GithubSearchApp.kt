package com.example.githubsearch.app

import android.app.Application
import com.example.githubsearch.di.koinNetworkModule
import com.example.githubsearch.di.koinRepositoriesModules
import com.example.githubsearch.di.koinUseCaseModules
import com.example.githubsearch.di.koinViewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GithubSearchApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@GithubSearchApp)
            modules(
                koinNetworkModule,
                koinRepositoriesModules,
                koinUseCaseModules,
                koinViewModelModules
            )
        }
    }

}