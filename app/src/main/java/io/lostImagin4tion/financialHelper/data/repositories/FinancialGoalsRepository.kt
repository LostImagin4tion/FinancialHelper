package io.lostImagin4tion.financialHelper.data.repositories

import io.lostImagin4tion.financialHelper.data.repositories.utils.withIO
import io.lostImagin4tion.financialHelper.data.room.converters.DateConverter
import io.lostImagin4tion.financialHelper.data.room.dao.FinancialGoalDao
import io.lostImagin4tion.financialHelper.data.room.entities.FinancialGoalRoomEntity
import io.lostImagin4tion.financialHelper.domain.entities.ui.FinancialGoalEntity
import io.lostImagin4tion.financialHelper.domain.repositories.IFinancialGoalsRepository
import javax.inject.Inject

class FinancialGoalsRepository @Inject constructor(
    private val financialGoalsDao: FinancialGoalDao
) : IFinancialGoalsRepository {

    override suspend fun getAll(): List<FinancialGoalEntity> = withIO {
        financialGoalsDao.getAll().map {
            FinancialGoalEntity(
                title = it.title,
                description = it.description,
                sumToAchieve = it.sumToAchieve,
                targetDate = DateConverter.fromDateInMillis(it.targetDateInMillis)
            )
        }
    }

    override suspend fun addNewGoal(item: FinancialGoalEntity) = withIO {
        financialGoalsDao.insert(
            FinancialGoalRoomEntity(
                title = item.title,
                description = item.description,
                sumToAchieve = item.sumToAchieve,
                targetDateInMillis = DateConverter.toDateInMillis(item.targetDate)
            )
        )
    }
}
