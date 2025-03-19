package com.faizal.newsapp.features.onboarding.model

import androidx.annotation.DrawableRes
import com.faizal.newsapp.R

data class Page(
    val tittle : String,
    val description : String,
    @DrawableRes val image : Int
)
