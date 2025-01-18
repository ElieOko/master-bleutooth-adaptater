package com.master.masteradaptaterbleutooth.intro.presentation

import android.content.res.Configuration
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.graphics.shapes.CornerRounding
import androidx.graphics.shapes.Morph
import androidx.graphics.shapes.RoundedPolygon
import androidx.graphics.shapes.toPath
import androidx.navigation.NavHostController
import com.master.masteradaptaterbleutooth.R
import com.master.masteradaptaterbleutooth.core.components.button.MButtonStructure
import com.master.masteradaptaterbleutooth.core.routes.ScreenRoute
import com.master.masteradaptaterbleutooth.core.ui.theme.Green100

//https://developer.android.com/develop/ui/compose/graphics/draw/shapes?hl=fr
@Composable
fun IntroScreen(navC: NavHostController) {
    val infiniteAnimation = rememberInfiniteTransition(label = "infinite animation")
    val morphProgress = infiniteAnimation.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            tween(500),
            repeatMode = RepeatMode.Reverse
        ),
        label = "morph"
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
    ) {

        Spacer(modifier = Modifier.height(35.dp))

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "BLE",
                fontSize = 24.sp,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                color = Green100
            )
        }
            Spacer(modifier = Modifier.height(15.dp))
            ConstraintLayout(Modifier.fillMaxWidth()) {
                val guideline = createGuidelineFromStart(-0.1f)
                val (row1, row2, row3) = createRefs()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .drawWithCache {
                            val triangle = RoundedPolygon(
                                numVertices = 3,
                                radius = size.minDimension / 2f,
                                centerX = size.width / 2f,
                                centerY = size.height / 2f,
                                rounding = CornerRounding(
                                    size.minDimension / 10f,
                                    smoothing = 0.1f
                                )
                            )
                            val square = RoundedPolygon(
                                numVertices = 4,
                                radius = size.minDimension / 2f,
                                centerX = size.width / 2f,
                                centerY = size.height / 2f
                            )

                            val morph = Morph(start = triangle, end = square)
                            val morphPath = morph
                                .toPath(progress = morphProgress.value)
                                .asComposePath()

                            onDrawBehind {
                                drawPath(morphPath, color = Color.White)
                            }
                        }
                        .constrainAs(row1) {
                            top.linkTo(parent.top, margin = 6.dp)
                        },
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.android),
                        contentDescription = ""
                    )
                }
                Row(Modifier.constrainAs(row2) {
                    top.linkTo(parent.top, margin = 146.dp)
                }) {
                    Image(painter = painterResource(id = R.drawable.data), contentDescription = "")
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .constrainAs(row3) {
                            top.linkTo(parent.top, margin = 150.dp)
                            start.linkTo(guideline)
                        }, horizontalArrangement = Arrangement.End) {
                    Image(painter = painterResource(id = R.drawable.eye), contentDescription = "")
                }
            }
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            MButtonStructure(
                contentDesc = "Se connecter",
                backgroundColor = Green100,
                modifier = Modifier.width(274.dp),
                click = {
                    navC.navigate(route = ScreenRoute.Login.name)
                }
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = "TO", color = Color.White)
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = "CREER COMPTE MUSUSU ?", color = Color.White, modifier = Modifier.clickable {
                navC.navigate(route = ScreenRoute.Register.name)
            })
        }
    }
}
//@OptIn(InternalCoroutinesApi::class)
//private fun decoupledConstraints(margin: Dp): ConstraintSet {
//    return ConstraintSet {
//        val button = createRefFor("button")
//        val text = createRefFor("text")
//
//        constrain(button) {
//            top.linkTo(parent.top, margin = margin)
//        }
//        constrain(text) {
//            top.linkTo(button.bottom, margin)
//        }
//    }
//}