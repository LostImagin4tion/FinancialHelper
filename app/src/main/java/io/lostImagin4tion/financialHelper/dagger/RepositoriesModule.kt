package io.lostImagin4tion.financialHelper.dagger

import dagger.Binds
import dagger.Module
import io.lostImagin4tion.financialHelper.data.repositories.ExpensesRepository
import io.lostImagin4tion.financialHelper.data.repositories.FinancialGoalsRepository
import io.lostImagin4tion.financialHelper.data.repositories.IncomeRepository
import io.lostImagin4tion.financialHelper.domain.repositories.IExpensesRepository
import io.lostImagin4tion.financialHelper.domain.repositories.IFinancialGoalsRepository
import io.lostImagin4tion.financialHelper.domain.repositories.IIncomeRepository
import javax.inject.Singleton

@Module
abstract class RepositoriesModule {

    @Binds
    @Singleton
    abstract fun bindIncomeRepository(impl: IncomeRepository): IIncomeRepository

    @Binds
    @Singleton
    abstract fun bindExpensesRepository(impl: ExpensesRepository): IExpensesRepository

    @Binds
    @Singleton
    abstract fun bindFinancialGoalsRepository(impl: FinancialGoalsRepository): IFinancialGoalsRepository
}