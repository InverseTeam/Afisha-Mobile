package ramble.sokol.sberafisha.routes.view.screens

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
import ramble.sokol.sberafisha.destinations.RouteScreenDestination
import ramble.sokol.sberafisha.profile.view.components.ButtonChangeProfile
import ramble.sokol.sberafisha.routes.domain.models.DataForTest
import ramble.sokol.sberafisha.routes.view.components.ButtonFurtherTest
import ramble.sokol.sberafisha.ui.theme.BoxNotPassed
import ramble.sokol.sberafisha.ui.theme.BoxTextNotClick
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
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                modifier = Modifier
                    .width(24.dp)
                    .height(24.dp)
                    .clickable {
                        navigator.popBackStack()
                        navigator.navigate(BottomMenuScreenDestination)
                    },
                painter = painterResource(id = R.drawable.image_button_back),
                contentDescription = "image button back")
            Row (
                Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center
            ){
                Box(
                    Modifier
                        .width(41.7473.dp)
                        .height(8.dp)
                        .background(
                            color = BoxNotPassed,
                            shape = RoundedCornerShape(size = 28.dp)
                        )
                )

                Spacer(modifier = Modifier.padding(start = 6.82.dp))

                Box(
                    Modifier
                        .width(41.7473.dp)
                        .height(8.dp)
                        .background(
                            color = BoxNotPassed,
                            shape = RoundedCornerShape(size = 28.dp)
                        )
                )

                Spacer(modifier = Modifier.padding(start = 6.82.dp))

                Box(
                    Modifier
                        .width(41.7473.dp)
                        .height(8.dp)
                        .background(
                            color = BoxNotPassed,
                            shape = RoundedCornerShape(size = 28.dp)
                        )
                )

                Spacer(modifier = Modifier.padding(start = 6.82.dp))

                Box(
                    Modifier
                        .width(41.7473.dp)
                        .height(8.dp)
                        .background(
                            color = BoxNotPassed,
                            shape = RoundedCornerShape(size = 28.dp)
                        )
                )

                Spacer(modifier = Modifier.padding(start = 6.82.dp))

                Box(
                    Modifier
                        .width(41.7473.dp)
                        .height(8.dp)
                        .background(
                            color = BoxNotPassed,
                            shape = RoundedCornerShape(size = 28.dp)
                        )
                )
            }
        }
        
        Spacer(modifier = Modifier.padding(top = 37.dp))

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 59.dp)) {

            Text(text = stringResource(id = dataForTest.arrQuestions[0]),
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

        Spacer(modifier = Modifier.padding(top = 32.dp))

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 22.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 2.dp,
                        color = BoxTextNotClick,
                        shape = RoundedCornerShape(size = 9.dp)
                    )
                    .background(
                        color = White,
                        shape = RoundedCornerShape(size = 9.dp)
                    ),
            ){

                Row (
                    modifier = Modifier
                        .padding(horizontal = 28.dp, vertical = 28.dp)
                ){
                    Text(
                        text = "Спокойный",
                        style = TextStyle(
                            fontSize = 15.sp,
                            lineHeight = 17.1.sp,
                            fontFamily = FontFamily(Font(R.font.mont_semibold)),
                            fontWeight = FontWeight(700),
                            color = ColorTextHint,
                        )
                    )
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

            ButtonFurtherTest(text = stringResource(id = R.string.text_further)) {
                
            }
            
        }

    }

}