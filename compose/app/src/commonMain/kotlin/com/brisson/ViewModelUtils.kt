package com.brisson

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import com.brisson.viewmodel.ViewModel
import org.koin.java.KoinJavaComponent.get

@Composable
inline fun <reified T: ViewModel> rememberViewModel(): T {
    val viewModel: T = get(T::class.java)

    DisposableEffect(viewModel) {
        onDispose { viewModel.onDispose() }
    }

    return viewModel
}

