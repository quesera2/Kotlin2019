package sera.sera.que.kotlin201x.screen.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.koin.android.viewmodel.ext.android.viewModel
import sera.sera.que.kotlin201x.databinding.SearchFragmentBinding

class SearchFragment : Fragment() {

    private val viewModel: SearchViewModel by viewModel()

    private lateinit var binding: SearchFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SearchFragmentBinding.inflate(inflater, container, false)
        binding.setLifecycleOwner(this)
        binding.viewModel = viewModel
        return binding.root
    }
}
