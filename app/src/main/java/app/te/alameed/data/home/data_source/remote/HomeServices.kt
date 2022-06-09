package app.te.alameed.data.home.data_source.remote

import app.te.alameed.domain.home.models.CategoriesApiModel
import app.te.alameed.domain.utils.BaseResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeServices {
  @GET("v1/app/home/categories/{cat_id}")
  suspend fun homeStudent(@Path("cat_id") cat_id: Int): BaseResponse<List<CategoriesApiModel>>

}