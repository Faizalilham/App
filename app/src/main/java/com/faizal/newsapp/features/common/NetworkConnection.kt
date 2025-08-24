package com.faizal.newsapp.features.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.faizal.core.utils.NetworkConnectionManager
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue

@Composable
fun rememberNetworkConnection(): State<Boolean> {
    val context = LocalContext.current
    val networkConnectionManager = remember {
        NetworkConnectionManager(context)
    }

    return networkConnectionManager.observeNetworkConnection()
        .collectAsState(initial = networkConnectionManager.isConnected())
}

@Composable
fun ConnectionAware(
    onConnected: @Composable () -> Unit,
    onDisconnected: @Composable () -> Unit
) {
    val isConnected by rememberNetworkConnection()

    if (isConnected) {
        onConnected()
    } else {
        onDisconnected()
    }
}