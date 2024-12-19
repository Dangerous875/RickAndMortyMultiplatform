package com.klyxdevs.rickmortyapp.ui.components

import androidx.compose.runtime.Composable
import app.cash.paging.compose.LazyPagingItems

@Composable
expect fun <T : Any> LazyVerticalTarget(
    pagingItems: LazyPagingItems<T>,
    itemView: @Composable (T) -> Unit
)