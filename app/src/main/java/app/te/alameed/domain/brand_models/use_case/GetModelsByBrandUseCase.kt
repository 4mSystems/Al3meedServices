package app.te.alameed.domain.brand_models.use_case

import androidx.paging.Pager
import androidx.paging.PagingConfig
import app.te.alameed.data.remote.AbstractPagingSource
import app.te.alameed.domain.brand_models.entity.BrandModels
import app.te.alameed.domain.brand_models.repository.BrandModelsRepository
import app.te.alameed.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class GetModelsByBrandUseCase @Inject constructor(
    private val brandModelsRepository: BrandModelsRepository
) {
    fun getModelsBrand(brand_id: Int, query: String?) =
        Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = false),
            pagingSourceFactory = {
                object : AbstractPagingSource<BrandModels>() {

                    override suspend fun fetchData(
                        pageSize: Int,
                        PageIndex: Int
                    ): List<BrandModels> {
                        val result = brandModelsRepository.getModels(PageIndex, brand_id,query)
                        var items = listOf<BrandModels>()
                        if (result is Resource.Success) {
                            nextPage = result.value.data.next_page_url
                            items = result.value.data.brandModels
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
