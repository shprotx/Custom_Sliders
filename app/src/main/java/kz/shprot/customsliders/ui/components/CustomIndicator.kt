package kz.shprot.customsliders.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import kz.shprot.customsliders.ui.theme.Dimensions

@Composable
internal fun CustomIndicator(
    value: String,
) {

    Text(
        modifier = Modifier
            .padding(bottom = Dimensions.paddingExtraSmall)
            .clip(RoundedCornerShape(Dimensions.cornerRadiusSmall))
            .background(Color.White)
            .padding(Dimensions.paddingSmall),
        text = value,
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.background,
    )
}