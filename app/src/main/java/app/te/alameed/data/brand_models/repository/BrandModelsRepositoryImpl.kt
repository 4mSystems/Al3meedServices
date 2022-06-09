package app.te.alameed.data.brand_models.repository

import app.te.alameed.data.brand_models.data_source.BrandModelsDataSource
import app.te.alameed.domain.brand_models.entity.BrandModelPaginate
import app.te.alameed.domain.brand_models.repository.BrandModelsRepository
import app.te.alameed.domain.utils.BaseResponse
import app.te.alameed.domain.utils.Resource
import javax.inject.Inject

class BrandModelsRepositoryImpl @Inject constructor(
    private val remoteDataSource: BrandModelsDataSource
) : BrandModelsRepository {
    override suspend fun getBrands(PageIndex: Int): Resource<BaseResponse<BrandModelPaginate>> =
        remoteDataSource.getBrands(PageIndex)

    override suspend fun getModels(
        PageIndex: Int,
        brand_id: Int,
        query: String?
    ): Resource<BaseResponse<BrandModelPaginate>> =
        remoteDataSource.getModels(PageIndex, brand_id,query)

    override suspend fun getBrandModelsSearch(
        PageIndex: Int,
        equal_modell_id: Int
    ): Resource<BaseResponse<BrandModelPaginate>> =
        remoteDataSource.getBrandModelsSearch(PageIndex, equal_modell_id)

}