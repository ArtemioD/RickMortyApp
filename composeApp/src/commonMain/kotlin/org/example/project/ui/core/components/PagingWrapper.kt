package org.example.project.ui.core.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridCells.*
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import app.cash.paging.compose.LazyPagingItems
import org.example.project.ui.core.components.PagingType.*


enum class PagingType {
    ROW,
    COLUMN,
    VERTICAL_GRID,
    HORIZONTAL_GRID
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
            // carga inicial
            initialView()
        }

        pagingItems.loadState.refresh is LoadState.NotLoading && pagingItems.itemCount == 0 -> {
            // vacio
            emptyView()
        }

        else -> {

            when (pagingType) {
                ROW -> {
                    LazyRow {
                        items(pagingItems.itemCount) { pos ->
                            pagingItems[pos]?.let { item ->
                                itemView(item)
                            }
                        }
                    }
                }

                COLUMN -> {
                    LazyColumn {
                        items(pagingItems.itemCount) { pos ->
                            pagingItems[pos]?.let { item ->
                                itemView(item)
                            }
                        }
                    }
                }

                VERTICAL_GRID -> {
                    LazyVerticalGrid(columns = Fixed(2)) {
                        items(pagingItems.itemCount) { pos ->
                            pagingItems[pos]?.let { item ->
                                itemView(item)
                            }
                        }
                    }
                }

                HORIZONTAL_GRID -> {
                    LazyHorizontalGrid(rows = Fixed(2)) {
                        items(pagingItems.itemCount) { pos ->
                            pagingItems[pos]?.let { item ->
                                itemView(item)
                            }
                        }
                    }
                }
            }

            if (pagingItems.loadState.append is LoadState.Loading) {
                extraItemsView()
            }
        }
    }
}