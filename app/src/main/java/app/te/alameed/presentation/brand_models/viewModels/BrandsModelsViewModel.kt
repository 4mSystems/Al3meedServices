package app.te.alameed.presentation.brand_models.viewModels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import app.te.alameed.domain.brand_models.entity.BrandModels
import app.te.alameed.domain.brand_models.use_case.GetBrandsUseCase
import app.te.alameed.domain.brand_models.use_case.GetModelsByBrandUseCase
import app.te.alameed.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class BrandsModelsViewModel @Inject constructor(
    private val brandsUseCase: GetBrandsUseCase,
    private val modelsUseCase: GetModelsByBrandUseCase,
    val savedStateHandle: SavedStateHandle
) : BaseViewModel() {
    private val _brandModelsResponse =
        MutableStateFlow<PagingData<BrandModels>>(PagingData.empty())
    val brandModelsResponse = _brandModelsResponse

    fun getBrands() {
        brandsUseCase.getBrands()
            .onEach { result ->
                _brandModelsResponse.value = result
            }
            .launchIn(viewModelScope)
    }

    fun getModelsByBrand(brand_id: Int, query: String? = null) {
        modelsUseCase.getModelsBrand(brand_id,query)
            .onEach { result ->
                _brandModelsResponse.value = result
            }
            .launchIn(viewModelScope)
    }

}