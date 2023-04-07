package io.lostImagin4tion.financialHelper.domain.entities.ui

import androidx.annotation.RawRes
import androidx.annotation.StringRes

data class WelcomeScreenEntity(
    @StringRes val subtitleRes: Int,
    @RawRes val animationRes: Int
)