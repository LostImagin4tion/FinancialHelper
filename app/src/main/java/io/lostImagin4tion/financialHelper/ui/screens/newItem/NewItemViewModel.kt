package io.lostImagin4tion.financialHelper.ui.screens.newItem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.lostImagin4tion.financialHelper.FinancialHelperApp
import io.lostImagin4tion.financialHelper.dagger.AppComponent
import io.lostImagin4tion.financialHelper.domain.entities.ui.ExpensesEntity
import io.lostImagin4tion.financialHelper.domain.entities.ui.IncomeEntity
import io.lostImagin4tion.financialHelper.domain.repositories.IExpensesRepository
import io.lostImagin4tion.financialHelper.domain.repositories.IIncomeRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewItemViewModel(
    appComponent: AppComponent = FinancialHelperApp.appComponent
): ViewModel() {

    @Inject lateinit var incomeRepository: IIncomeRepository
    @Inject lateinit var expensesRepository: IExpensesRepository

    init {
        appComponent.inject(this)
    }

    fun addNewIncome(item: IncomeEntity) {
        viewModelScope.launch {
            incomeRepository.addNewIncome(item)
        }
    }

    fun addNewExpenses(item: ExpensesEntity) {
        viewModelScope.launch {
            expensesRepository.addNewExpenses(item)
        }
    }
}