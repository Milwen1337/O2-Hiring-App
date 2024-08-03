package com.milwen.blueprint.ui.compose

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.milwen.blueprint.core.ui.R

sealed class AppBarNavigation {
    data object None: AppBarNavigation()

    sealed class Button: AppBarNavigation() {
        abstract val icon: Int
        abstract val callback: () -> Unit

        data class Up(override val callback: () -> Unit) : Button() {
            override val icon: Int = R.drawable.icon_back
        }

        data class Close(override val callback: () -> Unit) : Button() {
            override val icon: Int = R.drawable.icon_close
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppBar(
    title: String,
    navigation: AppBarNavigation = AppBarNavigation.None,
    windowInsets: WindowInsets = TopAppBarDefaults.windowInsets,
) {
    TopAppBar(
        navigationIcon = {
            NavigationButton(navigation)
        },
        title = {
          AppBarTitle(text = title)
        },
        windowInsets = windowInsets,
    )
}

@Composable
fun NavigationButton(navigation: AppBarNavigation){
    if (navigation is AppBarNavigation.Button) {
        IconButton(onClick = navigation.callback) {
            Icon(
               painter = painterResource(id = navigation.icon),
                contentDescription = null,
            )
        }
    }
}

@Composable
fun AppBarTitle(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier,
    )
}

@Preview(name = "PreviewAppBar", group = "Main UI")
@Composable
fun PreviewAppBar(){
    ThemedPreview {
        MainAppBar(
            title = "PreviewMainScreen",
            navigation = AppBarNavigation.Button.Up {},
        )
    }
}