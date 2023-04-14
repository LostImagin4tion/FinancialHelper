package io.lostImagin4tion.financialHelper.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import io.lostImagin4tion.financialHelper.data.room.TableNames
import io.lostImagin4tion.financialHelper.data.room.entities.IncomeRoomEntity
import io.lostImagin4tion.financialHelper.data.room.entities.IncomeSumDateType

@Dao
interface IncomeDao {

    @Query("SELECT * FROM $TABLE_NAME ORDER BY date DESC")
    fun getAll(): List<IncomeRoomEntity>

    @Query("SELECT sum, type, date FROM $TABLE_NAME ORDER BY date")
    fun getSumTypeAndDate(): List<IncomeSumDateType>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: IncomeRoomEntity)

    @Update
    fun update(entity: IncomeRoomEntity)

    @Delete
    fun delete(entity: IncomeRoomEntity)

    companion object {
        private const val TABLE_NAME = TableNames.INCOME_TABLE
    }
}
