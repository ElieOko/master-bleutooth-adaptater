package com.example.jc_component.component.topbar

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jc_component.ui.theme.Green100
import com.example.jc_component.ui.theme.Grey100


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTopBar(
    titleMain : String = "Immobilier",
    shadow : Int = 0,
    iconStart: ImageVector = Icons.Filled.KeyboardArrowLeft,
    iconEnd : ImageVector =  Icons.Filled.CheckCircle,
    isAction : Boolean = false,
    colorIcon : Color = Green100,
    sizeIcon : Int = 33,
    containerColor : Color = Color.White,
    clickStartAction : ()-> Unit = {},
    clickEndAction : ()-> Unit = {},
    paddingSurface : Int = 5
){
    Surface(shadowElevation = shadow.dp,shape = RoundedCornerShape(10.dp), modifier = Modifier.padding(paddingSurface.dp)) {
        CenterAlignedTopAppBar(
            title = { Text(text = titleMain, maxLines = 1, overflow = TextOverflow.Ellipsis, color = Grey100) },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = containerColor,
                titleContentColor = MaterialTheme.colorScheme.primary,),
            navigationIcon = {
                IconButton(onClick = { clickStartAction() }) {
                    Icon(
                        tint = colorIcon,
                        modifier = Modifier.size(sizeIcon.dp),
                        imageVector = iconStart,
                        contentDescription = "Localized description"
                    )
                }
            },
            actions = {
                if (isAction)
                IconButton(onClick = { clickEndAction()}) {
                    Icon(
                        tint = colorIcon,
                        modifier = Modifier.size(sizeIcon.dp),
                        imageVector = iconEnd,
                        contentDescription = "Localized description"
                    )
                }
            }
        )

    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleTopBarBody(){
    Scaffold(
        topBar = {
            SimpleTopBar()
        }
    ) {

    }
}

@Composable
@Preview(showBackground = true)
fun SimpleTopBarPreview(){
    SimpleTopBarBody()
}