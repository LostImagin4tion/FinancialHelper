package io.lostImagin4tion.financialHelper.dagger

import android.content.Context
import dagger.Module
import dagger.Provides
import io.lostImagin4tion.financialHelper.data.room.FinancialHelperDatabase
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideDataBase(context: Context): FinancialHelperDatabase =
        FinancialHelperDatabase.getInstance(context)

    @Provides
    @Singleton
    fun provideFinancialHelpersDao(database: FinancialHelperDatabase) = database.financialGoalsDao()

    @Provides
    @Singleton
    fun provideExpensesDao(database: FinancialHelperDatabase) = database.expensesDao()

    @Provides
    @Singleton
    fun provideIncomeDao(database: FinancialHelperDatabase) = database.incomeDao()
}