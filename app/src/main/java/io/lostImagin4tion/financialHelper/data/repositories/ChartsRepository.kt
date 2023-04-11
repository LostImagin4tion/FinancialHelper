package io.lostImagin4tion.financialHelper.data.repositories

import io.lostImagin4tion.financialHelper.data.repositories.utils.withIO
import io.lostImagin4tion.financialHelper.data.room.converters.DateConverter
import io.lostImagin4tion.financialHelper.data.room.dao.ExpensesDao
import io.lostImagin4tion.financialHelper.data.room.dao.IncomeDao
import io.lostImagin4tion.financialHelper.domain.entities.ui.ExpensesCategories
import io.lostImagin4tion.financialHelper.domain.entities.ui.IncomeAndExpensesLineChartData
import io.lostImagin4tion.financialHelper.domain.entities.ui.IncomeAndExpensesPieChartData
import io.lostImagin4tion.financialHelper.domain.entities.ui.IncomeCategories
import io.lostImagin4tion.financialHelper.domain.entities.ui.LineChartEntity
import io.lostImagin4tion.financialHelper.domain.entities.ui.LineChartPointEntity
import io.lostImagin4tion.financialHelper.domain.entities.ui.PieChartEntity
import io.lostImagin4tion.financialHelper.domain.repositories.IChartsRepository
import io.lostImagin4tion.financialHelper.ui.theme.finHelperExpensesChartColor
import io.lostImagin4tion.financialHelper.ui.theme.finHelperIncomeChartColor
import javax.inject.Inject

class ChartsRepository @Inject constructor(
    private val incomeDao: IncomeDao,
    private val expensesDao: ExpensesDao
): IChartsRepository {

    private val expensesCategories = ExpensesCategories.values().associateBy { it.name }
    private val incomeCategories = IncomeCategories.values().associateBy { it.name }

    override suspend fun getIncomeAndExpensesForLineChart(): IncomeAndExpensesLineChartData = withIO {
        val incomes = incomeDao.getSumTypeAndDate()
            .map {
                LineChartPointEntity(
                    date = DateConverter.fromDateInMillis(it.dateInMillis),
                    sum = it.sum.toFloat()
                )
            }

        val expenses = expensesDao.getSumTypeAndDate()
            .map {
                LineChartPointEntity(
                    date = DateConverter.fromDateInMillis(it.dateInMillis),
                    sum = it.sum.toFloat()
                )
            }

        val incomeLine = LineChartEntity(
            points = incomes,
            lineColor = finHelperIncomeChartColor,
            lineLegend = "Income"
        )
        val expensesLine = LineChartEntity(
            points = expenses,
            lineColor = finHelperExpensesChartColor,
            lineLegend = "Expenses"
        )

        IncomeAndExpensesLineChartData(
            incomeData = incomeLine,
            expensesData = expensesLine
        )
    }

    override suspend fun getIncomeAndExpensesForPieChart(): IncomeAndExpensesPieChartData = withIO {
        val incomesMap = hashMapOf<String, Float>()
        val expensesMap = hashMapOf<String, Float>()

        incomeDao.getSumTypeAndDate().forEach {
            incomesMap[it.type] = incomesMap.getOrDefault(it.type, 0f) + it.sum.toFloat()
        }

        expensesDao.getSumTypeAndDate().forEach {
            expensesMap[it.type] = incomesMap.getOrDefault(it.type, 0f) + it.sum.toFloat()
        }

        val incomes = incomesMap.map {
            PieChartEntity(
                sum = it.value,
                typeRes = incomeCategories[it.key]?.nameRes!!,
                color = incomeCategories[it.key]?.color!!
            )
        }

        val expenses = expensesMap.map {
            PieChartEntity(
                sum = it.value,
                typeRes = expensesCategories[it.key]?.nameRes!!,
                color = expensesCategories[it.key]?.color!!
            )
        }

        IncomeAndExpensesPieChartData(
            incomeData = incomes,
            expensesData = expenses
        )
    }
}