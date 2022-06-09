package app.te.alameed.data.brand_models.data_source

import app.te.alameed.domain.brand_models.entity.BrandModelPaginate
import app.te.alameed.domain.utils.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface BrandModelsServices {

    @GET("newapp/v1/equal-brands")
    suspend fun getBrands(@Query("page") PageIndex: Int): BaseResponse<BrandModelPaginate>

    @GET("newapp/v1/equal-modells")
    suspend fun getModels(
        @Query("page") PageIndex: Int,
        @Query("equal_brand_id") brand_id: Int,
        @Query("search_query") query: String?
    ): BaseResponse<BrandModelPaginate>

    @GET("newapp/v1/search-equals-sizes")
    suspend fun getBrandModelsSearch(
        @Query("page") PageIndex: Int,
        @Query("equal_modell_id") brand_id: Int
    ): BaseResponse<BrandModelPaginate>

}