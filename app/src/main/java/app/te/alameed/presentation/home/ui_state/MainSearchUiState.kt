package app.te.alameed.presentation.home.ui_state

import android.content.Context
import android.view.View

interface MainSearchUiState {
  fun getLayoutRes(): Int
  fun bind(item: View?, position: Int, context: Context)
  fun getId(): Int
}