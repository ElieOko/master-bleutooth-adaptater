package com.example.jc_component.component.input

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MInputTextField(){
    MInputTextFieldBody()
}

@Composable
fun MInputTextFieldBody(){
    OutlinedTextField(
        value = "",
        onValueChange = {},
        placeholder = { Text("Username") } ,
        shape = RoundedCornerShape(topEnd = 12.dp, bottomStart = 12.dp)
        )
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun MInputTextFieldPreview(){
    Column(modifier = Modifier.fillMaxHeight()
        .padding(10.dp)
    ) {
        MInputTextFieldBody()
    }

}

@Composable
fun MInputTextFieldBody3(placeholder: String = "",){
    OutlinedTextField(
        value = "",
        onValueChange = {},
        placeholder = { Text(placeholder) } ,
        shape = RoundedCornerShape(topEnd = 12.dp, bottomStart = 12.dp)
    )
}