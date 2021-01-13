package com.money.management.ui.info

import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.money.management.base.BaseViewModel
import com.money.management.prefsstore.DataStoreRepository
import kotlinx.coroutines.launch

class InfoViewModel: BaseViewModel() {

    val repoMode = DataStoreRepository.getInstance().isNightMode().asLiveData()

    fun toggleNightMode() {
        viewModelScope.launch {
            DataStoreRepository.getInstance().toggleNightMode()
        }
    }
}