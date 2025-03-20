package com.faizal.newsapp.helper

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@ExperimentalCoroutinesApi
class MainDispatcherRule : TestWatcher(), TestCoroutineScope by TestCoroutineScope() {

    override fun starting(description: Description?) {
        Dispatchers.setMain(StandardTestDispatcher())
    }

    override fun finished(description: Description?) {
        Dispatchers.resetMain()
        cleanupTestCoroutines()
    }
}
