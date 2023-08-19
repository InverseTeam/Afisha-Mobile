package ramble.sokol.sberafisha.routes.view.screens

import android.util.Log
import android.view.MotionEvent
import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalContext
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
import ramble.sokol.sberafisha.R
import ramble.sokol.sberafisha.destinations.BeforeTestScreenDestination
import ramble.sokol.sberafisha.destinations.WarningAuthScreenDestination
import ramble.sokol.sberafisha.model_project.FirstEntryManager
import ramble.sokol.sberafisha.routes.view.components.ButtonSearchAfisha
import ramble.sokol.sberafisha.ui.theme.TextTitle

private lateinit var firstEntryManager: FirstEntryManager

@OptIn(ExperimentalComposeUiApi::class)
@Destination
@Composable
fun RouteScreen(
    navigator: DestinationsNavigator
){

    val context = LocalContext.current

    firstEntryManager = FirstEntryManager(context)

    val selected1 = remember { mutableStateOf(false) }
    val scale1 = animateFloatAsState(if (selected1.value) 0.85f else 1f, label = "")

    val selected2 = remember { mutableStateOf(false) }
    val scale2 = animateFloatAsState(if (selected2.value) 0.85f else 1f, label = "")

    val selected3 = remember { mutableStateOf(false) }
    val scale3 = animateFloatAsState(if (selected1.value) 0.85f else 1f, label = "")

    val selected4 = remember { mutableStateOf(false) }
    val scale4 = animateFloatAsState(if (selected2.value) 0.85f else 1f, label = "")

    val selected5 = remember { mutableStateOf(false) }
    val scale5 = animateFloatAsState(if (selected2.value) 0.85f else 1f, label = "")

    val selected6 = remember { mutableStateOf(false) }
    val scale6 = animateFloatAsState(if (selected2.value) 0.85f else 1f, label = "")

    val selected7 = remember { mutableStateOf(false) }
    val scale7 = animateFloatAsState(if (selected2.value) 0.85f else 1f, label = "")

    val selected8 = remember { mutableStateOf(false) }
    val scale8 = animateFloatAsState(if (selected2.value) 0.85f else 1f, label = "")

    Column(
        modifier = Modifier
            .padding(top = 16.dp, start = 32.dp, end = 32.dp, bottom = 60.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth(),
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

            Spacer(modifier = Modifier.padding(top = 16.dp))
        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())) {


            Spacer(modifier = Modifier.padding(top = 8.dp))

            Image(
                modifier = Modifier
                    .scale(scale3.value)
                    .fillMaxWidth()
                    .pointerInteropFilter {
                        when (it.action) {
                            MotionEvent.ACTION_DOWN -> {
                                selected3.value = true
                            }

                            MotionEvent.ACTION_UP -> {
                                selected3.value = false
                                Toast
                                    .makeText(
                                        context,
                                        "Перейдите в другое приложение",
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()

                            }
                        }
                        true
                    },
                painter = painterResource(id = R.drawable.button_ekb),
                contentDescription = "generate button"
            )

            Image(
                modifier = Modifier
                    .scale(scale1.value)
                    .fillMaxWidth()
                    .pointerInteropFilter {
                        when (it.action) {
                            MotionEvent.ACTION_DOWN -> {
                                selected1.value = true
                            }

                            MotionEvent.ACTION_UP -> {
                                selected1.value = false
                                Log.d("MyLog", "click")
                                if (firstEntryManager.getFirstTest() == true) {
                                    navigator.popBackStack()
                                    navigator.navigate(BeforeTestScreenDestination)
                                } else {
                                    navigator.popBackStack()
                                    navigator.navigate(WarningAuthScreenDestination)
                                }

                            }
                        }
                        true
                    },
                painter = painterResource(id = R.drawable.button_generate_route),
                contentDescription = "generate button"
            )

            Spacer(modifier = Modifier.padding(top = 8.dp))

            Image(
                modifier = Modifier
                    .scale(scale2.value)
                    .fillMaxWidth()
                    .pointerInteropFilter {
                        when (it.action) {
                            MotionEvent.ACTION_DOWN -> {
                                selected2.value = true
                            }

                            MotionEvent.ACTION_UP -> {
                                selected2.value = false
                                if (firstEntryManager.getFirstTest() == true) {
                                    Toast
                                        .makeText(
                                            context,
                                            R.string.text_into_developing,
                                            Toast.LENGTH_SHORT
                                        )
                                        .show()
                                } else {
                                    navigator.popBackStack()
                                    navigator.navigate(WarningAuthScreenDestination)
                                }
                            }
                        }
                        true
                    },
                painter = painterResource(id = R.drawable.button_build_route),
                contentDescription = "generate button"
            )

            Spacer(modifier = Modifier.padding(top = 32.dp))

            Text(
                text = stringResource(id = R.string.text_sport_holiday),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.mont_bold)),
                    fontWeight = FontWeight(800),
                    color = TextTitle,
                    letterSpacing = 0.18.sp,
                )
            )

            Spacer(modifier = Modifier.padding(top = 16.dp))

            Image(
                modifier = Modifier
                    .scale(scale4.value)
                    .fillMaxWidth()
                    .pointerInteropFilter {
                        when (it.action) {
                            MotionEvent.ACTION_DOWN -> {
                                selected4.value = true
                            }

                            MotionEvent.ACTION_UP -> {
                                selected4.value = false
                                Toast
                                    .makeText(
                                        context,
                                        R.string.text_into_developing,
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()


                            }
                        }
                        true
                    },
                painter = painterResource(id = R.drawable.image_sport_1),
                contentDescription = "generate button"
            )

            Spacer(modifier = Modifier.padding(top = 8.dp))

            Image(
                modifier = Modifier
                    .scale(scale5.value)
                    .fillMaxWidth()
                    .pointerInteropFilter {
                        when (it.action) {
                            MotionEvent.ACTION_DOWN -> {
                                selected5.value = true
                            }

                            MotionEvent.ACTION_UP -> {
                                selected5.value = false
                                Toast
                                    .makeText(
                                        context,
                                        R.string.text_into_developing,
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()


                            }
                        }
                        true
                    },
                painter = painterResource(id = R.drawable.image_sport_2),
                contentDescription = "generate button"
            )

            Spacer(modifier = Modifier.padding(top = 8.dp))

            Image(
                modifier = Modifier
                    .scale(scale6.value)
                    .fillMaxWidth()
                    .pointerInteropFilter {
                        when (it.action) {
                            MotionEvent.ACTION_DOWN -> {
                                selected6.value = true
                            }

                            MotionEvent.ACTION_UP -> {
                                selected6.value = false
                                Toast
                                    .makeText(
                                        context,
                                        R.string.text_into_developing,
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()


                            }
                        }
                        true
                    },
                painter = painterResource(id = R.drawable.image_sport_3),
                contentDescription = "generate button"
            )

            Spacer(modifier = Modifier.padding(top = 32.dp))

            Text(
                text = stringResource(id = R.string.text_street_art),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.mont_bold)),
                    fontWeight = FontWeight(800),
                    color = TextTitle,
                    letterSpacing = 0.18.sp,
                )
            )

            Spacer(modifier = Modifier.padding(top = 16.dp))

            Image(
                modifier = Modifier
                    .scale(scale7.value)
                    .fillMaxWidth()
                    .pointerInteropFilter {
                        when (it.action) {
                            MotionEvent.ACTION_DOWN -> {
                                selected7.value = true
                            }

                            MotionEvent.ACTION_UP -> {
                                selected7.value = false
                                Toast
                                    .makeText(
                                        context,
                                        R.string.text_into_developing,
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()


                            }
                        }
                        true
                    },
                painter = painterResource(id = R.drawable.street_1),
                contentDescription = "generate button"
            )

            Spacer(modifier = Modifier.padding(top = 8.dp))

            Image(
                modifier = Modifier
                    .scale(scale8.value)
                    .fillMaxWidth()
                    .pointerInteropFilter {
                        when (it.action) {
                            MotionEvent.ACTION_DOWN -> {
                                selected8.value = true
                            }

                            MotionEvent.ACTION_UP -> {
                                selected8.value = false
                                Toast
                                    .makeText(
                                        context,
                                        R.string.text_into_developing,
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()


                            }
                        }
                        true
                    },
                painter = painterResource(id = R.drawable.street_2),
                contentDescription = "generate button"
            )

        }

    }
}