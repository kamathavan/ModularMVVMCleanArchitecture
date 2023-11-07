package com.app.feature.coffee.ui.coffeelist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.app.feature.coffee.ui.theme.Brown

@Composable
fun IngredientChip(
    text: String,
) {
    Surface(
        color = Color.Transparent,
        contentColor = Color.DarkGray,
        shape = CircleShape,
        border = BorderStroke(
            width = 1.dp,
            color = Color.LightGray
        )
    ) {
        Text(
            text = text,
            textAlign = TextAlign.Center,
            color = Brown,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(14.dp)
        )
    }
}