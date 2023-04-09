package io.lostImagin4tion.financialHelper.ui.screens.newFinancialGoal

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import io.lostImagin4tion.financialHelper.R
import io.lostImagin4tion.financialHelper.domain.entities.ui.FinancialGoalEntity
import io.lostImagin4tion.financialHelper.ui.components.buttons.TextFilledButton
import io.lostImagin4tion.financialHelper.ui.components.pickers.CustomDatePickerDialog
import io.lostImagin4tion.financialHelper.ui.components.text.DisplayText
import io.lostImagin4tion.financialHelper.ui.components.textFields.CustomOutlinedTextField
import io.lostImagin4tion.financialHelper.ui.theme.Dimensions
import io.lostImagin4tion.financialHelper.ui.utils.getRuFormattedDate
import java.util.GregorianCalendar

@Composable
fun NewFinancialGoalScreen(
    navController: NavHostController
) {
    val viewModel: NewFinancialGoalViewModel = viewModel()

    val saveNewGoal: (FinancialGoalEntity) -> Unit = {
        viewModel.addNewGoal(it)
        navController.popBackStack()
    }

    NewFinancialGoalScreenContent(
        navigateBack = navController::popBackStack,
        saveItem = saveNewGoal
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewFinancialGoalScreenContent(
    navigateBack: () -> Unit = {},
    saveItem: (FinancialGoalEntity) -> Unit = {}
) = Column(
    verticalArrangement = Arrangement.Top,
    horizontalAlignment = Alignment.Start,
    modifier = Modifier
        .fillMaxSize()
        .padding(Dimensions.mainHorizontalPadding / 2)
        .imePadding()
) {
    val datePickerState = rememberDatePickerState()
    val calendar = GregorianCalendar()

    var titleInput by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }
    var targetMoneyInput by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }
    var targetDateInput by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }
    var descriptionInput by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue())
    }

    var isDatePickerDisplayed by rememberSaveable {
        mutableStateOf(false)
    }

    val onSaveButtonClick = {
        saveItem(
            FinancialGoalEntity(
                title = titleInput.text,
                description = descriptionInput.text,
                sumToAchieve = targetMoneyInput.text.toDouble(),
                targetDate = targetDateInput.text
            )
        )
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

                    targetDateInput = TextFieldValue(date)
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
            textRes = R.string.new_financial_goal_screen_title
        )
    }

    Spacer(modifier = Modifier.height(Dimensions.mainVerticalPadding * 2))

    CustomOutlinedTextField(
        value = titleInput,
        onValueChange = { titleInput = it },
        labelRes = R.string.new_financial_goal_screen_title_text_field,
        shape = RoundedCornerShape(15)
    )

    Spacer(modifier = Modifier.height(Dimensions.mainVerticalPadding * 2))

    CustomOutlinedTextField(
        value = targetMoneyInput,
        onValueChange = { targetMoneyInput = it },
        labelRes = R.string.new_financial_goal_screen_target_money,
        shape = RoundedCornerShape(15),
        keyboardType = KeyboardType.Decimal
    )

    Spacer(modifier = Modifier.height(Dimensions.mainVerticalPadding * 2))

    CustomOutlinedTextField(
        value = targetDateInput,
        enabled = false,
        readOnly = true,
        onValueChange = {
            targetDateInput = it
        },
        labelRes = R.string.new_financial_goal_screen_target_date,
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

    Spacer(modifier = Modifier.weight(1f))

    TextFilledButton(
        onClick = onSaveButtonClick,
        isEnabled = titleInput.text.isNotBlank() &&
                targetMoneyInput.text.isNotBlank() &&
                targetDateInput.text.isNotBlank(),
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