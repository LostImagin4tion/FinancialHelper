package io.lostImagin4tion.financialHelper.ui.screens.expenses

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.lostImagin4tion.financialHelper.FinancialHelperApp
import io.lostImagin4tion.financialHelper.dagger.AppComponent
import io.lostImagin4tion.financialHelper.domain.entities.ui.ExpensesEntity
import io.lostImagin4tion.financialHelper.domain.repositories.IExpensesRepository
import io.lostImagin4tion.financialHelper.ui.utils.MutableResultFlow
import io.lostImagin4tion.financialHelper.ui.utils.loadOrError
import kotlinx.coroutines.launch
import javax.inject.Inject

class ExpensesViewModel(
    appComponent: AppComponent = FinancialHelperApp.appComponent
): ViewModel() {

    @Inject
    lateinit var expensesRepository: IExpensesRepository

    init {
        appComponent.inject(this)
    }

    val expensesResult = MutableResultFlow<List<ExpensesEntity>>()

    fun getAllIncome() {
        viewModelScope.launch {
            expensesResult.loadOrError {
                expensesRepository.getAll()
            }
        }
    }
}