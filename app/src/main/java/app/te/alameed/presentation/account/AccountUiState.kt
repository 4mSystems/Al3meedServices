package app.te.alameed.presentation.account

import android.content.Context
import android.view.View
import androidx.databinding.Bindable
import app.te.alameed.BR
import app.te.alameed.R
import app.te.alameed.presentation.base.BaseUiState
import com.structure.base_mvvm.User

class AccountUiState(val user: User) : BaseUiState() {
  @Bindable
  var updateSubscribeVisibility: Int = View.GONE

  @Bindable
  var updateProfileVisibility: Int = View.GONE

  fun updateUi() {
    updateSubscribeVisibility()
    updateProfileVisibility()
  }

  private fun updateSubscribeVisibility() {
    updateSubscribeVisibility = if (user.subscriber == 0 && user.name.isNotEmpty())
      View.VISIBLE
    else
      View.GONE
    notifyPropertyChanged(BR.updateSubscribeVisibility)
  }

  private fun updateProfileVisibility() {
    updateProfileVisibility = if (user.name.isNotEmpty())
      View.VISIBLE
    else
      View.GONE
    notifyPropertyChanged(BR.updateProfileVisibility)

  }

  fun getLogUser(context: Context): String =
    if (user.name.isNotEmpty()) context.getString(R.string.log_out) else context.getString(R.string.login)
}