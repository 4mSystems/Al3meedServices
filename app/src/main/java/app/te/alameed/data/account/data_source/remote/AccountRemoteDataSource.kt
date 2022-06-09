package app.te.alameed.data.account.data_source.remote

import app.te.alameed.data.remote.BaseRemoteDataSource
import app.te.alameed.domain.account.entity.request.SendFirebaseTokenRequest
import javax.inject.Inject

class AccountRemoteDataSource @Inject constructor(private val apiService: AccountServices) :
  BaseRemoteDataSource() {

  suspend fun sendFirebaseToken(request: SendFirebaseTokenRequest) = safeApiCall {
    apiService.sendFirebaseToken(request)
  }

  suspend fun logOut() = safeApiCall {
    apiService.logOut()
  }
}