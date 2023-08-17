package ramble.sokol.sberafisha.routes.view.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
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
import ramble.sokol.sberafisha.destinations.AfterTestScreenDestination
import ramble.sokol.sberafisha.destinations.BeforeTestScreenDestination
import ramble.sokol.sberafisha.destinations.BottomMenuScreenDestination
import ramble.sokol.sberafisha.destinations.EntryScreenDestination
import ramble.sokol.sberafisha.profile.view.components.ButtonForEntryProfile
import ramble.sokol.sberafisha.ui.theme.BackColumn
import ramble.sokol.sberafisha.ui.theme.ColorTextField
import ramble.sokol.sberafisha.ui.theme.White

@Composable
@Destination
fun MapAfterTestScreen(
    navigator: DestinationsNavigator
){
    Box(
        modifier = Modifier
            .fillMaxSize()
    ){

        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(id = R.drawable.image_mao_after_test),
            contentDescription = "map",
            contentScale = ContentScale.Crop
        )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = White,
                        shape = RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp)
                    )
                    .padding(bottom = 12.dp, top = 12.dp)
            ) {
                Box(
                    modifier = Modifier.padding(start = 27.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Image(
                        modifier = Modifier
                            .padding(1.dp)
                            .width(28.dp)
                            .height(28.dp)
                            .clickable {
                                navigator.popBackStack()
                                navigator.navigate(BeforeTestScreenDestination)
                            },
                        painter = painterResource(id = R.drawable.image_button_back),
                        contentDescription = "back before start"
                    )
                }
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.text_route),
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontFamily = FontFamily(Font(R.font.mont_bold)),
                            fontWeight = FontWeight(800),
                            color = ColorTextField,
                            textAlign = TextAlign.Center,
                            letterSpacing = 0.2.sp,
                        )
                    )
                }
            }

        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.BottomCenter){

            Box(
                Modifier.background(
                    color = White,
                    shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
            .padding(vertical = 18.dp, horizontal = 32.dp)){
                ButtonForEntryProfile(text = stringResource(id = R.string.text_save_route)) {
                    navigator.popBackStack()
                    navigator.navigate(AfterTestScreenDestination)
                }
        }

        }

        }

    BackHandler {
        navigator.popBackStack()
        navigator.navigate(BeforeTestScreenDestination)
    }

    }