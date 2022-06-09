package app.te.alameed.presentation.splash

import android.view.View
import androidx.databinding.Bindable
import androidx.lifecycle.viewModelScope
import app.te.alameed.domain.general.use_case.GeneralUseCases
import app.te.alameed.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val generalUseCases: GeneralUseCases) :
    BaseViewModel() {
    lateinit var eventListener: SplashEventListener

    @Bindable
    var adImage: String = ""

    @Bindable
    var adImageVisibility: Int = View.GONE

    @Bindable
    var logoVisibility: Int = View.GONE

    init {
        viewModelScope.launch {

            delay(3000)

            eventListener.openHome()

        }
    }
}