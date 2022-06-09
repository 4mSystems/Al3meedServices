package app.te.alameed.presentation.base.events


interface BaseContentEventListener : BaseEventListener {
    fun changeSubCategoryItem(itemId: Int, currentPosition: Int)
    fun openContent(itemId: Int, content: String)
}