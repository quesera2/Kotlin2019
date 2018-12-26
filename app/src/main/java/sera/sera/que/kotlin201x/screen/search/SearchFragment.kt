package sera.sera.que.kotlin201x.screen.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.android.viewmodel.ext.android.viewModel
import sera.sera.que.kotlin201x.databinding.SearchFragmentBinding
import sera.sera.que.kotlin201x.model.WikipediaPage

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val searchResultAdapter = SearchResultAdapter(emptyList())

        binding.recyclerView.apply {
            setHasFixedSize(true)
            adapter = searchResultAdapter
            layoutManager = LinearLayoutManager(requireActivity())
        }

        viewModel.searchResult
            .observe(this, Observer {
                searchResultAdapter.dataSet = it ?: return@Observer
                searchResultAdapter.notifyDataSetChanged()
            })
    }
    
    class SearchResultAdapter(
        var dataSet: List<WikipediaPage>
    ) : RecyclerView.Adapter<SearchResultAdapter.ViewHolder>() {

        class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val textView = TextView(parent.context)
            return ViewHolder(textView)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.textView.text = dataSet[position].title
        }

        override fun getItemCount() = dataSet.size
    }
}
