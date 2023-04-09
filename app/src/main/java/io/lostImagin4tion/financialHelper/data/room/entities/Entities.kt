package io.lostImagin4tion.financialHelper.data.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.lostImagin4tion.financialHelper.data.room.TableNames

@Entity(tableName = TableNames.FINANCIAL_GOALS_TABLE)
data class FinancialGoalRoomEntity(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "sum_to_achieve") val sumToAchieve: Double,
    @ColumnInfo(name = "target_date") val targetDate: String
)

@Entity(tableName = TableNames.EXPENSES_TABLE)
data class ExpensesRoomEntity(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "sum") val sum: Double,
    @ColumnInfo(name = "date") val date: String
)

@Entity(tableName = TableNames.INCOME_TABLE)
data class IncomeRoomEntity(
    @PrimaryKey val uid: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "sum") val sum: Double,
    @ColumnInfo(name = "date") val date: String
)