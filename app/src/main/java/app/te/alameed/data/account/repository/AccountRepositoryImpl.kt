package app.te.alameed.data.account.repository

import app.te.alameed.data.account.data_source.remote.AccountRemoteDataSource
import app.te.alameed.data.local.preferences.AppPreferences
import app.te.alameed.domain.account.entity.request.SendFirebaseTokenRequest
import app.te.alameed.domain.account.repository.AccountRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
  private val remoteDataSource: AccountRemoteDataSource,
  private val appPreferences: AppPreferences
) : AccountRepository {

  override
  suspend fun sendFirebaseToken(request: SendFirebaseTokenRequest) =
    remoteDataSource.sendFirebaseToken(request)

  override
  suspend fun logOut() = remoteDataSource.logOut()
  override suspend fun isLoggedIn(isLoggedIn: Boolean) {
    appPreferences.isLoggedIn(isLoggedIn)
  }

  override suspend fun getIsLoggedIn(): Flow<Boolean> {
    return appPreferences.getIsLoggedIn()
  }


  override suspend fun saveFirebaseTokenToLocal(firebaseToken: String) {
    appPreferences.saveFireBaseToken(firebaseToken)
  }

  override suspend fun getFirebaseTokenToLocal(): Flow<String> {
    return appPreferences.getFireBaseToken()
  }

  override suspend fun setFirstTime(isFirstTime: Boolean) {
    appPreferences.isFirstTime(isFirstTime)
  }

  override suspend fun isFirstTime(): Flow<Boolean> {
    return appPreferences.getIsFirstTime()
  }

  override suspend fun saveUserToken(userToken: String) {
    appPreferences.userToken(userToken)
  }

  override suspend fun getUserToken(): Flow<String> {
    return appPreferences.getUserToken()
  }

  override suspend fun setLang(lang: String) {
    appPreferences.setLang(lang)
  }

  override suspend fun getLang(): Flow<String> {
    return appPreferences.getLang()
  }


  override suspend fun saveSplash(value: String) {
    appPreferences.saveSplash(value)
  }

  override suspend fun getSplash(): Flow<String> {
    return appPreferences.getSplash()
  }

  override
  suspend fun clearPreferences() = appPreferences.clearPreferences()
}