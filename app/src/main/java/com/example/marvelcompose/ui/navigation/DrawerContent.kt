package com.example.marvelcompose.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp

@Composable
fun DrawerContent(drawerOptions: List<NavItem>, onOptionClick: (NavItem) -> Unit) {
    var selectedItem by remember { mutableStateOf(drawerOptions[0]) }
    ModalDrawerSheet {
        Box(
            modifier = Modifier
                .background(
                    Brush.verticalGradient(
                        listOf(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.secondary
                        )
                    )
                )
                .height(200.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        drawerOptions.forEach { navItem ->
            NavigationDrawerItem(
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = navItem.name
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = navItem.title)
                    )
                },
                selected = selectedItem == navItem,
                onClick = {
                    selectedItem = navItem
                    onOptionClick(navItem)
                }
            )
        }
    }
}