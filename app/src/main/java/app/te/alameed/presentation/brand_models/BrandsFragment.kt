package app.te.alameed.presentation.brand_models

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import app.te.alameed.R
import app.te.alameed.databinding.FragmentBrandsBinding
import app.te.alameed.presentation.base.BaseFragment
import app.te.alameed.presentation.base.extensions.backToPreviousScreen
import app.te.alameed.presentation.base.extensions.setUpAdapter
import app.te.alameed.presentation.base.utils.Constants
import app.te.alameed.presentation.brand_models.adapters.BrandsAdapter
import app.te.alameed.presentation.brand_models.listeners.BrandModelsEventListeners
import app.te.alameed.presentation.brand_models.viewModels.BrandsModelsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class BrandsFragment : BaseFragment<FragmentBrandsBinding>(), BrandModelsEventListeners {
    private val viewModel: BrandsModelsViewModel by viewModels()
    private lateinit var brandAdapter: BrandsAdapter

    override
    fun getLayoutId() = R.layout.fragment_brands

    override
    fun setBindingVariables() {
        brandAdapter = BrandsAdapter(this)
        viewModel.getBrands()
    }

    override
    fun setupObservers() {
        brandAdapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading && !isDetached)
                binding.contentLoading.shimmerFrameLayout.visibility = View.VISIBLE
            else
                binding.contentLoading.shimmerFrameLayout.visibility = View.GONE

            if (loadState.source.refresh is LoadState.NotLoading &&
                loadState.append.endOfPaginationReached && (brandAdapter.itemCount
                    ?: 0) < 1
            ) {
                // getting the error
                val error = when {
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                    else -> null
                }

                error?.let {
                    if (it.error.message != null)
                        if (it.error.message?.isNotEmpty() == true)
                            Toast.makeText(requireContext(), it.error.message, Toast.LENGTH_LONG)
                                .show()
                }
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.brandModelsResponse.collect {
                brandAdapter.submitData(it)
                binding.rcBrands.setUpAdapter(brandAdapter, "1", "1")
            }
        }

    }

    override fun selectItem(itemId: Int, itemName: String) {
        val bundle = Bundle()
        bundle.putInt(Constants.ITEM_ID, itemId)
        bundle.putString(Constants.ITEM_NAME, itemName)
        setFragmentResult(Constants.BRAND, bundle)
        backToPreviousScreen()
    }


}