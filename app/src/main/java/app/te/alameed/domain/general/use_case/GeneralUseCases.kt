package app.te.alameed.domain.general.use_case

import app.te.alameed.domain.account.use_case.CheckFirstTimeUseCase
import app.te.alameed.domain.account.use_case.CheckLoggedInUserUseCase
import app.te.alameed.domain.account.use_case.SetFirstTimeUseCase

class GeneralUseCases(
  val checkFirstTimeUseCase: CheckFirstTimeUseCase,
  val checkLoggedInUserUseCase: CheckLoggedInUserUseCase,
  val setFirstTimeUseCase: SetFirstTimeUseCase,
  val languageUseCase: LanguageUseCase
)