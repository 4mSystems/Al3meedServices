package app.te.alameed.core.di.module

import app.te.alameed.data.account.data_source.remote.AccountServices
import app.te.alameed.data.brand_models.data_source.BrandModelsServices
import app.te.alameed.data.general.data_source.remote.GeneralServices
import app.te.alameed.data.home.data_source.remote.HomeServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkServicesModule {


    @Provides
    @Singleton
    fun provideAccountServices(retrofit: Retrofit): AccountServices =
        retrofit.create(AccountServices::class.java)

    @Provides
    @Singleton
    fun provideGeneralServices(retrofit: Retrofit): GeneralServices =
        retrofit.create(GeneralServices::class.java)

    @Provides
    @Singleton
    fun provideHomeServices(retrofit: Retrofit): HomeServices =
        retrofit.create(HomeServices::class.java)

    @Provides
    @Singleton
    fun provideBrandModelsServices(retrofit: Retrofit): BrandModelsServices =
        retrofit.create(BrandModelsServices::class.java)

}