package app.te.alameed.presentation.brand_models.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import app.te.alameed.R
import app.te.alameed.databinding.ItemModelBinding
import app.te.alameed.domain.brand_models.entity.BrandModels
import app.te.alameed.presentation.brand_models.listeners.BrandModelsEventListeners

class ModelsAdapter(val brandModelsEventListeners: BrandModelsEventListeners) :
    PagingDataAdapter<BrandModels, ModelsAdapter.ViewHolder>(differCallback) {
    lateinit var context: Context

    companion object {
        private val differCallback = object : DiffUtil.ItemCallback<BrandModels>() {
            override fun areItemsTheSame(oldItem: BrandModels, newItem: BrandModels): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: BrandModels,
                newItem: BrandModels
            ): Boolean {
                return oldItem.toString() == newItem.toString()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_model, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position)!!
        holder.setModel(data)
    }


    override fun onViewAttachedToWindow(holder: ViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.bind()
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.unBind()
    }

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        lateinit var itemLayoutBinding: ItemModelBinding

        init {
            bind()
        }

        fun bind() {
            itemLayoutBinding = DataBindingUtil.bind(itemView)!!
        }

        fun unBind() {
            itemLayoutBinding.unbind()
        }

        fun setModel(item: BrandModels) {
            itemLayoutBinding.eventListener = brandModelsEventListeners
            itemLayoutBinding.itemModel = item
        }
    }

}