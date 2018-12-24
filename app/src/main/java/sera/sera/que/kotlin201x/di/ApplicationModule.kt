package sera.sera.que.kotlin201x.di

import org.koin.android.viewmodel.experimental.builder.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import sera.sera.que.kotlin201x.api.WikipediaSearchService
import sera.sera.que.kotlin201x.screen.MainViewModel
import sera.sera.que.kotlin201x.screen.search.SearchViewModel

val applicationModule = module {
    viewModel<MainViewModel>()
    viewModel<SearchViewModel>()

    single { createAPIService() }
}

private fun createAPIService(): WikipediaSearchService = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create())
    .baseUrl("https://ja.wikipedia.org")
    .build()
    .let { it.create(WikipediaSearchService::class.java) }