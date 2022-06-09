package app.te.alameed.domain.brand_models.entity.search

import com.google.gson.annotations.SerializedName

class SearchEqualsRequest {
    var brandId: Int = 0

    @SerializedName("equal_modell_id")
    var modelId: Int = 0
}