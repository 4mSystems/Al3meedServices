package app.te.alameed.domain.general.entity.countries

import com.google.gson.annotations.SerializedName

data class CityModel(

  @SerializedName("image")
  var image: String = "",

  @SerializedName("name")
  val name: String = "",

  @SerializedName("id")
  val id: Int = 0
)

