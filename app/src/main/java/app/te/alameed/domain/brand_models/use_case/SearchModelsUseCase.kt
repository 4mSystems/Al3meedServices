package app.te.alameed.domain.brand_models.use_case

import androidx.paging.Pager
import androidx.paging.PagingConfig
import app.te.alameed.data.remote.AbstractPagingSource
import app.te.alameed.domain.brand_models.repository.BrandModelsRepository
import app.te.alameed.domain.utils.Resource
import app.te.alameed.presentation.home.ui_state.ContentEmptyUiState
import app.te.alameed.presentation.home.ui_state.MainSearchUiState
import app.te.alameed.presentation.home.ui_state.SearchItemUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class SearchModelsUseCase @Inject constructor(
    private val brandModelsRepository: BrandModelsRepository
) {
    fun search(equal_modell_id: Int) =
        Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = {
                object : AbstractPagingSource<MainSearchUiState>() {

                    override suspend fun fetchData(
                        pageSize: Int,
                        PageIndex: Int
                    ): List<MainSearchUiState> {
                        val result =
                            brandModelsRepository.getBrandModelsSearch(PageIndex, equal_modell_id)
                        val items = mutableListOf<MainSearchUiState>()
                        if (result is Resource.Success) {
                            nextPage = result.value.data.next_page_url
                            val data = result.value.data
                            if (data.brandModels.isEmpty()) {
                                items.add(ContentEmptyUiState())
                            } else {
                                data.brandModels.map { searchData ->
                                    items.add(SearchItemUiState(searchData))
                                }
                            }
                        }

                        return items
                    }

                    override fun hasNextPages(): Boolean {
                        return nextPage != null
                    }
                }.getPagingSource()
            }
        ).flow.flowOn(Dispatchers.IO)
}
