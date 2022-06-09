package app.te.alameed.domain.general.repository

import app.te.alameed.domain.general.entity.countries.CityModel
import app.te.alameed.domain.utils.BaseResponse
import app.te.alameed.domain.utils.Resource


interface GeneralRepository {
  suspend fun getCities(): Resource<BaseResponse<List<CityModel>>>
}