package app.te.alameed.data.brand_models.data_source

import app.te.alameed.data.remote.BaseRemoteDataSource
import javax.inject.Inject

class BrandModelsDataSource @Inject constructor(private val apiService: BrandModelsServices) :
    BaseRemoteDataSource() {

    suspend fun getBrands(PageIndex: Int) = safeApiCall {
        apiService.getBrands(PageIndex)
    }

    suspend fun getModels(PageIndex: Int,brand_id: Int, query: String?) = safeApiCall {
        apiService.getModels(PageIndex,brand_id,query)
    }
     suspend fun getBrandModelsSearch(PageIndex: Int,equal_modell_id: Int) = safeApiCall {
        apiService.getBrandModelsSearch(PageIndex,equal_modell_id)
    }

}