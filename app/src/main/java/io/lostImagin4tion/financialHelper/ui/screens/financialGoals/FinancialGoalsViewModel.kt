package io.lostImagin4tion.financialHelper.ui.screens.financialGoals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.lostImagin4tion.financialHelper.FinancialHelperApp
import io.lostImagin4tion.financialHelper.dagger.AppComponent
import io.lostImagin4tion.financialHelper.domain.entities.ui.FinancialGoalEntity
import io.lostImagin4tion.financialHelper.domain.repositories.IFinancialGoalsRepository
import io.lostImagin4tion.financialHelper.ui.utils.MutableResultFlow
import io.lostImagin4tion.financialHelper.ui.utils.loadOrError
import kotlinx.coroutines.launch
import javax.inject.Inject

class FinancialGoalsViewModel(
    appComponent: AppComponent = FinancialHelperApp.appComponent
): ViewModel() {

    @Inject lateinit var financialGoalsRepository: IFinancialGoalsRepository

    init {
        appComponent.inject(this)
    }

    val financialGoalsResult = MutableResultFlow<List<FinancialGoalEntity>>()

    fun getAllGoals() {
        viewModelScope.launch {
            financialGoalsResult.loadOrError {
                financialGoalsRepository.getAll()
            }
        }
    }
}