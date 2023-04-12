package io.lostImagin4tion.financialHelper.ui.screens.newItem

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconButton
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import io.lostImagin4tion.financialHelper.R
import io.lostImagin4tion.financialHelper.domain.entities.ui.Categories
import io.lostImagin4tion.financialHelper.domain.entities.ui.ExpensesCategories
import io.lostImagin4tion.financialHelper.domain.entities.ui.ExpensesEntity
import io.lostImagin4tion.financialHelper.domain.entities.ui.IncomeCategories
import io.lostImagin4tion.financialHelper.domain.entities.ui.IncomeEntity
import io.lostImagin4tion.financialHelper.ui.components.buttons.TextFilledButton
import io.lostImagin4tion.financialHelper.ui.components.pickers.CustomDatePickerDialog
import io.lostImagin4tion.financialHelper.ui.components.text.DisplayText
import io.lostImagin4tion.financialHelper.ui.components.text.LabelText
import io.lostImagin4tion.financialHelper.ui.components.text.SubtitleText
import io.lostImagin4tion.financialHelper.ui.components.textFields.CustomOutlinedTextField
import io.lostImagin4tion.financialHelper.ui.theme.Dimensions
import io.lostImagin4tion.financialHelper.ui.theme.finHelperGray
import io.lostImagin4tion.financialHelper.ui.utils.getRuFormattedDate
import java.util.GregorianCalendar

@Composable
fun NewItemScreen(
    navController: NavHostController
) {
    val viewModel: NewItemViewModel = viewModel()

    val incomeCategories = IncomeCategories.values().toList()
    val expensesCategories = ExpensesCategories.values().toList()

    val radioButtonOptions = Categories.values().toList()

    val addNewIncome: (IncomeEntity) -> Unit = {
        viewModel.addNewIncome(it)
        navController.popBackStack()
    }

    val addNewExpenses: (ExpensesEntity) -> Unit = {
        viewModel.addNewExpenses(it)
        navController.popBackStack()
    }

    NewItemScreenContent(
        incomeCategories = incomeCategories,
        expensesCategories = expensesCategories,
        radioButtonOptions = radioButtonOptions,
        addNewIncome = addNewIncome,
        addNewExpenses = addNewExpenses,
        navigateBack = navController::popBackStack,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewItemScreenContent(
    incomeCategories: List<IncomeCategories> = emptyList(),
    expensesCategories: List<ExpensesCategories> = emptyList(),
    radioButtonOptions: List<Categories> = emptyList(),
    addNewIncome: (IncomeEntity) -> Unit = {},
    addNewExpenses: (ExpensesEntity) -> Unit = {},
    navigateBack: () -> Unit = {},
) = Column(
    verticalArrangement = Arrangement.Top,
    horizontalAlignment = Alignment.Start,
    modifier = Modifier
        .fillMaxSize()
        .padding(Dimensions.mainHorizontalPadding / 2)
        .imePadding()
        .verticalScroll(rememberScrollState())
) {
    val iconBackgroundSize = 48.dp
    val iconSize = 32.dp

    val datePickerState = rememberDatePickerState()
    val calendar = GregorianCalendar()

    var titleInput by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }
    var moneyInput by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }
    var dateInput by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }
    var descriptionInput by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }

    var isIncomeOrExpenses by rememberSaveable {
        mutableStateOf(radioButtonOptions[0])
    }

    var chosenIncomeCategory by rememberSaveable {
        mutableStateOf(incomeCategories.random())
    }
    var chosenExpensesCategory by rememberSaveable {
        mutableStateOf(expensesCategories.random())
    }

    var isCategoriesMenuExpanded by rememberSaveable {
        mutableStateOf(false)
    }
    var isDatePickerDisplayed by rememberSaveable {
        mutableStateOf(false)
    }

    val onRadioButtonClick: (Categories) -> Unit = { isIncomeOrExpenses = it }

    val onSaveButtonClick: () -> Unit = {
        if (isIncomeOrExpenses == Categories.Income) {
            addNewIncome(
                IncomeEntity(
                    title = titleInput.text,
                    type = chosenIncomeCategory.name,
                    description = descriptionInput.text,
                    sum = moneyInput.text.toDouble(),
                    date = dateInput.text,
                    iconRes = chosenIncomeCategory.iconRes,
                    iconBackgroundColor = chosenIncomeCategory.color
                )
            )
        } else {
            addNewExpenses(
                ExpensesEntity(
                    title = titleInput.text,
                    type = chosenExpensesCategory.name,
                    description = descriptionInput.text,
                    sum = moneyInput.text.toDouble(),
                    date = dateInput.text,
                    iconRes = chosenExpensesCategory.iconRes,
                    iconBackgroundColor = chosenExpensesCategory.color
                )
            )
        }
    }

    if (isDatePickerDisplayed) {
        CustomDatePickerDialog(
            state = datePickerState,
            onDismiss = { isDatePickerDisplayed = false },
            onCancelClick = { isDatePickerDisplayed = false },
            onOkClick = {
                isDatePickerDisplayed = false
                println(datePickerState.selectedDateMillis)

                datePickerState.selectedDateMillis?.let {
                    calendar.timeInMillis = it

                    val date = calendar.getRuFormattedDate()

                    dateInput = TextFieldValue(date)
                }
            }
        )
    }

    Spacer(modifier = Modifier.height(Dimensions.mainVerticalPadding))

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        IconButton(
            onClick = navigateBack,
            modifier = Modifier.size(36.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(32.dp)
            )
        }

        Spacer(modifier = Modifier.width(Dimensions.commonPadding))

        DisplayText(
            textRes = R.string.new_item_screen_title
        )
    }

    Spacer(modifier = Modifier.height(Dimensions.mainVerticalPadding * 2))

    CustomOutlinedTextField(
        value = titleInput,
        onValueChange = { titleInput = it },
        labelRes = R.string.new_item_screen_title_text_field,
        shape = RoundedCornerShape(15)
    )

    Spacer(modifier = Modifier.height(Dimensions.mainVerticalPadding * 2))

    CustomOutlinedTextField(
        value = moneyInput,
        onValueChange = { moneyInput = it },
        labelRes = R.string.new_item_money_text_field,
        shape = RoundedCornerShape(15),
        keyboardType = KeyboardType.Decimal
    )

    Spacer(modifier = Modifier.height(Dimensions.mainVerticalPadding * 2))

    CustomOutlinedTextField(
        value = dateInput,
        enabled = false,
        readOnly = true,
        onValueChange = { dateInput = it },
        labelRes = R.string.new_item_date_text_field,
        trailingIconRes = R.drawable.ic_calendar,
        onTrailingIconClick = { isDatePickerDisplayed = true },
        modifier = Modifier.clickable {
            isDatePickerDisplayed = true
        }
    )

    Spacer(modifier = Modifier.height(Dimensions.mainVerticalPadding * 2))

    CustomOutlinedTextField(
        value = descriptionInput,
        onValueChange = { descriptionInput = it },
        labelRes = R.string.new_item_screen_desc,
        minHeight = 150.dp,
        singleLine = false,
        shape = RoundedCornerShape(10)
    )

    Spacer(modifier = Modifier.height(Dimensions.mainVerticalPadding * 2))

    SubtitleText(textRes = R.string.new_item_screen_category_label)

    Spacer(modifier = Modifier.height(Dimensions.mainVerticalPadding))

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        radioButtonOptions.forEach { option ->
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .selectable(
                        selected = (option == isIncomeOrExpenses),
                        onClick = { onRadioButtonClick(option) }
                    )
            ) {
                RadioButton(
                    onClick = { onRadioButtonClick(option) },
                    selected = (option == isIncomeOrExpenses),
                    colors = RadioButtonDefaults.colors(
                        selectedColor = MaterialTheme.colorScheme.primaryContainer,
                        unselectedColor = finHelperGray
                    )
                )

                LabelText(textRes = option.title)
            }

            Spacer(modifier = Modifier.height(Dimensions.commonPadding / 2))
        }
    }

    Spacer(modifier = Modifier.height(Dimensions.mainVerticalPadding))

    SubtitleText(textRes = R.string.new_item_screen_select_category)

    Spacer(modifier = Modifier.height(Dimensions.mainVerticalPadding))

    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(RoundedCornerShape(25))
            .clickable {
                isCategoriesMenuExpanded = !isCategoriesMenuExpanded
            }
    ) {
        val iconRes = if (isIncomeOrExpenses == Categories.Income) {
            chosenIncomeCategory.iconRes
        } else {
            chosenExpensesCategory.iconRes
        }
        val stringRes = if (isIncomeOrExpenses == Categories.Income) {
            chosenIncomeCategory.nameRes
        } else {
            chosenExpensesCategory.nameRes
        }
        val iconBackgroundColor = if (isIncomeOrExpenses == Categories.Income) {
            chosenIncomeCategory.color
        } else {
            chosenExpensesCategory.color
        }

        Spacer(modifier = Modifier.width(3.dp))

        Box(
            modifier = Modifier
                .background(
                    color = iconBackgroundColor,
                    shape = CircleShape
                )
                .clip(CircleShape)
                .size(iconBackgroundSize)
        ) {
            Icon(
                painter = painterResource(iconRes),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(iconSize)
                    .align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.width(Dimensions.commonPadding))

        LabelText(
            textRes = stringRes,
            isLarge = true
        )

        Spacer(modifier = Modifier.width(Dimensions.commonPadding))
    }

    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Top,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DropdownMenu(
                expanded = isCategoriesMenuExpanded,
                onDismissRequest = { isCategoriesMenuExpanded = false }
            ) {
                if (isIncomeOrExpenses == Categories.Income) {
                    incomeCategories.forEach {
                        DropdownMenuItem(
                            onClick = {
                                chosenIncomeCategory = it
                                isCategoriesMenuExpanded = false
                            },
                            text = {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Start,
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .background(
                                                color = it.color,
                                                shape = CircleShape
                                            )
                                            .clip(CircleShape)
                                            .size(iconBackgroundSize)
                                    ) {
                                        Icon(
                                            painter = painterResource(it.iconRes),
                                            contentDescription = null,
                                            tint = Color.White,
                                            modifier = Modifier
                                                .size(iconSize)
                                                .align(Alignment.Center)
                                        )
                                    }

                                    Spacer(modifier = Modifier.width(Dimensions.commonPadding))

                                    LabelText(
                                        textRes = it.nameRes,
                                        isLarge = true
                                    )
                                }
                            },
                            contentPadding = PaddingValues(
                                vertical = Dimensions.commonPadding / 2,
                                horizontal = Dimensions.commonPadding
                            )
                        )
                    }
                } else {
                    expensesCategories.forEach {
                        DropdownMenuItem(
                            onClick = {
                                chosenExpensesCategory = it
                                isCategoriesMenuExpanded = false
                            },
                            text = {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Start
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .background(
                                                color = it.color,
                                                shape = CircleShape
                                            )
                                            .clip(CircleShape)
                                            .size(iconBackgroundSize)
                                    ) {
                                        Icon(
                                            painter = painterResource(it.iconRes),
                                            contentDescription = null,
                                            tint = Color.White,
                                            modifier = Modifier
                                                .size(iconSize)
                                                .align(Alignment.Center)
                                        )
                                    }

                                    Spacer(modifier = Modifier.width(Dimensions.commonPadding))

                                    LabelText(
                                        textRes = it.nameRes,
                                        isLarge = true
                                    )
                                }
                            },
                            contentPadding = PaddingValues(Dimensions.commonPadding)
                        )
                    }
                }
            }
        }
    }

    Spacer(modifier = Modifier.height(Dimensions.mainVerticalPadding * 2))

    TextFilledButton(
        onClick = onSaveButtonClick,
        isEnabled = titleInput.text.isNotBlank() &&
                moneyInput.text.isNotBlank() &&
                dateInput.text.isNotBlank(),
        textResource = R.string.new_item_save_button,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = Dimensions.mainVerticalPadding)
            .padding(horizontal = Dimensions.mainHorizontalPadding / 2)
    )
}