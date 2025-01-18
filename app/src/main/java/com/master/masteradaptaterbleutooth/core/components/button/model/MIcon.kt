package com.example.jc_component.component.button.model

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector

data class MIcon(
    val iconVector: ImageVector? = null,
    val iconImage : ImageBitmap? = null,
    val iconPainter: Painter? = null
)
