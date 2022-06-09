package app.te.alameed.domain.home.repository
import app.te.alameed.domain.home.models.CategoriesApiModel
import app.te.alameed.domain.utils.BaseResponse
import app.te.alameed.domain.utils.Resource

interface HomeRepository {
  suspend fun getHome(cat_id: Int): Resource<BaseResponse<List<CategoriesApiModel>>>
}