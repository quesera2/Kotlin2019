package sera.sera.que.kotlin201x.di

import org.koin.android.viewmodel.experimental.builder.viewModel
import org.koin.dsl.module.module
import sera.sera.que.kotlin201x.api.WikipediaSearchService
import sera.sera.que.kotlin201x.api.WikipediaSearchServiceImpl
import sera.sera.que.kotlin201x.screen.MainViewModel
import sera.sera.que.kotlin201x.screen.detail.DetailViewModel
import sera.sera.que.kotlin201x.screen.search.SearchViewModel

val applicationModule = module {
    viewModel<MainViewModel>()
    viewModel<SearchViewModel>()
    viewModel<DetailViewModel>()

    single<WikipediaSearchService> { WikipediaSearchServiceImpl }
}