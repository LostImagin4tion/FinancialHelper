package io.lostImagin4tion.financialHelper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import io.lostImagin4tion.financialHelper.ui.theme.FinancialHelperTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FinancialHelperTheme {
            }
        }
    }
}
