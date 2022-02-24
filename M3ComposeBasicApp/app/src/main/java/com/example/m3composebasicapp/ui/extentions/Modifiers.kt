package com.example.m3composebasicapp.ui.extentions

import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

internal fun Modifier.mainContentPadding(paddingValues: PaddingValues): Modifier {
    return fillMaxSize()
        .padding(
            PaddingValues(
                paddingValues.calculateStartPadding(LayoutDirection.Ltr) + 12.dp,
                paddingValues.calculateTopPadding() + 12.dp,
                paddingValues.calculateEndPadding(LayoutDirection.Ltr) + 12.dp,
                paddingValues.calculateBottomPadding() + 12.dp
            )
        )
}