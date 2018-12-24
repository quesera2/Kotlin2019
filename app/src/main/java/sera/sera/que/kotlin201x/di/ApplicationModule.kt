package sera.sera.que.kotlin201x.di

import org.koin.android.viewmodel.experimental.builder.viewModel
import org.koin.dsl.module.module
import sera.sera.que.kotlin201x.screen.MainViewModel
import sera.sera.que.kotlin201x.screen.search.SearchViewModel

val applicationModule = module {
    viewModel<MainViewModel>()
    viewModel<SearchViewModel>()
}