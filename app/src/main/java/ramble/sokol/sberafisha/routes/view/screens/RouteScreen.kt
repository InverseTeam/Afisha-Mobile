package ramble.sokol.sberafisha.routes.view.screens

import android.util.Log
import android.view.MotionEvent
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch
import ramble.sokol.sberafisha.R
import ramble.sokol.sberafisha.destinations.TestRouterScreenDestination
import ramble.sokol.sberafisha.routes.view.components.ButtonSearchAfisha
import ramble.sokol.sberafisha.ui.theme.TextTitle
import ramble.sokol.sberafisha.ui.theme.White

@OptIn(ExperimentalComposeUiApi::class)
@Destination
@Composable
fun RouteScreen(
    navigator: DestinationsNavigator
){

    val selected = remember { mutableStateOf(false) }
    val scale = animateFloatAsState(if (selected.value) 0.85f else 1f, label = "")


    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(top = 16.dp, start = 32.dp, end = 32.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.text_router),
            style = TextStyle(
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.mont_bold)),
                fontWeight = FontWeight(800),
                color = TextTitle,
                letterSpacing = 0.24.sp,
                textAlign = TextAlign.Left
            )
        )

        Spacer(modifier = Modifier.padding(top = 16.dp))

        ButtonSearchAfisha(
            text = stringResource(id = R.string.text_search)
        ) {
            // click
        }

        Spacer(modifier = Modifier.padding(top = 41.dp))

        Box(
            modifier = Modifier
                .scale(scale.value)
                .fillMaxWidth()
                .pointerInteropFilter {
                    when (it.action) {
                        MotionEvent.ACTION_DOWN -> {
                            selected.value = true }

                        MotionEvent.ACTION_UP  -> {
                            selected.value = false
                            Log.d("MyLog", "click")
                            navigator.navigate(TestRouterScreenDestination)}
                    }
                    true
                },
            contentAlignment = Alignment.Center
            ){
            Image(
                painter = painterResource(id = R.drawable.round_button_route),
                contentDescription = "big button route")

            Text(
                text = stringResource(id = R.string.text_create_route),
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.mont_semibold)),
                    fontWeight = FontWeight(700),
                    color = White,
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.24.sp,
                ),
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.padding(top = 48.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.text_your_routes),
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.mont_bold)),
                fontWeight = FontWeight(800),
                color = TextTitle,
                letterSpacing = 0.18.sp,
                textAlign = TextAlign.Left
            )
        )

    }
}