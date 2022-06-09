package app.te.alameed.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.paging.LoadState
import androidx.paging.PagingData
import app.te.alameed.BR
import app.te.alameed.R
import app.te.alameed.databinding.FragmentHomeBinding
import app.te.alameed.presentation.base.BaseFragment
import app.te.alameed.presentation.base.extensions.*
import app.te.alameed.presentation.base.utils.Constants
import app.te.alameed.presentation.base.utils.showNoApiErrorAlert
import app.te.alameed.presentation.home.adapters.SearchAdapter
import app.te.alameed.presentation.home.eventListener.HomeEventListener
import app.te.alameed.presentation.home.viewModels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(), HomeEventListener {
    private val viewModel: HomeViewModel by viewModels()
    var searchAdapter: SearchAdapter = SearchAdapter()

    override
    fun getLayoutId() = R.layout.fragment_home

    override
    fun setBindingVariables() {
        binding.eventlistener = this
        binding.viewModel = viewModel
        setFragmentResultListener(Constants.BRAND) { _: String, bundle: Bundle ->
            binding.edBrand.setText(bundle.getString(Constants.ITEM_NAME))
            viewModel.searchEqualsRequest.brandId = bundle.getInt(Constants.ITEM_ID)
        }

        setFragmentResultListener(Constants.MODELS) { _: String, bundle: Bundle ->
            binding.edModel.setText(bundle.getString(Constants.ITEM_NAME))
            viewModel.searchEqualsRequest.modelId = bundle.getInt(Constants.ITEM_ID)
        }

    }

    override
    fun setupObservers() {
        searchAdapter.addLoadStateListener { loadState ->

            if (loadState.refresh is LoadState.Loading && !isDetached)
                binding.contentLoading.shimmerFrameLayout.visibility = View.VISIBLE
            else
                binding.contentLoading.shimmerFrameLayout.visibility = View.GONE

            if (loadState.source.refresh is LoadState.NotLoading &&
                loadState.append.endOfPaginationReached && (searchAdapter.itemCount
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
            viewModel.searchResponse.collect {
                searchAdapter.submitData(it)
                binding.rcSearch.adapter = searchAdapter
            }
        }
    }


    override fun search() {
        if (viewModel.searchEqualsRequest.modelId != 0 && viewModel.searchEqualsRequest.brandId != 0) {
            lifecycleScope.launch {
                searchAdapter.submitData(PagingData.empty())
                searchAdapter.refresh()
                viewModel.search()
            }
        }

    }

    override fun openModels() {
        if (viewModel.searchEqualsRequest.brandId != 0)
            navigateSafe(HomeFragmentDirections.actionHomeFragmentToModelsFragment(viewModel.searchEqualsRequest.brandId))
        else
            showNoApiErrorAlert(requireActivity(), getString(R.string.brand_required))
    }

    override fun openBrand() {
        navigateSafe(HomeFragmentDirections.actionHomeFragmentToBrandsFragment())
    }

}