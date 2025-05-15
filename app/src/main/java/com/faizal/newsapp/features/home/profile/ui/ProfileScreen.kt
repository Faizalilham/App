package com.faizal.newsapp.features.home.profile.ui

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun ProfileScreen() {
    val githubUrl = "https://github.com/Faizalilham" // Ganti dengan URL GitHub kamu

    AndroidView(factory = { context ->
        WebView(context).apply {
            webViewClient = WebViewClient() // Agar tetap di dalam WebView
            settings.javaScriptEnabled = true
            loadUrl(githubUrl)
        }
    })
}