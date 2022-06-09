package app.te.alameed.domain.account.use_case

import app.te.alameed.domain.account.repository.AccountRepository
import javax.inject.Inject

class UserLocalUseCase @Inject constructor(private val accountRepository: AccountRepository) {


  suspend fun saveUserToken(value: String) =
    accountRepository.saveUserToken(value)

  suspend fun logOut() = accountRepository.clearPreferences()

}