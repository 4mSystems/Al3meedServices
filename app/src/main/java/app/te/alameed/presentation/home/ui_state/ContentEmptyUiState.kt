package app.te.alameed.presentation.home.ui_state

import android.content.Context
import android.view.View
import app.te.alameed.R

class ContentEmptyUiState : MainSearchUiState {
    override fun getLayoutRes(): Int = R.layout.item_empty_content

    override fun bind(
        item: View?,
        position: Int,
        context: Context
    ) {
        item ?: return
    }

    override fun getId(): Int = 0

}
