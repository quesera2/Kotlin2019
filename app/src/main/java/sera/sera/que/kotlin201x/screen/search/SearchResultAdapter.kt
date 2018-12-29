package sera.sera.que.kotlin201x.screen.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import sera.sera.que.kotlin201x.R
import sera.sera.que.kotlin201x.model.WikipediaPage

class SearchResultAdapter : RecyclerView.Adapter<SearchResultAdapter.ViewHolder>() {

    private var dataSet: List<WikipediaPage> = emptyList()

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

    fun updateData(newData: List<WikipediaPage>) {
        val diffResult = DiffUtil.calculateDiff(Callback(dataSet, newData), false)
        dataSet = newData
        diffResult.dispatchUpdatesTo(this)
    }

    private class Callback(
        private val oldData: List<WikipediaPage>,
        private val newData: List<WikipediaPage>
    ) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldData.size
        override fun getNewListSize(): Int = newData.size

        override fun areItemsTheSame(
            oldItemPosition: Int,
            newItemPosition: Int
        ) = oldData[oldItemPosition].pageid == newData[newItemPosition].pageid

        override fun areContentsTheSame(
            oldItemPosition: Int,
            newItemPosition: Int
        ) = oldData[oldItemPosition] == newData[newItemPosition]
    }
}