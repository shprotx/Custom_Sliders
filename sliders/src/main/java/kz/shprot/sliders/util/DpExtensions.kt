package kz.shprot.sliders.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp

@Composable
internal fun Dp.toPx() = with(LocalDensity.current) {
    this@toPx.toPx()
}

@Composable
internal fun Float.toDp() = with(LocalDensity.current) {
    this@toDp.toDp()
}