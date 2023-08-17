package ramble.sokol.sberafisha.start_test.view.screeens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import ramble.sokol.sberafisha.destinations.BottomMenuScreenDestination
import ramble.sokol.sberafisha.destinations.EntryScreenDestination
import ramble.sokol.sberafisha.destinations.RouteScreenDestination
import ramble.sokol.sberafisha.destinations.TestRouterScreenDestination
import ramble.sokol.sberafisha.profile.view.components.ButtonForEntryProfile
import ramble.sokol.sberafisha.routes.view.components.ButtonWithoutSurvey
import ramble.sokol.sberafisha.start_test.view.components.ButtonForAdToApp
import ramble.sokol.sberafisha.ui.theme.BackColumn
import ramble.sokol.sberafisha.ui.theme.ColorTextField
import ramble.sokol.sberafisha.ui.theme.ColorTextHint
import ramble.sokol.sberafisha.ui.theme.TextTitle
import ramble.sokol.sberafisha.ui.theme.White

@Composable
@Destination
fun WarningAuthScreen(
    navigator: DestinationsNavigator
){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackColumn)
            .padding(bottom = 38.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = White,  shape = RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp))
                .padding(bottom = 12.dp, top = 12.dp)
        ){
            Box(
                modifier = Modifier.padding(start = 27.dp),
                contentAlignment = Alignment.CenterStart){
                Image(
                    modifier = Modifier
                        .padding(1.dp)
                        .width(28.dp)
                        .height(28.dp)
                        .clickable {
                            navigator.popBackStack()
                            navigator.navigate(BottomMenuScreenDestination)
                        },
                    painter = painterResource(id = R.drawable.image_button_back),
                    contentDescription = "back before start"
                )
            }
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center){
                Text(
                    text = stringResource(id = R.string.text_warning),
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

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.image_warning),
                contentDescription = "image before test"
            )

            Spacer(modifier = Modifier.padding(top = 13.dp))

            Text(
                text = stringResource(id = R.string.text_warning_title),
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.mont_bold)),
                    fontWeight = FontWeight(800),
                    color = TextTitle,
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.24.sp,
                )
            )

            Spacer(modifier = Modifier.padding(top = 8.dp))

            Text(
                text = stringResource(id = R.string.text_warning_hint),
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 20.5.sp,
                    fontFamily = FontFamily(Font(R.font.mont_semibold)),
                    fontWeight = FontWeight(700),
                    color = ColorTextHint,
                    textAlign = TextAlign.Center,
                )
            )

        }

        Column (modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)){

            ButtonForEntryProfile(text = stringResource(id = R.string.text_button_warning)) {
                navigator.navigate(EntryScreenDestination)
            }

        }

    }

    BackHandler {
        navigator.popBackStack()
        navigator.navigate(BottomMenuScreenDestination)
    }

}