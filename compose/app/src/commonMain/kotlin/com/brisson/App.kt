package com.brisson

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.brisson.theme.QuiuiTheme
import com.brisson.viewmodel.HomeViewModel
import com.brisson.viewmodel.HomeViewState
import io.kamel.core.Resource
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun App() {
    QuiuiTheme {
        Surface(color = MaterialTheme.colors.background) {
            HomeRoute(modifier = Modifier.fillMaxSize())
        }
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
    LazyColumn(modifier) {
        item {
            Row {
                TextField(
                    value = text,
                    onValueChange = { text = it },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(onSearch = { onSearch(text) }),
                )

                TextButton(onClick = { onSearch(text) }) {
                    Text("Search")
                }
            }
        }

        when (uiState) {
            is HomeViewState.Initial -> {}
            is HomeViewState.Loading -> {}
            is HomeViewState.Error -> {}
            is HomeViewState.Content -> {
                items(uiState.sections.toList()) { (title, metas) ->
                    Text(
                        modifier = Modifier.padding(bottom = 12.dp, top = 24.dp, start = 16.dp),
                        text = title,
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp
                    )

                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                    ) {
                        items(metas) { meta ->
                            (meta.poster ?: meta.background)?.let { imageUrl ->
                                val painterResource: Resource<Painter> = asyncPainterResource(imageUrl)
                                KamelImage(
                                    modifier = Modifier.size(width = 138.dp, height = 202.dp),
                                    resource = painterResource,
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    onLoading = { progress -> CircularProgressIndicator(progress) },
                                )
                            }
                        }
                    }
                }
            }
        }

    }
}

@Preview
@Composable
private fun PreviewHomeScreen() {
    val uiState: HomeViewState = HomeViewState.Initial
    HomeScreen(modifier = Modifier.fillMaxSize(), uiState = uiState, onSearch = { })
}
