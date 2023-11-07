package com.app.feature.coffee.util

import android.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import org.junit.Rule

@OptIn(ExperimentalCoroutinesApi::class)
abstract class BaseCoroutineTestWithTestDispatcherProviderAndInstantTaskExecutorRule(
    dispatcher: TestDispatcher
) : BaseCoroutineTestWithTestDispatcherProvider(
    dispatcher = dispatcher
) {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

}