package com.faizal.newsapp.domain.model

import androidx.annotation.DrawableRes

data class BottomNavigationItem(
    @DrawableRes val icon: Int,
    val text: String
)
