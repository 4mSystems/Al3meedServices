package app.te.alameed.data.home.data_source.remote

import app.te.alameed.data.remote.BaseRemoteDataSource
import javax.inject.Inject

class HomeRemoteDataSource @Inject constructor(private val apiService: HomeServices) :
  BaseRemoteDataSource() {

  suspend fun homeStudent(cat_id: Int) = safeApiCall {
    apiService.homeStudent(cat_id)
  }
}