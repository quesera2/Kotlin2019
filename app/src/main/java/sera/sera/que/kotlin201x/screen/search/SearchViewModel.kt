package sera.sera.que.kotlin201x.screen.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.navigation.NavDirections
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import sera.sera.que.kotlin201x.api.WikipediaSearchService
import sera.sera.que.kotlin201x.model.WikipediaPage
import sera.sera.que.kotlin201x.screen.Event
import sera.sera.que.kotlin201x.screen.search.SearchFragmentDirections.actionSearchFragmentToDetailFragment

class SearchViewModel(
    private val searchService: WikipediaSearchService
) : ViewModel(), CoroutineScope {

    val searchQuery = MutableLiveData<String>()

    val canExecute: LiveData<Boolean> = searchQuery.map { !it.isNullOrEmpty() }

    private val _searchResult = MutableLiveData<List<WikipediaPage>>()
    val searchResult: LiveData<List<WikipediaPage>> get() = _searchResult

    private val _navigateTo = MutableLiveData<Event<NavDirections>>()
    val navigateTo: LiveData<Event<NavDirections>> get() = _navigateTo

    private val job = Job()
    override val coroutineContext = Dispatchers.Main + job

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    fun onSearchClick() = launch {
        runCatching {
            val query = searchQuery.value.orEmpty()
            searchService.search(query)
        }.onSuccess {
            _searchResult.postValue(it.query.search)
        }.onFailure {
            // TODO: Exception Handling
            it.printStackTrace()
        }
    }

    fun onItemClick(page: WikipediaPage) = actionSearchFragmentToDetailFragment(page)
        .let(::Event)
        .let(_navigateTo::postValue)
}