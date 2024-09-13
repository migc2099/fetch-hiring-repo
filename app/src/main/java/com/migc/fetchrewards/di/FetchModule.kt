package com.migc.fetchrewards.di

import com.migc.fetchrewards.MainViewModel
import com.migc.fetchrewards.data.repository.RepositoryImpl
import com.migc.fetchrewards.domain.repository.Repository
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val fetchModule = module {

    single<HttpClient> {
        HttpClient(Android)
    }

    single<Repository> {
        RepositoryImpl(client = get())
    }

    viewModel {
        MainViewModel(repository = get())
    }

}