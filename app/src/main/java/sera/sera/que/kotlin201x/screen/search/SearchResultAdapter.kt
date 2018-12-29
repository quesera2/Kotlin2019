package sera.sera.que.kotlin201x.screen.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import sera.sera.que.kotlin201x.R
import sera.sera.que.kotlin201x.model.WikipediaPage

class SearchResultAdapter : RecyclerView.Adapter<SearchResultAdapter.ViewHolder>() {

    var dataSet: List<WikipediaPage> = emptyList()

    class ViewHolder(root: View) : RecyclerView.ViewHolder(root) {
        val title: TextView = root.findViewById(R.id.title)
        val snippet: TextView = root.findViewById(R.id.snippet)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return LayoutInflater.from(parent.context)
            .inflate(R.layout.listitem_search_result, parent, false)
            .let(::ViewHolder)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = dataSet[position].title
        holder.snippet.text = dataSet[position].snippet
    }

    override fun getItemCount() = dataSet.size
}