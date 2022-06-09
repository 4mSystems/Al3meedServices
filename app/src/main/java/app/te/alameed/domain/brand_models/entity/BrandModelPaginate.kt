package app.te.alameed.domain.brand_models.entity

import com.google.gson.annotations.SerializedName
data class BrandModelPaginate(
    val current_page: Int,
    @SerializedName("data")
    val brandModels: List<BrandModels>,
    val first_page_url: String,
    val from: Int,
    val last_page: Int,
    val last_page_url: String,
    val next_page_url: String,
    val path: String,
    val per_page: Int,
    val prev_page_url: Any,
    val to: Int,
    val total: Int
)