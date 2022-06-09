package app.te.alameed.data.home.repository

import app.te.alameed.data.home.data_source.remote.HomeRemoteDataSource
import app.te.alameed.domain.home.models.CategoriesApiModel
import app.te.alameed.domain.home.repository.HomeRepository
import app.te.alameed.domain.utils.BaseResponse
import app.te.alameed.domain.utils.Resource
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(private val remoteDataSource: HomeRemoteDataSource) :
  HomeRepository {
  override suspend fun getHome(cat_id: Int): Resource<BaseResponse<List<CategoriesApiModel>>> =
    remoteDataSource.homeStudent(cat_id)
}