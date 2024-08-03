package com.milwen.blueprint.ui.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen(
    title: String,
    modifier: Modifier = Modifier,
    navigation: AppBarNavigation = AppBarNavigation.None,
    content: @Composable BoxScope.() -> Unit,
) {
    Box {
        Scaffold(
            topBar = {
                MainAppBar(
                    title = title,
                    navigation = navigation,
                )
            },
            contentWindowInsets = WindowInsets(0.dp),
            modifier = modifier,
        ) { padding ->
            Box(
                modifier = Modifier.padding(padding)
            ) {
                content()
            }
        }
    }

}

@Preview(name = "PreviewMainScreen", group = "Main UI")
@Composable
fun PreviewMainScreen() {
    ThemedPreview {
        MainScreen(
            title = "PreviewMainScreen",
            navigation = AppBarNavigation.Button.Up {},
            content = {},
        )
    }
}