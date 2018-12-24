package sera.sera.que.kotlin201x.screen.search

import android.os.Handler
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation.findNavController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import sera.sera.que.kotlin201x.R
import sera.sera.que.kotlin201x.api.WikipediaSearchService
import sera.sera.que.kotlin201x.model.WikipediaPageResponse

class SearchViewModel(
    private val searchService: WikipediaSearchService
) : ViewModel() {

    val searchQuery = MutableLiveData<String>()

    val canExecute: LiveData<Boolean> = Transformations.map(searchQuery) { !it.isNullOrEmpty() }

    fun onSearchClick(view: View) {
        val query = searchQuery.value ?: return
        val results = searchService.search(query)
        val navController = findNavController(view)
        val handler = Handler()

        results.enqueue(object : Callback<WikipediaPageResponse> {
            override fun onResponse(call: Call<WikipediaPageResponse>, response: Response<WikipediaPageResponse>) {
                val result = response.body() ?: return
                Log.d("TAG", result.toString())
                handler.post { navController.navigate(R.id.action_searchFragment_to_detailFragment) }
            }

            override fun onFailure(call: Call<WikipediaPageResponse>, t: Throwable) {
                Log.e("TAG", "failure", t)
            }
        })
    }
}
