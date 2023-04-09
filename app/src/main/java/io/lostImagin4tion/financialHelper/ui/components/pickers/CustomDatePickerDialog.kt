package io.lostImagin4tion.financialHelper.ui.components.pickers

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import io.lostImagin4tion.financialHelper.R
import io.lostImagin4tion.financialHelper.ui.components.buttons.CustomTextButton
import io.lostImagin4tion.financialHelper.ui.theme.Dimensions

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePickerDialog(
    state: DatePickerState,
    onDismiss: () -> Unit = {},
    onCancelClick: () -> Unit = {},
    onOkClick: () -> Unit = {}
) {
    val datePickerColors = DatePickerDefaults.colors(
        containerColor = MaterialTheme.colorScheme.background,
        titleContentColor = MaterialTheme.colorScheme.primary,
        headlineContentColor = MaterialTheme.colorScheme.primary,
        weekdayContentColor = MaterialTheme.colorScheme.primary,
        subheadContentColor = MaterialTheme.colorScheme.primary,
        yearContentColor = MaterialTheme.colorScheme.primary,
        currentYearContentColor = MaterialTheme.colorScheme.primary,
        selectedYearContentColor = MaterialTheme.colorScheme.primary,
        selectedYearContainerColor = MaterialTheme.colorScheme.secondaryContainer,
        dayContentColor = MaterialTheme.colorScheme.primary,
        selectedDayContentColor = MaterialTheme.colorScheme.primary,
        selectedDayContainerColor = MaterialTheme.colorScheme.secondaryContainer,
        todayContentColor = MaterialTheme.colorScheme.primary,
        todayDateBorderColor = MaterialTheme.colorScheme.secondaryContainer
    )

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            CustomTextButton(
                onClick = onOkClick,
                textResource = R.string.date_picker_ok_button,
                fontSize = 18.sp,
                colors = ButtonDefaults.textButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.primary
                ),
                contentPadding = PaddingValues(
                    horizontal = Dimensions.mainHorizontalPadding / 2,
                    vertical = Dimensions.commonPadding
                ),
            )
        },
        dismissButton = {
            CustomTextButton(
                onClick = onCancelClick,
                textResource = R.string.date_picker_cancel_button,
                fontSize = 18.sp,
                colors = ButtonDefaults.textButtonColors(
                    containerColor = Color.Transparent,
                    contentColor = MaterialTheme.colorScheme.primary
                ),
                contentPadding = PaddingValues(
                    horizontal = Dimensions.mainHorizontalPadding / 2,
                    vertical = Dimensions.commonPadding
                ),
            )
        },
        colors = datePickerColors,
        modifier = Modifier.padding(horizontal = Dimensions.mainHorizontalPadding / 2)
    ) {
        DatePicker(
            state = state,
            colors = datePickerColors
        )
    }
}
