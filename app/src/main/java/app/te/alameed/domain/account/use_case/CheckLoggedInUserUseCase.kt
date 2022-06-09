package app.te.alameed.domain.account.use_case

import app.te.alameed.domain.account.repository.AccountRepository
import javax.inject.Inject

class CheckLoggedInUserUseCase @Inject constructor(private val accountRepository: AccountRepository) {

}