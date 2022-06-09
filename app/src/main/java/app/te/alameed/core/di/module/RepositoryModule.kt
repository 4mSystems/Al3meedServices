package app.te.alameed.core.di.module

import app.te.alameed.data.account.data_source.remote.AccountRemoteDataSource
import app.te.alameed.data.account.repository.AccountRepositoryImpl
import app.te.alameed.data.brand_models.data_source.BrandModelsDataSource
import app.te.alameed.data.brand_models.repository.BrandModelsRepositoryImpl
import app.te.alameed.data.general.data_source.remote.GeneralRemoteDataSource
import app.te.alameed.data.general.repository.GeneralRepositoryImpl
import app.te.alameed.data.home.data_source.remote.HomeRemoteDataSource
import app.te.alameed.data.home.repository.HomeRepositoryImpl
import app.te.alameed.data.local.preferences.AppPreferences
import app.te.alameed.domain.account.repository.AccountRepository
import app.te.alameed.domain.brand_models.repository.BrandModelsRepository
import app.te.alameed.domain.general.repository.GeneralRepository
import app.te.alameed.domain.home.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideGeneralRepository(
        remoteDataSource: GeneralRemoteDataSource
    ): GeneralRepository =
        GeneralRepositoryImpl(remoteDataSource)


    @Provides
    @Singleton
    fun provideAccountRepository(
        remoteDataSource: AccountRemoteDataSource,
        appPreferences: AppPreferences
    ): AccountRepository = AccountRepositoryImpl(remoteDataSource, appPreferences)

    @Provides
    @Singleton
    fun provideHomeRepository(
        remoteDataSource: HomeRemoteDataSource
    ): HomeRepository = HomeRepositoryImpl(remoteDataSource)

    @Provides
    @Singleton
    fun provideBrandModelsRepository(
        remoteDataSource: BrandModelsDataSource
    ): BrandModelsRepository = BrandModelsRepositoryImpl(remoteDataSource)

}