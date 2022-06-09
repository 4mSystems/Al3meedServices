package app.te.alameed.domain.brand_models.entity

data class BrandModels(
    val id: Int,
    val image: String,
    val name: String,
    val description: String,
    val brand: BrandModels
)