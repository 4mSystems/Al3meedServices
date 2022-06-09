package app.te.alameed.domain.brand_models.repository

import app.te.alameed.domain.brand_models.entity.BrandModelPaginate
import app.te.alameed.domain.utils.BaseResponse
import app.te.alameed.domain.utils.Resource

interface BrandModelsRepository {
    suspend fun getBrands(PageIndex: Int): Resource<BaseResponse<BrandModelPaginate>>
    suspend fun getModels(PageIndex: Int, brand_id: Int, query: String?): Resource<BaseResponse<BrandModelPaginate>>
    suspend fun getBrandModelsSearch(PageIndex: Int,equal_modell_id: Int): Resource<BaseResponse<BrandModelPaginate>>
}