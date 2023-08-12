package ramble.sokol.sberafisha.start_test.view.screeens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import ramble.sokol.sberafisha.model_project.FirstEntryManager
import ramble.sokol.sberafisha.model_project.RoleManager
import ramble.sokol.sberafisha.start_test.view.components.BoxTest
import ramble.sokol.sberafisha.start_test.view.components.ButtonFurtherStartTest
import ramble.sokol.sberafisha.ui.theme.BoxBackTest
import ramble.sokol.sberafisha.ui.theme.BoxTextNotClick
import ramble.sokol.sberafisha.ui.theme.ColorActionText
import ramble.sokol.sberafisha.ui.theme.ColorTextHint
import ramble.sokol.sberafisha.ui.theme.ColorTextSecond
import ramble.sokol.sberafisha.ui.theme.TextTitle
import ramble.sokol.sberafisha.ui.theme.White


private lateinit var roleManager: RoleManager
private lateinit var firstEntryManager: FirstEntryManager

@Composable
@Destination
fun StartTestScreen(
    navigator: DestinationsNavigator
){

    val context = LocalContext.current

    roleManager = RoleManager(context)

    firstEntryManager = FirstEntryManager(context)

    var clickNumber by remember {
        mutableIntStateOf(0)
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(White)
        .padding(top = 76.dp, bottom = 36.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally){

        Row (
            modifier = Modifier.padding(horizontal = 59.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        )
        {
            Text(
                text = stringResource(id = R.string.text_title_start_test),
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.mont_bold)),
                    fontWeight = FontWeight(800),
                    color = TextTitle,
                    textAlign = TextAlign.Center,
                    letterSpacing = 0.24.sp,
                )
            )
        }

        Column(
            modifier = Modifier
                .padding(horizontal = 22.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BoxTest(
                text = stringResource(id = R.string.text_parent),
                colorBorder = if(clickNumber != 1) BoxTextNotClick else ColorActionText,
                colorBackground = if(clickNumber != 1) White else BoxBackTest,
                colorText = if(clickNumber != 1) ColorTextHint else ColorActionText
            ){
                clickNumber = 1
            }

            Spacer(modifier = Modifier.padding(top = 8.dp))

            BoxTest(
                text = stringResource(id = R.string.text_teenager),
                colorBorder = if(clickNumber != 2) BoxTextNotClick else ColorActionText,
                colorBackground = if(clickNumber != 2) White else BoxBackTest,
                colorText = if(clickNumber != 2) ColorTextHint else ColorActionText
            ){
                clickNumber = 2
            }

            Spacer(modifier = Modifier.padding(top = 8.dp))

            BoxTest(
                text = stringResource(id = R.string.text_tourist),
                colorBorder = if(clickNumber != 3) BoxTextNotClick else ColorActionText,
                colorBackground = if(clickNumber != 3) White else BoxBackTest,
                colorText = if(clickNumber != 3) ColorTextHint else ColorActionText
            ){
                clickNumber = 3
            }
        }

        Column(modifier = Modifier.padding(horizontal = 32.dp)) {
            Text(
                text = "Это поможет подобрать самые интересные события",
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 20.5.sp,
                    fontFamily = FontFamily(Font(R.font.mont_semibold)),
                    fontWeight = FontWeight(700),
                    color = ColorTextHint,
                    textAlign = TextAlign.Center,
                )
            )

            Spacer(modifier = Modifier.padding(top = 16.dp))


            if (clickNumber != 0){
                ButtonFurtherStartTest(
                    text = stringResource(id = R.string.text_to_app)) {
                    roleManager.saveRole(clickNumber)
                    firstEntryManager.saveFirstTest(true)
                    navigator.popBackStack()
                    navigator.navigate(BottomMenuScreenDestination)
                }
            }

        }

    }

}