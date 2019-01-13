package sera.sera.que.kotlin201x.screen.search

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import sera.sera.que.kotlin201x.api.WikipediaSearchService
import sera.sera.que.kotlin201x.model.WikipediaPage

class SearchViewModel(
    private val searchService: WikipediaSearchService
) : ViewModel(), CoroutineScope {

    val searchQuery = MutableLiveData<String>()

    val canExecute: LiveData<Boolean> = Transformations.map(searchQuery) { !it.isNullOrEmpty() }

    private val _searchResult = MutableLiveData<List<WikipediaPage>>()
    val searchResult: LiveData<List<WikipediaPage>> get() = _searchResult

    private val job = Job()
    override val coroutineContext = Dispatchers.Main + job

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    fun onSearchClick(view: View) {
        launch {
            try {
                val query = searchQuery.value ?: return@launch
                val results = searchService.search(query)
                Log.d("SearchViewModel", results.query.search.toString())
                _searchResult.postValue(results.query.search)
//                findNavController(view).navigate(R.id.action_searchFragment_to_detailFragment)

            } catch (t: Throwable) {
                Log.e("SearchViewModel", "failure", t)
            }
        }
    }
}