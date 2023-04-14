package io.lostImagin4tion.financialHelper.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.lostImagin4tion.financialHelper.FinancialHelperApp
import io.lostImagin4tion.financialHelper.dagger.AppComponent
import io.lostImagin4tion.financialHelper.domain.entities.ui.IncomeAndExpensesLineChartData
import io.lostImagin4tion.financialHelper.domain.entities.ui.IncomeAndExpensesPieChartData
import io.lostImagin4tion.financialHelper.domain.repositories.IChartsRepository
import io.lostImagin4tion.financialHelper.ui.utils.MutableResultFlow
import io.lostImagin4tion.financialHelper.ui.utils.loadOrError
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel(
    appComponent: AppComponent = FinancialHelperApp.appComponent
) : ViewModel() {

    @Inject lateinit var chartsRepository: IChartsRepository

    init {
        appComponent.inject(this)
    }

    val incomeAndExpensesChartResult = MutableResultFlow<IncomeAndExpensesLineChartData>()
    val pieChartDataResult = MutableResultFlow<IncomeAndExpensesPieChartData>()

    fun getAllIncomeAndExpenses() {
        viewModelScope.launch {
            incomeAndExpensesChartResult.loadOrError {
                chartsRepository.getIncomeAndExpensesForLineChart()
            }
        }
    }

    fun getIncomeAndExpensesForPieChart() {
        viewModelScope.launch {
            pieChartDataResult.loadOrError {
                chartsRepository.getIncomeAndExpensesForPieChart()
            }
        }
    }
}
