package ramble.sokol.sberafisha.profile.view.screens

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination
import ramble.sokol.sberafisha.R
import ramble.sokol.sberafisha.authentication_and_splash.view.components.TextInputEmailEntry
import ramble.sokol.sberafisha.profile.view.components.ButtonChangeProfile
import ramble.sokol.sberafisha.profile.view.components.DropDownLanguageProfile
import ramble.sokol.sberafisha.profile.view.components.TextInputAgeProfile
import ramble.sokol.sberafisha.profile.view.components.TextInputNameProfile
import ramble.sokol.sberafisha.profile.view.components.TextInputSurnameProfile
import ramble.sokol.sberafisha.ui.theme.TextTitle

@Destination
@Composable
fun ProfileScreen(){

    var name by remember {
        mutableStateOf("Иван")
    }

    var surname by remember {
        mutableStateOf("Иванов")
    }

    var age by remember {
        mutableStateOf("18")
    }

    var language by remember {
        mutableStateOf("Русский")
    }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(top = 16.dp, start = 32.dp, end = 32.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.text_profile),
            style = TextStyle(
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.mont_bold)),
                fontWeight = FontWeight(800),
                color = TextTitle,
                letterSpacing = 0.24.sp,
                textAlign = TextAlign.Left
            )
        )

        Spacer(modifier = Modifier.padding(top = 24.dp))

        TextInputNameProfile(
            text = name,
            onValueChange = {
                name = it
            },
            interactionSource = remember { MutableInteractionSource() }
                .also { interactionSource ->
                    LaunchedEffect(interactionSource) {
                        interactionSource.interactions.collect {
                            if (it is PressInteraction.Release) {
                                // click on textfield
                            }
                        }
                    }
                }
        )

        Spacer(modifier = Modifier.padding(top = 8.dp))

        TextInputSurnameProfile(
            text = surname,
            onValueChange = {
                surname = it
            },
            interactionSource = remember {
                MutableInteractionSource()
            }
                .also { interactionSource ->
                    LaunchedEffect(interactionSource) {
                        interactionSource.interactions.collect {
                            if (it is PressInteraction.Release) {
                                // click on textfield
                            }
                        }
                    }
                }
        )

        Spacer(modifier = Modifier.padding(top = 8.dp))

        TextInputAgeProfile(
            text = age,
            onValueChange = {
                age = it
            },
            interactionSource = remember {
                MutableInteractionSource()
            }
                .also { interactionSource ->
                    LaunchedEffect(interactionSource) {
                        interactionSource.interactions.collect {
                            if (it is PressInteraction.Release) {
                                // click on textfield
                            }
                        }
                    }
                }
        )

        Spacer(modifier = Modifier.padding(top = 8.dp))

        DropDownLanguageProfile()

        Spacer(modifier = Modifier.padding(top = 8.dp))

        ButtonChangeProfile(
            text = stringResource(id = R.string.text_change)
        ) {

        }

    }
}