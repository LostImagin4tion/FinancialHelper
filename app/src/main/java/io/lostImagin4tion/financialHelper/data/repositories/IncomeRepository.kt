package io.lostImagin4tion.financialHelper.data.repositories

import androidx.compose.ui.graphics.Color
import io.lostImagin4tion.financialHelper.R
import io.lostImagin4tion.financialHelper.data.repositories.utils.withIO
import io.lostImagin4tion.financialHelper.data.room.dao.IncomeDao
import io.lostImagin4tion.financialHelper.data.room.entities.IncomeRoomEntity
import io.lostImagin4tion.financialHelper.domain.entities.ui.IncomeCategories
import io.lostImagin4tion.financialHelper.domain.entities.ui.IncomeEntity
import io.lostImagin4tion.financialHelper.domain.repositories.IIncomeRepository
import javax.inject.Inject

class IncomeRepository @Inject constructor(
    private val incomeDao: IncomeDao
): IIncomeRepository {

    private val incomeCategories = IncomeCategories.values().associateBy { it.name }

    override suspend fun getAll(): List<IncomeEntity> = withIO {
        incomeDao.getAll().map {
            IncomeEntity(
                title = it.title,
                type = it.type,
                description = it.description,
                sum = it.sum,
                date = it.date,
                iconRes = incomeCategories[it.type]?.iconRes ?: R.drawable.ic_like,
                iconBackgroundColor = incomeCategories[it.type]?.color ?: Color.White
            )
        }
    }

    override suspend fun addNewIncome(item: IncomeEntity) = withIO {
        incomeDao.insert(
            IncomeRoomEntity(
                title = item.title,
                type = item.type,
                description = item.description,
                sum = item.sum,
                date = item.date
            )
        )
    }
}