package app.te.alameed.presentation.splash

import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.viewModels
import app.te.alameed.R
import app.te.alameed.presentation.base.BaseFragment
import app.te.alameed.databinding.FragmentSplashBinding
import app.te.alameed.presentation.base.extensions.getMyColor
import app.te.alameed.presentation.base.extensions.navigateSafe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(), SplashEventListener {

    private val viewModel: SplashViewModel by viewModels()

    override
    fun getLayoutId() = R.layout.fragment_splash

    override
    fun setUpViews() {
        viewModel.eventListener = this
        binding.viewModel = viewModel
        viewModel.eventListener = this
    }


    override fun openHome() {
        setLanguage("ar")
        navigateSafe(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
    }

    override fun openOnBoarding() {
    }

    override fun setupStatusBar() {
        val window: Window = requireActivity().window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = getMyColor(R.color.white)
    }
}