package com.brisson

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.brisson.viewmodel.HomeViewModel
import com.brisson.viewmodel.HomeViewState

@Composable
fun App() {
    MaterialTheme {
//        var showContent by remember { mutableStateOf(false) }
//        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
//            Button(onClick = { showContent = !showContent }) {
//                Text("Click me!")
//            }
//            AnimatedVisibility(showContent) {
//                val greeting = remember { Greeting().greet() }
//                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
//                    Image(painterResource(Res.drawable.compose_multiplatform), null)
//                    Text("Compose: $greeting")
//                }
//            }
//        }
        HomeRoute(modifier = Modifier.fillMaxSize())
    }
}

@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
) {
    val homeViewModel: HomeViewModel = rememberViewModel()
    val uiState by homeViewModel.homeState.collectAsState()

    HomeScreen(modifier, uiState, onSearch = { homeViewModel.search(it) })
}

@Composable
private fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeViewState,
    onSearch: (query: String) -> Unit,
) {
    var text by remember { mutableStateOf("") }
    Column(modifier) {
        TextField(
            value = text,
            onValueChange = { text = it },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { onSearch(text) }),
        )

        Text(
            modifier = Modifier.padding(top = 20.dp),
            text = uiState.toString(),
        )

        Spacer(modifier = Modifier.weight(1f))

        TextButton(onClick = { onSearch(text) }) {
            Text("Search")
        }
    }
}
