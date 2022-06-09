package app.te.alameed.data.general.data_source.remote

import app.te.alameed.domain.general.entity.countries.CityModel
import app.te.alameed.domain.utils.BaseResponse
import retrofit2.http.GET

interface GeneralServices {
  @GET("v1/app/cities")
  suspend fun getCities(): BaseResponse<List<CityModel>>

}