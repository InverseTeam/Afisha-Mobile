package ramble.sokol.sberafisha.authentication_and_splash.view.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import ramble.sokol.sberafisha.authentication_and_splash.view.components.ButtonForEntry
import ramble.sokol.sberafisha.authentication_and_splash.view.components.ButtonForEntrySber
import ramble.sokol.sberafisha.authentication_and_splash.view.components.ButtonForEntryToRegistration
import ramble.sokol.sberafisha.authentication_and_splash.view.components.TextInputEmailEntry
import ramble.sokol.sberafisha.authentication_and_splash.view.components.TextInputPasswordConfirmationEntry
import ramble.sokol.sberafisha.authentication_and_splash.view.components.TextInputPasswordEntry
import ramble.sokol.sberafisha.destinations.BottomMenuScreenDestination
import ramble.sokol.sberafisha.destinations.EntryScreenDestination
import ramble.sokol.sberafisha.ui.theme.ColorActionText
import ramble.sokol.sberafisha.ui.theme.ColorText

@Destination
@Composable
fun RegistrationScreen(
    navigator: DestinationsNavigator
) {
    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var passwordConfirmation by remember {
        mutableStateOf("")
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .width(167.dp)
                .height(24.dp),
            painter = painterResource(id = R.drawable.image_text_auth),
            contentDescription = "image_text_for_auth"
        )

        Spacer(modifier = Modifier.padding(top = 16.dp))

        TextInputEmailEntry(
            text = email,
            onValueChange = {
                email = it
            }
        )

        Spacer(modifier = Modifier.padding(top = 8.dp))

        TextInputPasswordEntry(
            text = password,
            onValueChange = {
                password = it
            }
        )

        Spacer(modifier = Modifier.padding(top = 8.dp))

        TextInputPasswordConfirmationEntry(
            text = passwordConfirmation,
            onValueChange = {
                passwordConfirmation = it
            }
        )

        Spacer(modifier = Modifier.padding(top = 8.dp))

        ButtonForEntry(text = stringResource(id = R.string.text_register)){
            navigator.navigate(BottomMenuScreenDestination)
        }

        Spacer(modifier = Modifier.padding(top = 153.dp))

        ButtonForEntryToRegistration(text = stringResource(id = R.string.text_entrance)) {
            navigator.navigate(EntryScreenDestination)
        }

        Spacer(modifier = Modifier.padding(top = 25.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = stringResource(id = R.string.text_start_agreement),
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 24.sp,
                    fontFamily = FontFamily(Font(R.font.mont_semibold)),
                    fontWeight = FontWeight(700),
                    color = ColorText,
                    textAlign = TextAlign.Center
                )
            )

            Spacer(modifier = Modifier.padding(2.dp))

            Text(
                modifier = Modifier.clickable {
                    // click
                },
                text = stringResource(id = R.string.text_end_agreement),
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 24.sp,
                    fontFamily = FontFamily(Font(R.font.mont_semibold)),
                    fontWeight = FontWeight(700),
                    color = ColorActionText,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}