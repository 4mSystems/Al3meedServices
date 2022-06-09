package app.te.alameed.core.di.module

import app.te.alameed.domain.account.repository.AccountRepository
import app.te.alameed.domain.account.use_case.*
import app.te.alameed.domain.brand_models.repository.BrandModelsRepository
import app.te.alameed.domain.brand_models.use_case.GetBrandsUseCase
import app.te.alameed.domain.brand_models.use_case.GetModelsByBrandUseCase
import app.te.alameed.domain.brand_models.use_case.SearchModelsUseCase
import app.te.alameed.domain.general.use_case.LanguageUseCase
import app.te.alameed.domain.general.use_case.GeneralUseCases
import app.te.alameed.domain.home.repository.HomeRepository
import app.te.alameed.domain.home.use_case.HomeUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {


    @Provides
    @Singleton
    fun provideHomeUseCase(
        homeRepository: HomeRepository
    ): HomeUseCase = HomeUseCase(homeRepository)


    //public use cases
    @Provides
    @Singleton
    fun provideCheckFirstTimeUseCase(
        accountRepository: AccountRepository
    ): CheckFirstTimeUseCase = CheckFirstTimeUseCase(accountRepository)

    @Provides
    @Singleton
    fun provideCheckLoggedInUserUseCase(
        accountRepository: AccountRepository
    ): CheckLoggedInUserUseCase = CheckLoggedInUserUseCase(accountRepository)

    @Provides
    @Singleton
    fun provideSetFirstTimeUseCase(
        accountRepository: AccountRepository
    ): SetFirstTimeUseCase = SetFirstTimeUseCase(accountRepository)

    @Provides
    @Singleton
    fun provideBrandsUseCase(
        brandModelsRepository: BrandModelsRepository
    ): GetBrandsUseCase = GetBrandsUseCase(brandModelsRepository)

    @Provides
    @Singleton
    fun provideGetModelsByBrandUseCase(
        brandModelsRepository: BrandModelsRepository
    ): GetModelsByBrandUseCase = GetModelsByBrandUseCase(brandModelsRepository)

    @Provides
    @Singleton
    fun provideSearchModelsUseCase(
        brandModelsRepository: BrandModelsRepository
    ): SearchModelsUseCase = SearchModelsUseCase(brandModelsRepository)


    @Provides
    @Singleton
    fun provideGeneralUseCases(
        checkFirstTimeUseCase: CheckFirstTimeUseCase,
        checkLoggedInUserUseCase: CheckLoggedInUserUseCase,
        setFirstTimeUseCase: SetFirstTimeUseCase,
        languageUseCase: LanguageUseCase
    ): GeneralUseCases =
        GeneralUseCases(
            checkFirstTimeUseCase,
            checkLoggedInUserUseCase,
            setFirstTimeUseCase,
            languageUseCase
        )

    @Provides
    @Singleton
    fun provideLogOutUseCase(
        accountRepository: AccountRepository
    ): LogOutUseCase = LogOutUseCase(accountRepository)

    @Provides
    @Singleton
    fun provideSendFirebaseTokenUseCase(
        accountRepository: AccountRepository
    ): SendFirebaseTokenUseCase = SendFirebaseTokenUseCase(accountRepository)

    @Provides
    @Singleton
    fun provideSaveUserToLocalUseCase(
        accountRepository: AccountRepository
    ): UserLocalUseCase = UserLocalUseCase(accountRepository)

    @Provides
    @Singleton
    fun provideClearPreferencesUseCase(
        accountRepository: AccountRepository
    ): LanguageUseCase = LanguageUseCase(accountRepository)

    @Provides
    @Singleton
    fun provideAccountUseCases(
        logOutUseCase: LogOutUseCase,
        sendFirebaseTokenUseCase: SendFirebaseTokenUseCase
    ): AccountUseCases = AccountUseCases(logOutUseCase, sendFirebaseTokenUseCase)

}