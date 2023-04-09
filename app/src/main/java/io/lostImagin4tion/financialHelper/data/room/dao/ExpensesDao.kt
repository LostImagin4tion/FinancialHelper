package io.lostImagin4tion.financialHelper.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import io.lostImagin4tion.financialHelper.data.room.TableNames
import io.lostImagin4tion.financialHelper.data.room.entities.ExpensesRoomEntity

@Dao
interface ExpensesDao {

    @Query("SELECT * FROM $TABLE_NAME")
    fun getAll(): List<ExpensesRoomEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: ExpensesRoomEntity)

    @Update
    fun update(entity: ExpensesRoomEntity)

    @Delete
    fun delete(entity: ExpensesRoomEntity)

    companion object {
        private const val TABLE_NAME = TableNames.EXPENSES_TABLE
    }
}