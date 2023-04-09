package io.lostImagin4tion.financialHelper.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.lostImagin4tion.financialHelper.data.room.dao.ExpensesDao
import io.lostImagin4tion.financialHelper.data.room.dao.FinancialGoalDao
import io.lostImagin4tion.financialHelper.data.room.dao.IncomeDao
import io.lostImagin4tion.financialHelper.data.room.entities.ExpensesRoomEntity
import io.lostImagin4tion.financialHelper.data.room.entities.FinancialGoalRoomEntity
import io.lostImagin4tion.financialHelper.data.room.entities.IncomeRoomEntity

@Database(
    entities = [
        FinancialGoalRoomEntity::class,
        ExpensesRoomEntity::class,
        IncomeRoomEntity::class
    ],
    version = 1
)
abstract class FinancialHelperDatabase: RoomDatabase() {
    abstract fun financialGoalsDao(): FinancialGoalDao
    abstract fun expensesDao(): ExpensesDao
    abstract fun incomeDao(): IncomeDao

    companion object {
        @Volatile
        private var INSTANCE: FinancialHelperDatabase? = null

        fun getInstance(context: Context): FinancialHelperDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FinancialHelperDatabase::class.java,
                        "financial_helper_database"
                    ).build()
                }
                return instance
            }
        }
    }
}