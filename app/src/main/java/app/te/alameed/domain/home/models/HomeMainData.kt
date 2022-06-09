package app.te.alameed.domain.home.models


import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.annotation.Keep

@Entity
data class HomeMainData(
  @PrimaryKey(autoGenerate = true)
  val roomId: Int? = null,
  val categories_subcategories: List<CategoriesApiModel> = listOf(),
  val whatsapp: String = "",
  val splash: String = "",
  val slider: List<Slider> = listOf()
)