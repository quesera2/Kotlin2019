package sera.sera.que.kotlin201x.screen.search

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation.findNavController
import sera.sera.que.kotlin201x.R

class SearchViewModel : ViewModel() {

    fun onSearchClick(view: View) {
        findNavController(view).navigate(R.id.action_searchFragment_to_detailFragment)
    }
}
