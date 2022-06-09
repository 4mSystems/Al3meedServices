package app.te.alameed.presentation.home.viewModels

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import app.te.alameed.domain.brand_models.entity.search.SearchEqualsRequest
import app.te.alameed.domain.brand_models.use_case.SearchModelsUseCase
import app.te.alameed.presentation.base.BaseViewModel
import app.te.alameed.presentation.home.ui_state.MainSearchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val searchModelsUseCase: SearchModelsUseCase
) : BaseViewModel() {

    val searchEqualsRequest = SearchEqualsRequest()
    private val _searchResponse =
        MutableStateFlow<PagingData<MainSearchUiState>>(PagingData.empty())
    val searchResponse = _searchResponse

    fun search() {
        searchModelsUseCase.search(searchEqualsRequest.modelId)
            .onEach { result ->
                _searchResponse.value = result
            }
            .launchIn(viewModelScope)
    }

}