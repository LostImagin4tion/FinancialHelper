package io.lostImagin4tion.financialHelper.domain.entities.ui

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.patrykandpatrick.vico.core.entry.ChartEntry
import io.lostImagin4tion.financialHelper.R

data class WelcomeScreenEntity(
    @StringRes val subtitleRes: Int,
    @RawRes val animationRes: Int
)

enum class Categories(
    @StringRes val title: Int
) {
    Expenses(R.string.new_item_screen_expenses),
    Income(R.string.new_item_screen_income)
}

enum class ExpensesCategories(
    @StringRes val nameRes: Int,
    @DrawableRes val iconRes: Int,
    val color: Color
) {
    Rent(
        R.string.expenses_rent,
        R.drawable.ic_key,
        Color(0xFFF44336)
    ),
    Groceries(
        R.string.expenses_groceries,
        R.drawable.ic_shopping_cart,
        Color(0xFF802A2A)
    ),
    UtilityBills(
        R.string.expenses_utility_bills,
        R.drawable.ic_water,
        Color(0xFF2054F3)
    ),
    Transport(
        R.string.expenses_transport,
        R.drawable.ic_transport,
        Color(0xFFE91E63)
    ),
    LoansAndDebts(
        R.string.expenses_loans_and_debts,
        R.drawable.ic_bank,
        Color(0xFF00BCD4)
    ),
    Transfers(
        R.string.expenses_transfers,
        R.drawable.ic_credit_card,
        Color(0xFF97C561)
    ),
    Health(
        R.string.expenses_health,
        R.drawable.ic_health,
        Color(0xFF009E06)
    ),
    Clothes(
        R.string.expenses_clothes,
        R.drawable.ic_shopping_bag,
        Color(0xFF4B5EC5)
    ),
    Entertainment(
        R.string.expenses_entertainment,
        R.drawable.ic_like,
        Color(0xFFFFD53C)
    ),
    RestaurantsAndCafe(
        R.string.expenses_restaurants_and_cafe,
        R.drawable.ic_coffee,
        Color(0xFF84AA00)
    ),
    Gifts(
        R.string.expenses_gifts,
        R.drawable.ic_gift,
        Color(0xFFFF9869)
    ),
    Other(
        R.string.expenses_other,
        R.drawable.ic_more,
        Color(0xFFB16AB8)
    )
}

enum class IncomeCategories(
    @StringRes val nameRes: Int,
    @DrawableRes val iconRes: Int,
    val color: Color
) {
    Salary(
        R.string.income_salary,
        R.drawable.ic_dollar,
        Color(0xFF058B00)
    ),
    Gifts(
        R.string.income_gifts,
        R.drawable.ic_gift,
        Color(0xFFFF9869)
    ),
    Transfers(
        R.string.income_transfers,
        R.drawable.ic_credit_card,
        Color(0xFF97C561)
    ),
    Debts(
        R.string.income_debts,
        R.drawable.ic_bank,
        Color(0xFF00BCD4)
    ),
    StockAndBonds(
        R.string.income_stock_and_bounds,
        R.drawable.ic_chart,
        Color(0xFFF50057)
    ),
    Rent(
        R.string.income_rent,
        R.drawable.ic_key,
        Color(0xFFF44336)
    ),
    Other(
        R.string.income_other,
        R.drawable.ic_more,
        Color(0xFFB16AB8)
    )
}

data class IncomeEntity(
    val title: String,
    val type: String,
    val description: String,
    val sum: Double,
    val date: String,
    @DrawableRes val iconRes: Int,
    val iconBackgroundColor: Color
)

data class ExpensesEntity(
    val title: String,
    val type: String,
    val description: String,
    val sum: Double,
    val date: String,
    @DrawableRes val iconRes: Int,
    val iconBackgroundColor: Color
)

data class FinancialGoalEntity(
    val title: String,
    val description: String,
    val sumToAchieve: Double,
    val targetDate: String
)

data class LineChartPointEntity(
    val date: String,
    val sum: Float
)

data class LineChartEntity(
    val points: List<LineChartPointEntity>,
    val lineColor: Color,
    @StringRes val lineLegendRes: Int
)

data class IncomeAndExpensesLineChartData(
    val incomeData: LineChartEntity,
    val expensesData: LineChartEntity
)

class CustomChartEntry(
    val date: String,
    override val x: Float,
    override val y: Float
): ChartEntry {
    override fun withY(y: Float) = CustomChartEntry(date, x, y)
}

data class PieChartEntity(
    val sum: Float,
    @StringRes val typeRes: Int,
    val color: Color
)

data class IncomeAndExpensesPieChartData(
    val incomeData: List<PieChartEntity>,
    val expensesData: List<PieChartEntity>
)