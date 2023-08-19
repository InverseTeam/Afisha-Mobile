package ramble.sokol.sberafisha.routes.view.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import ramble.sokol.sberafisha.destinations.AfterTestScreenDestination
import ramble.sokol.sberafisha.destinations.BeforeTestScreenDestination
import ramble.sokol.sberafisha.destinations.BottomMenuScreenDestination
import ramble.sokol.sberafisha.destinations.EntryScreenDestination
import ramble.sokol.sberafisha.destinations.MapAfterTestScreenDestination
import ramble.sokol.sberafisha.destinations.RouteScreenDestination
import ramble.sokol.sberafisha.profile.view.components.ButtonChangeProfile
import ramble.sokol.sberafisha.routes.domain.models.DataForTest
import ramble.sokol.sberafisha.routes.view.components.ButtonFurtherTest
import ramble.sokol.sberafisha.start_test.view.components.BoxTest
import ramble.sokol.sberafisha.start_test.view.components.ButtonFurtherStartTest
import ramble.sokol.sberafisha.ui.theme.BoxBackTest
import ramble.sokol.sberafisha.ui.theme.BoxNotPassed
import ramble.sokol.sberafisha.ui.theme.BoxTextNotClick
import ramble.sokol.sberafisha.ui.theme.ColorActionText
import ramble.sokol.sberafisha.ui.theme.ColorTextHint
import ramble.sokol.sberafisha.ui.theme.TextTitle
import ramble.sokol.sberafisha.ui.theme.White

private lateinit var dataForTest: DataForTest

@Composable
@Destination
fun TestRouterScreen(
    navigator: DestinationsNavigator
){

    dataForTest = DataForTest()

    var countQuestion by remember {
        mutableIntStateOf(0)
    }

    var clickNumber by remember {
        mutableIntStateOf(0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(top = 12.dp)
            .background(
                color = White
            )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ){
            Image(
                modifier = Modifier
                    .width(28.dp)
                    .height(28.dp)
                    .clickable {
                        navigator.popBackStack()
                        navigator.navigate(BeforeTestScreenDestination)
                    },
                painter = painterResource(id = R.drawable.image_button_back),
                contentDescription = "image button back")
        }
        
        Spacer(modifier = Modifier.padding(top = 37.dp))

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 59.dp)) {

            Text(text = stringResource(id = dataForTest.arrQuestions[countQuestion]),
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

        Spacer(modifier = Modifier.padding(top = 40.dp))

        Column(
            modifier = Modifier
                .padding(horizontal = 22.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            BoxTest(
                text = DataForTest().arrAnswers[countQuestion][0],
                colorBorder = if(clickNumber != 1) BoxTextNotClick else ColorActionText,
                colorBackground = if(clickNumber != 1) White else BoxBackTest,
                colorText = if(clickNumber != 1) ColorTextHint else ColorActionText
            ){
                clickNumber = 1
            }

            Spacer(modifier = Modifier.padding(top = 8.dp))

            BoxTest(
                text = DataForTest().arrAnswers[countQuestion][1],
                colorBorder = if(clickNumber != 2) BoxTextNotClick else ColorActionText,
                colorBackground = if(clickNumber != 2) White else BoxBackTest,
                colorText = if(clickNumber != 2) ColorTextHint else ColorActionText
            ){
                clickNumber = 2
            }

            Spacer(modifier = Modifier.padding(top = 8.dp))

            BoxTest(
                text = DataForTest().arrAnswers[countQuestion][2],
                colorBorder = if(clickNumber != 3) BoxTextNotClick else ColorActionText,
                colorBackground = if(clickNumber != 3) White else BoxBackTest,
                colorText = if(clickNumber != 3) ColorTextHint else ColorActionText
            ){
                clickNumber = 3
            }

            if (countQuestion != 1){

                Spacer(modifier = Modifier.padding(top = 8.dp))

                BoxTest(
                    text = DataForTest().arrAnswers[countQuestion][3],
                    colorBorder = if(clickNumber != 4) BoxTextNotClick else ColorActionText,
                    colorBackground = if(clickNumber != 4) White else BoxBackTest,
                    colorText = if(clickNumber != 4) ColorTextHint else ColorActionText
                ){
                    clickNumber = 4
                }
            }

            if (countQuestion != 1){

                Spacer(modifier = Modifier.padding(top = 8.dp))

                BoxTest(
                    text = DataForTest().arrAnswers[countQuestion][4],
                    colorBorder = if(clickNumber != 5) BoxTextNotClick else ColorActionText,
                    colorBackground = if(clickNumber != 5) White else BoxBackTest,
                    colorText = if(clickNumber != 5) ColorTextHint else ColorActionText
                ){
                    clickNumber = 5
                }
            }
        }
        
        Spacer(modifier = Modifier.padding(top = 63.dp))

        Column(modifier = Modifier
            .padding(horizontal = 28.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.text_main_hint_test),
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 20.5.sp,
                    fontFamily = FontFamily(Font(R.font.mont_bold)),
                    fontWeight = FontWeight(700),
                    color = ColorTextHint,
                    textAlign = TextAlign.Center,
                )
            )

            Spacer(modifier = Modifier.padding(top = 16.dp))

            if (clickNumber != 0) {
                ButtonFurtherStartTest(
                    text = stringResource(id = R.string.text_further)
                ) {
                    if (countQuestion < 2){
                        countQuestion++
                        clickNumber = 0
                    }
                    else if (countQuestion == 2 && clickNumber == 5){
                        navigator.popBackStack()
                        navigator.navigate(MapAfterTestScreenDestination)
                    }else if (countQuestion == 2){
                        countQuestion++
                        clickNumber = 0
                    }else{
                        navigator.popBackStack()
                        navigator.navigate(AfterTestScreenDestination)
                    }
                }
            }
        }

    }

    BackHandler {
        navigator.popBackStack()
        navigator.navigate(BeforeTestScreenDestination)
    }

}