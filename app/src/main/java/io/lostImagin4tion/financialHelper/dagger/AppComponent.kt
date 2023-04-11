package io.lostImagin4tion.financialHelper.dagger

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import io.lostImagin4tion.financialHelper.ui.screens.expenses.ExpensesViewModel
import io.lostImagin4tion.financialHelper.ui.screens.financialGoals.FinancialGoalsViewModel
import io.lostImagin4tion.financialHelper.ui.screens.home.HomeViewModel
import io.lostImagin4tion.financialHelper.ui.screens.income.IncomeViewModel
import io.lostImagin4tion.financialHelper.ui.screens.newFinancialGoal.NewFinancialGoalViewModel
import io.lostImagin4tion.financialHelper.ui.screens.newItem.NewItemViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [DataModule::class, RepositoriesModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }

    fun inject(incomeViewModel: IncomeViewModel)
    fun inject(newItemViewModel: NewItemViewModel)
    fun inject(expensesViewModel: ExpensesViewModel)
    fun inject(newFinancialGoalViewModel: NewFinancialGoalViewModel)
    fun inject(financialGoalsViewModel: FinancialGoalsViewModel)
    fun inject(homeViewModel: HomeViewModel)
}