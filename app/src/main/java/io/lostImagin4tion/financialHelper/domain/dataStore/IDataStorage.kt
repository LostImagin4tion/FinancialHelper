package io.lostImagin4tion.financialHelper.domain.dataStore

interface IDataStorage {

    val isFirstLaunch: Boolean
    fun setIsFirstLaunch(newValue: Boolean)
}