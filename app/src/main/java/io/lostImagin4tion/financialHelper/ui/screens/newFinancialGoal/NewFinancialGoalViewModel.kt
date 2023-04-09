package io.lostImagin4tion.financialHelper.ui.screens.newFinancialGoal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.lostImagin4tion.financialHelper.FinancialHelperApp
import io.lostImagin4tion.financialHelper.dagger.AppComponent
import io.lostImagin4tion.financialHelper.domain.entities.ui.FinancialGoalEntity
import io.lostImagin4tion.financialHelper.domain.repositories.IFinancialGoalsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewFinancialGoalViewModel(
    appComponent: AppComponent = FinancialHelperApp.appComponent
): ViewModel() {

    @Inject lateinit var financialGoalRepository: IFinancialGoalsRepository

    init {
        appComponent.inject(this)
    }

    fun addNewGoal(item: FinancialGoalEntity) {
        viewModelScope.launch {
            financialGoalRepository.addNewGoal(item)
        }
    }
}