package io.lostImagin4tion.financialHelper.domain.repositories

import io.lostImagin4tion.financialHelper.domain.entities.ui.IncomeAndExpensesLineChartData
import io.lostImagin4tion.financialHelper.domain.entities.ui.IncomeAndExpensesPieChartData

interface IChartsRepository {

    suspend fun getIncomeAndExpensesForLineChart(): IncomeAndExpensesLineChartData

    suspend fun getIncomeAndExpensesForPieChart(): IncomeAndExpensesPieChartData
}