package app.te.alameed.domain.general.use_case

import app.te.alameed.domain.general.entity.countries.CityModel
import app.te.alameed.domain.general.repository.GeneralRepository
import app.te.alameed.domain.utils.BaseResponse
import app.te.alameed.domain.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class CitiesUseCase @Inject constructor(
  private val generalRepository: GeneralRepository
) {
  operator fun invoke(): Flow<Resource<BaseResponse<List<CityModel>>>> = flow {
    emit(Resource.Loading)
    val result = generalRepository.getCities()
    emit(result)
  }.flowOn(Dispatchers.IO)
}
