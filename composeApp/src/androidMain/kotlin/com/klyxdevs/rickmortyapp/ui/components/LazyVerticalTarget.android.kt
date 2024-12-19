package com.klyxdevs.rickmortyapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.klyxdevs.rickmortyapp.ui.core.colors.BackgroundPrimaryColor

@Composable
actual fun <T : Any> LazyVerticalTarget(
    pagingItems: LazyPagingItems<T>,
    itemView: @Composable (T) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxSize().background(BackgroundPrimaryColor)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(pagingItems.itemCount) { pos ->
            pagingItems[pos]?.let { item ->
                itemView(item)
            }
        }
    }
}