package ramble.sokol.sberafisha.afisha.view.screens

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.annotation.Destination
import ramble.sokol.sberafisha.R
import ramble.sokol.sberafisha.destinations.BottomMenuScreenDestination
import ramble.sokol.sberafisha.profile.view.components.ButtonPushkin
import ramble.sokol.sberafisha.ui.theme.BackColumn
import ramble.sokol.sberafisha.ui.theme.ColorTextField
import ramble.sokol.sberafisha.ui.theme.ColorTextHint
import ramble.sokol.sberafisha.ui.theme.ColorTextSecond
import ramble.sokol.sberafisha.ui.theme.CurrentEventHint
import ramble.sokol.sberafisha.ui.theme.CurrentEventName
import ramble.sokol.sberafisha.ui.theme.TextTitle
import ramble.sokol.sberafisha.ui.theme.White

@Composable
@Destination
fun CurrentEventsScreen (
    navigator: DestinationsNavigator
) {

    var clickFavorite by remember {
        mutableStateOf(false)
    }

    val iconFavorite = if (clickFavorite)
        painterResource(id = R.drawable.favorite_true)
    else
        painterResource(id = R.drawable.favorite_false)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackColumn) ,
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

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
                            navigator.navigate(BottomMenuScreenDestination)
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
                    text = stringResource(id = R.string.text_event),
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
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                contentAlignment = Alignment.TopEnd
            ){
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    painter = painterResource(id = R.drawable.image_warning),
                    contentDescription = "image current events",
                    contentScale = ContentScale.Crop
                )
                IconButton(
                    modifier = Modifier
                        .width(48.dp)
                        .height(48.dp),
                    onClick ={
                        clickFavorite = !clickFavorite
                    }
                ){
                    Icon(
                        painter = iconFavorite,
                        contentDescription = "icon favorite"
                    )
                }
            }

            Spacer(modifier = Modifier.padding(top = 16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Цирк Никулина",
                    style = TextStyle(
                        fontSize = 20.sp,
                        lineHeight = 22.sp,
                        fontFamily = FontFamily(Font(R.font.mont_bold)),
                        fontWeight = FontWeight(800),
                        color = CurrentEventName,
                    )
                )

                Text(
                    text = "1500₽",
                    style = TextStyle(
                        fontSize = 20.sp,
                        lineHeight = 22.sp,
                        fontFamily = FontFamily(Font(R.font.mont_bold)),
                        fontWeight = FontWeight(800),
                        color = ColorTextSecond,
                        textAlign = TextAlign.Right,
                    )
                )
            }

            Spacer(modifier = Modifier.padding(top = 4.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "8 августа, 18:00 · Геологическая 8",
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 17.6.sp,
                    fontFamily = FontFamily(Font(R.font.mont_semibold)),
                    fontWeight = FontWeight(700),
                    color = CurrentEventHint,
                    textAlign = TextAlign.Start
                )
            )

            Spacer(modifier = Modifier.padding(top = 32.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.text_about_events),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.mont_bold)),
                    fontWeight = FontWeight(800),
                    color = TextTitle,
                    letterSpacing = 0.18.sp,
                    textAlign = TextAlign.Start
                )
            )

            Spacer(modifier = Modifier.padding(top = 4.dp))

            Text(
                text = "В настоящее время коллектив «Московский цирк на льду» под руководством Наталии Абрамовой Московского цирка Никулина на Цветном бульваре — коллектив молодых, красивых, и высокопрофессиональных артистов. Бережно сохраняя лучшие традиции",
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontFamily = FontFamily(Font(R.font.mont_semibold)),
                    fontWeight = FontWeight(700),
                    color = ColorTextHint,
                )
            )

            Spacer(modifier = Modifier.padding(top = 24.dp))

            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.image_with_pushkin),
                contentDescription = "without pushkin"
            )

            Image(
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(id = R.drawable.image_without_pushkin),
                contentDescription = "without pushkin"
            )
            
            Spacer(modifier = Modifier.padding(top = 8.dp))

            ButtonPushkin(text = stringResource(id = R.string.text_pushkin)) {
                
            }
            
            Spacer(modifier = Modifier.padding(top = 15.dp))

        }
    }
}