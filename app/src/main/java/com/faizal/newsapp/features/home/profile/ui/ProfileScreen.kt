package com.faizal.newsapp.features.home.profile.ui

import android.annotation.SuppressLint
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.faizal.newsapp.UIKit.EmptyScreen
import com.faizal.newsapp.features.common.ConnectionAware
import com.faizal.newsapp.features.common.WebViewContent

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun ProfileScreen() {
    val myProfile = "https://github.com/Faizalilham"

    ConnectionAware(
        onConnected = {
            WebViewContent(url = myProfile)
        },
        onDisconnected = {
            EmptyScreen()
        }
    )
}