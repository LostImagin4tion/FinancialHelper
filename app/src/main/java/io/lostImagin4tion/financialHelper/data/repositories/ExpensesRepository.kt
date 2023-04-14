package io.lostImagin4tion.financialHelper.data.repositories

import androidx.compose.ui.graphics.Color
import io.lostImagin4tion.financialHelper.R
import io.lostImagin4tion.financialHelper.data.repositories.utils.withIO
import io.lostImagin4tion.financialHelper.data.room.converters.DateConverter
import io.lostImagin4tion.financialHelper.data.room.dao.ExpensesDao
import io.lostImagin4tion.financialHelper.data.room.entities.ExpensesRoomEntity
import io.lostImagin4tion.financialHelper.domain.entities.ui.ExpensesCategories
import io.lostImagin4tion.financialHelper.domain.entities.ui.ExpensesEntity
import io.lostImagin4tion.financialHelper.domain.repositories.IExpensesRepository
import javax.inject.Inject

class ExpensesRepository @Inject constructor(
    private val expensesDao: ExpensesDao
) : IExpensesRepository {

    private val expensesCategories = ExpensesCategories.values().associateBy { it.name }

    override suspend fun getAll(): List<ExpensesEntity> = withIO {
        expensesDao.getAll().map {
            ExpensesEntity(
                title = it.title,
                type = it.type,
                description = it.description,
                sum = it.sum,
                date = DateConverter.fromDateInMillis(it.dateInMillis),
                iconRes = expensesCategories[it.type]?.iconRes ?: R.drawable.ic_like,
                iconBackgroundColor = expensesCategories[it.type]?.color ?: Color.White
            )
        }
    }

    override suspend fun addNewExpenses(item: ExpensesEntity) = withIO {
        expensesDao.insert(
            ExpensesRoomEntity(
                title = item.title,
                type = item.type,
                description = item.description,
                sum = item.sum,
                dateInMillis = DateConverter.toDateInMillis(item.date)
            )
        )
    }
}
