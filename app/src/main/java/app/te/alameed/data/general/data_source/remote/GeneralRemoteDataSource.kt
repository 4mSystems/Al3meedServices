package app.te.alameed.data.general.data_source.remote

import app.te.alameed.data.remote.BaseRemoteDataSource
import javax.inject.Inject

class GeneralRemoteDataSource @Inject constructor(private val apiService: GeneralServices) :
  BaseRemoteDataSource() {
  suspend fun getCities() = safeApiCall {
    apiService.getCities()
  }
}