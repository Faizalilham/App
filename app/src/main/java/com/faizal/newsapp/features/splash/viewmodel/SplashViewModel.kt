package com.faizal.newsapp.features.splash.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.faizal.core.domain.usecases.datastore.ReadAppEntry
import com.faizal.newsapp.nvgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val readAppEntry: ReadAppEntry
): ViewModel() {

    private val _splashCondition = mutableStateOf(true)
    val splashCondition: State<Boolean> = _splashCondition

    private val _startDestination = mutableStateOf(Route.AppStartNavigation.route)
    val startDestination: State<String> = _startDestination

    init {
        readAppEntry().onEach { shouldStartFromHomeScreen ->
            if(shouldStartFromHomeScreen){
                _startDestination.value = Route.NewsNavigation.route
            }else{
                _startDestination.value = Route.NewsNavigation.route
//                _startDestination.value = Route.AppStartNavigation.route // ngga dipake buat test
            }
            delay(400)
            _splashCondition.value = false
        }.launchIn(viewModelScope)
    }
}