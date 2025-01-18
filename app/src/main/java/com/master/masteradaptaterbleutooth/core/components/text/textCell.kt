package com.example.jc_component.component.text

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun TextCell(text : String, modifier: Modifier = Modifier){
    val cellModifier =  Modifier
        .padding(4.dp)
        .size(100.dp,100.dp)
        .border(width = 4.dp, color = Color.Black)
    Text(
        text,
        modifier = cellModifier.then(modifier),
        fontSize = 70.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )

}