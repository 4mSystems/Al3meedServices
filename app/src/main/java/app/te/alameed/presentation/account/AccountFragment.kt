package app.te.alameed.presentation.account

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.te.alameed.R
import app.te.alameed.databinding.FragmentAccountBinding
import app.te.alameed.domain.utils.Resource
import app.te.alameed.presentation.base.BaseFragment
import app.te.alameed.presentation.base.extensions.*
import codes.grand.pretty_pop_up.PrettyPopUpHelper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class AccountFragment : BaseFragment<FragmentAccountBinding>(), AccountEventListener {
  private val accountViewModel: AccountViewModel by viewModels()
  lateinit var uiState: AccountUiState

  override
  fun getLayoutId() = R.layout.fragment_account
  override fun setBindingVariables() {
    binding.eventListener = this
  }

  override
  fun setupObservers() {

    lifecycleScope.launchWhenResumed {
      accountViewModel.logOutResponse.collect {
        when (it) {
          Resource.Loading -> {
            hideKeyboard()
            showLoading()
          }
          is Resource.Success -> {
            hideLoading()
            accountViewModel.clearStorage()
            openLogInScreen()
          }
          is Resource.Failure -> {
            hideLoading()
            handleApiError(it)
          }
        }
      }
    }
  }

  private fun showLogOutPopUp() {
    PrettyPopUpHelper.Builder(childFragmentManager)
      .setStyle(PrettyPopUpHelper.Style.STYLE_1_HORIZONTAL_BUTTONS)
      .setTitle(R.string.log_out)
      .setTitleColor(getMyColor(R.color.black))
      .setContent(R.string.log_out_hint)
      .setContentColor(getMyColor(R.color.black))
      .setPositiveButtonBackground(R.drawable.corner_view_primary_dark)
      .setPositiveButtonTextColor(getMyColor(R.color.white))
      .setImage(R.drawable.ic_logout)
      .setPositiveButton(R.string.log_out) {
        it.dismiss()
        accountViewModel.logOut()
      }
      .setNegativeButtonBackground(R.drawable.corner_view_gray_border)
      .setNegativeButtonTextColor(getMyColor(R.color.black))
      .setNegativeButton(getMyString(R.string.cancel), null)
      .create()
  }

  private fun openLogInScreen() {
  }

  override fun openProfile() {
  }

  override fun openFavorite() {
  }

  override fun openSubscribe() {
  }

  override fun openChangeLanguage() {
  }

  override fun logout() {

  }
}