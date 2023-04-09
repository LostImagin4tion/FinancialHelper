package io.lostImagin4tion.financialHelper.domain.repositories

import io.lostImagin4tion.financialHelper.domain.entities.ui.IncomeEntity

interface IIncomeRepository {

    suspend fun getAll(): List<IncomeEntity>

    suspend fun addNewIncome(item: IncomeEntity)
}