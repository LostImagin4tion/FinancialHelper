package io.lostImagin4tion.financialHelper.data.dataStore

import android.content.Context
import androidx.core.content.edit
import io.lostImagin4tion.financialHelper.domain.dataStore.IDataStorage
import timber.log.Timber
import javax.inject.Inject

class DataStorage @Inject constructor(
    private val context: Context
) : IDataStorage {

    private val sharedPreferences by lazy {
        context.getSharedPreferences(
            PREFERENCES_NAME,
            Context.MODE_PRIVATE
        )
    }
    override val isFirstLaunch by sharedPreferences.delegates.boolean(true)

    override fun setIsFirstLaunch(newValue: Boolean) {
        if (newValue != isFirstLaunch) {
            sharedPreferences.edit {
                putBoolean(::isFirstLaunch.name, newValue)
            }
            Timber.d("setIsFirstLaunch(): newValue = $newValue")
        }
    }

    private companion object {
        const val PREFERENCES_NAME = "dataStore"
    }
}
