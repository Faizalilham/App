package com.faizal.newsapp.features.onboarding.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faizal.newsapp.datastore.domain.LocalUserManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val localUserManager: LocalUserManager
) : ViewModel() {

    fun onEvent(event : OnBoardingEvent){
        when(event){
            is OnBoardingEvent.SaveAppEntry -> saveAppEntry()
        }
    }

    private fun saveAppEntry(){
        viewModelScope.launch {
            localUserManager.saveAppEntry()
        }
    }

}