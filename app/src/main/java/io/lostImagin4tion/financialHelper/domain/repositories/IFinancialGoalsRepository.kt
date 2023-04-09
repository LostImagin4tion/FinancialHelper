package io.lostImagin4tion.financialHelper.domain.repositories

import io.lostImagin4tion.financialHelper.domain.entities.ui.FinancialGoalEntity

interface IFinancialGoalsRepository {

    suspend fun getAll(): List<FinancialGoalEntity>

    suspend fun addNewGoal(item: FinancialGoalEntity)
}