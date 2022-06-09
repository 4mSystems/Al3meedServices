package app.te.alameed.domain.utils

import android.content.Context
import android.view.View
import androidx.appcompat.widget.PopupMenu
import app.te.alameed.domain.general.entity.countries.CityModel

fun showCityPopUp(context: Context, view: View, types: List<CityModel>): PopupMenu {
  val typesPopUps = PopupMenu(context, view)
  for (i in types.indices) {
    typesPopUps.menu.add(i, i, i, types[i].name)
  }
  typesPopUps.show()
  return typesPopUps
}