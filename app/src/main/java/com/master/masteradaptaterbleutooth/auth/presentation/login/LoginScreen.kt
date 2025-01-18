package com.master.masteradaptaterbleutooth.auth.presentation.login

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.master.masteradaptaterbleutooth.core.components.button.MButtonStructure
import com.master.masteradaptaterbleutooth.core.components.topbar.SimpleTopBar
import com.master.masteradaptaterbleutooth.core.routes.ScreenRoute
import com.master.masteradaptaterbleutooth.core.ui.theme.Green100

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginScreen(navC: NavHostController) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            SimpleTopBar(
                containerColor = Color.Black,
                colorIcon = Color.White,
                iconStart = Icons.Filled.Close,
                titleMain = "Connexion",
                paddingSurface = 0,
                clickStartAction = {
                    navC.navigate(route = ScreenRoute.Intro.name)
                }
            )
        }
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(8.dp)
        ) {
            Spacer(modifier = Modifier.height(85.dp))
            Text(modifier = Modifier.fillMaxWidth(),text = "Se Connecter Rapidement ", fontSize = 24.sp, overflow = TextOverflow.Ellipsis, fontWeight = FontWeight.Bold, color = Green100, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(15.dp))
            Text(modifier = Modifier.fillMaxWidth(),text = "Retrouvez votre compte en mettant vos identifiant pour continuer l'aventure sur BLE !!", fontSize = 17.sp, color = Color.White,textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(25.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().padding(18.dp),
                value = "",
                onValueChange = {},
                placeholder = { Text("Username") } ,
                shape = RoundedCornerShape(topEnd = 12.dp, bottomStart = 12.dp)
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().padding(18.dp),
                value = "",
                onValueChange = {},
                placeholder = { Text("Mot de passe") } ,
                shape = RoundedCornerShape(topEnd = 12.dp, bottomStart = 12.dp)
            )
            Spacer(modifier = Modifier.height(25.dp))
            MButtonStructure(
                contentDesc = "Se connecter",
                backgroundColor = Green100,
                modifier = Modifier.fillMaxWidth().padding(18.dp),
                click = {
                    Toast.makeText(context,"Action login", Toast.LENGTH_LONG).show()
                }
            )
        }
    }
}