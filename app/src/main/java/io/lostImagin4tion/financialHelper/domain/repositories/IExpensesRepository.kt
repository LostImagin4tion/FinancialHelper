package io.lostImagin4tion.financialHelper.domain.repositories

import io.lostImagin4tion.financialHelper.domain.entities.ui.ExpensesEntity

interface IExpensesRepository {

    suspend fun getAll(): List<ExpensesEntity>

    suspend fun addNewExpenses(item: ExpensesEntity)
}
