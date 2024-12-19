package com.klyxdevs.rickmortyapp.ui.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import app.cash.paging.compose.LazyPagingItems


enum class PagingType {
    ROW,
    COLUMN,
    VERTICAL_GRID
}

@Composable
fun <T : Any> PagingWrapper(
    pagingType: PagingType,
    pagingItems: LazyPagingItems<T>,
    initialView: @Composable () -> Unit = {},
    emptyView: @Composable () -> Unit = {},
    extraItemsView: @Composable () -> Unit = {},
    itemView: @Composable (T) -> Unit
) {

    when {
        pagingItems.loadState.refresh is LoadState.Loading && pagingItems.itemCount == 0 -> {
            initialView()
        }

        pagingItems.loadState.refresh is LoadState.NotLoading && pagingItems.itemCount == 0 -> {
            emptyView()
        }

        else -> {

            when (pagingType) {
                PagingType.ROW -> {
                    LazyRowTarget(pagingItems, itemView = itemView)
                }

                PagingType.COLUMN -> {
                    LazyColumn {
                        items(pagingItems.itemCount) { pos ->
                            pagingItems[pos]?.let { item ->
                                itemView(item)
                            }
                        }
                    }
                }

                PagingType.VERTICAL_GRID -> {
                    LazyVerticalTarget(pagingItems, itemView = itemView)
                }
            }

            if (pagingItems.loadState.append is LoadState.Loading) {
                extraItemsView()
            }

        }
    }

}