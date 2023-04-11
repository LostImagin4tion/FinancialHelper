package io.lostImagin4tion.financialHelper.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import io.lostImagin4tion.financialHelper.data.room.TableNames
import io.lostImagin4tion.financialHelper.data.room.entities.FinancialGoalRoomEntity

@Dao
interface FinancialGoalDao {

    @Query("SELECT * FROM $TABLE_NAME ORDER BY target_date DESC")
    fun getAll(): List<FinancialGoalRoomEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(goal: FinancialGoalRoomEntity)

    @Update
    fun update(goal: FinancialGoalRoomEntity)

    @Delete
    fun delete(goal: FinancialGoalRoomEntity)

    companion object {
        private const val TABLE_NAME = TableNames.FINANCIAL_GOALS_TABLE
    }
}