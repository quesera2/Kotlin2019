package sera.sera.que.kotlin201x.screen

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import sera.sera.que.kotlin201x.R

class MainViewModel(
    context: Context
) : ViewModel() {

    private val _text = MutableLiveData<String>()
    val text: LiveData<String> get() = _text

    init {
        _text.postValue(context.getString(R.string.hello))
    }
}