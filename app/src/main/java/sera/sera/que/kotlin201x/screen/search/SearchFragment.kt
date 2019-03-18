package sera.sera.que.kotlin201x.screen.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
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
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchResultAdapter = SearchResultAdapter().apply {
            setOnItemClickListener { model -> viewModel.onItemClick(model) }
        }

        binding.recyclerView.apply {
            setHasFixedSize(true)
            adapter = searchResultAdapter
            layoutManager = LinearLayoutManager(requireActivity())
        }

        viewModel.searchResult
            .observe(viewLifecycleOwner) {
                searchResultAdapter.updateData(it)
            }

        viewModel.navigateTo
            .observe(viewLifecycleOwner) {
                // Event<T> にした方がいいと思われるがとりあえずは雑に
                findNavController().navigate(it)
            }
    }
}
