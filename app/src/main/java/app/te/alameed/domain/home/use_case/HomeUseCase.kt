package app.te.alameed.domain.home.use_case

import app.te.alameed.domain.home.repository.HomeRepository
import app.te.alameed.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class HomeUseCase @Inject constructor(
  private val homeRepository: HomeRepository
) {
  fun homeService(cat_id: Int) = flow {
    emit(Resource.Loading)
    val result = homeRepository.getHome(cat_id)
    emit(result)
  }.flowOn(Dispatchers.IO)

}
