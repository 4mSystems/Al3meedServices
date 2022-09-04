package app.te.alameed.presentation.home.ui_state

import android.content.Context
import android.view.View
import androidx.databinding.DataBindingUtil
import app.te.alameed.R
import app.te.alameed.databinding.ItemHomeBinding
import app.te.alameed.domain.brand_models.entity.BrandModels
import app.te.alameed.presentation.base.BaseUiState

class SearchItemUiState(val itemSearch: BrandModels) : BaseUiState(), MainSearchUiState {
    override fun getLayoutRes(): Int = R.layout.item_home

    override fun bind(
        item: View?,
        position: Int,
        context: Context
    ) {
        item ?: return
        val binding = DataBindingUtil.bind<ItemHomeBinding>(item)
        binding?.itemModel = this
    }

    override fun getId(): Int = itemSearch.id
    fun getCm(context: Context): String =
        context.getString(R.string.cm).plus(" ${itemSearch.size.cm}")

    fun getInch(context: Context): String =
        context.getString(R.string.inch).plus(" ${itemSearch.size.inch}")

}