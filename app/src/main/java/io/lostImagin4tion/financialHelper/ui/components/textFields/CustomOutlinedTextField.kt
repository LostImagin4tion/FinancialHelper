package io.lostImagin4tion.financialHelper.ui.components.textFields

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.lostImagin4tion.financialHelper.ui.theme.finHelperGray
import io.lostImagin4tion.financialHelper.ui.theme.finHelperLightGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomOutlinedTextField(
    modifier: Modifier = Modifier,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit = {},
    onLeadingIconClick: () -> Unit = {},
    onTrailingIconClick: () -> Unit = {},
    @StringRes labelRes: Int,
    @StringRes placeholderRes: Int? = null,
    @DrawableRes trailingIconRes: Int? = null,
    @DrawableRes leadingIconRes: Int? = null,
    minHeight: Dp? = null,
    enabled: Boolean = true,
    isError: Boolean = false,
    singleLine: Boolean = true,
    readOnly: Boolean = false,
    shape: CornerBasedShape = MaterialTheme.shapes.small,
    imeAction: ImeAction = ImeAction.Done,
    keyboardType: KeyboardType = KeyboardType.Text,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }
    interactionSource.interactions

    val contentVerticalPadding = 12.dp

    val colors = TextFieldDefaults.outlinedTextFieldColors(
        focusedTextColor = MaterialTheme.colorScheme.primary,
        unfocusedTextColor = MaterialTheme.colorScheme.primary,
        cursorColor = MaterialTheme.colorScheme.primary,
        errorCursorColor = MaterialTheme.colorScheme.error,
        focusedBorderColor = MaterialTheme.colorScheme.primaryContainer,
        unfocusedBorderColor = finHelperGray,
        errorBorderColor = MaterialTheme.colorScheme.error,
        errorTrailingIconColor = MaterialTheme.colorScheme.error,
        focusedLabelColor = MaterialTheme.colorScheme.primary,
        unfocusedLabelColor = MaterialTheme.colorScheme.primary,
        disabledLabelColor = MaterialTheme.colorScheme.primary,
        errorLabelColor = MaterialTheme.colorScheme.error,
        unfocusedPlaceholderColor = finHelperGray,
        focusedPlaceholderColor = finHelperGray,
        disabledBorderColor = finHelperGray,
        disabledTextColor = MaterialTheme.colorScheme.primary
    )

    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = MaterialTheme.typography.labelMedium.copy(
                color = MaterialTheme.colorScheme.primary
            ),
            cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
            enabled = enabled,
            singleLine = singleLine,
            readOnly = readOnly,
            visualTransformation = visualTransformation,
            interactionSource = interactionSource,
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .then(
                    minHeight?.let {
                        Modifier.defaultMinSize(minHeight = it)
                    } ?: Modifier
                ),
        ) { innerTextField ->
            TextFieldDefaults.OutlinedTextFieldDecorationBox(
                value = value.text,
                innerTextField = innerTextField,
                enabled = enabled,
                singleLine = singleLine,
                visualTransformation = visualTransformation,
                contentPadding = PaddingValues(
                    horizontal = 16.dp,
                    vertical = contentVerticalPadding
                ),
                interactionSource = interactionSource,
                label = {
                    Text(
                        text = stringResource(labelRes),
                        style = MaterialTheme.typography.labelMedium,
                    )
                },
                leadingIcon = leadingIconRes?.let {
                    {
                        IconButton(
                            onClick = onTrailingIconClick,
                            modifier = Modifier.size(28.dp)
                        ) {
                            Icon(
                                painter = painterResource(it),
                                contentDescription = null,
                                tint = finHelperLightGray,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                },
                trailingIcon = trailingIconRes?.let {
                    {
                        IconButton(
                            onClick = onTrailingIconClick,
                            modifier = Modifier.size(28.dp)
                        ) {
                            Icon(
                                painter = painterResource(trailingIconRes),
                                contentDescription = null,
                                tint = finHelperLightGray,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }
                },
                placeholder = placeholderRes?.let {
                    {
                        Text(
                            text = stringResource(it),
                            style = MaterialTheme.typography.labelMedium,
                        )
                    }
                },
                colors = colors,
                container = {
                    TextFieldDefaults.OutlinedBorderContainerBox(
                        enabled = enabled,
                        isError = isError,
                        interactionSource = interactionSource,
                        shape = shape,
                        colors = colors,
                        unfocusedBorderThickness = 1.dp,
                        focusedBorderThickness = 1.dp
                    )
                }
            )
        }
    }
}
