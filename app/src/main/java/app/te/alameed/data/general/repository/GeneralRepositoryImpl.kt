package app.te.alameed.data.general.repository

import app.te.alameed.data.general.data_source.remote.GeneralRemoteDataSource
import app.te.alameed.domain.general.entity.countries.CityModel
import app.te.alameed.domain.general.repository.GeneralRepository
import app.te.alameed.domain.utils.BaseResponse
import app.te.alameed.domain.utils.Resource
import javax.inject.Inject

class GeneralRepositoryImpl @Inject constructor(
  private val remoteDataSource: GeneralRemoteDataSource
) : GeneralRepository {
  override suspend fun getCities(): Resource<BaseResponse<List<CityModel>>> =
    remoteDataSource.getCities()
}