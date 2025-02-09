package kz.shprot.customsliders.util

internal fun Float.roundIfWhole(): String =
    when (this % 1.0f == 0f) {
        true -> String.format("%d", this.toInt())
        false -> String.format("%.2f", this)
    }