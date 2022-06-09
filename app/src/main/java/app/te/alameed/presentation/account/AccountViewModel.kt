package app.te.alameed.presentation.account

import androidx.lifecycle.viewModelScope
import app.te.alameed.domain.account.use_case.AccountUseCases
import app.te.alameed.domain.account.use_case.UserLocalUseCase
import app.te.alameed.domain.utils.BaseResponse
import app.te.alameed.domain.utils.Resource
import app.te.alameed.presentation.base.BaseViewModel
import com.structure.base_mvvm.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
  private val accountUseCases: AccountUseCases,
  private val userLocalUseCase: UserLocalUseCase
) : BaseViewModel() {

  private val _logOuResponse = MutableStateFlow<Resource<BaseResponse<*>>>(Resource.Default)
  val logOutResponse = _logOuResponse

  private val _userData = MutableStateFlow<User>(User.getDefaultInstance())
  val userData = _userData

  lateinit var user: User

  init {

  }

  fun logOut() {
    accountUseCases.logOutUseCase()
      .onEach { result ->
        _logOuResponse.value = result
      }
      .launchIn(viewModelScope)
  }

  fun clearStorage() {
    viewModelScope.launch {
      userLocalUseCase.logOut()
    }
  }
}