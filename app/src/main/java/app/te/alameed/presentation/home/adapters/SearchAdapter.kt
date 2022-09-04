package app.te.alameed.presentation.home.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.te.alameed.R
import app.te.alameed.presentation.home.ui_state.MainSearchUiState

class SearchAdapter :
    PagingDataAdapter<MainSearchUiState, SearchAdapter.ViewHolder>(differCallback) {
    lateinit var context: Context

    companion object {
        private val differCallback = object : DiffUtil.ItemCallback<MainSearchUiState>() {
            override fun areItemsTheSame(
                oldItem: MainSearchUiState,
                newItem: MainSearchUiState
            ): Boolean {
                return oldItem.toString() == newItem.toString()
            }

            override fun areContentsTheSame(
                oldItem: MainSearchUiState,
                newItem: MainSearchUiState
            ): Boolean {
                return oldItem.toString() == newItem.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        context = parent.context
        return ViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position)?.getLayoutRes() ?: R.layout.item_empty_content
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(getItem(position), position)
    }


    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bindItem(item: MainSearchUiState?, position: Int) {
            item?.bind(itemView, position, context)
        }
    }
}