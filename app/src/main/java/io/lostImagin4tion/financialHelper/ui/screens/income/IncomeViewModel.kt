package io.lostImagin4tion.financialHelper.ui.screens.income

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.lostImagin4tion.financialHelper.FinancialHelperApp
import io.lostImagin4tion.financialHelper.dagger.AppComponent
import io.lostImagin4tion.financialHelper.domain.entities.ui.IncomeEntity
import io.lostImagin4tion.financialHelper.domain.repositories.IIncomeRepository
import io.lostImagin4tion.financialHelper.ui.utils.MutableResultFlow
import io.lostImagin4tion.financialHelper.ui.utils.loadOrError
import kotlinx.coroutines.launch
import javax.inject.Inject

class IncomeViewModel(
    appComponent: AppComponent = FinancialHelperApp.appComponent
): ViewModel() {

    @Inject lateinit var incomeRepository: IIncomeRepository

    init {
        appComponent.inject(this)
    }

    val incomeResult = MutableResultFlow<List<IncomeEntity>>()

    fun getAllIncome() {
        viewModelScope.launch {
            incomeResult.loadOrError {
                incomeRepository.getAll()
            }
        }
    }
}