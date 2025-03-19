package com.faizal.newsapp.features.onboarding.viewmodel

sealed class OnBoardingEvent {

    data object SaveAppEntry : OnBoardingEvent()
}