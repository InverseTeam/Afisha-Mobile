package ramble.sokol.sberafisha.main.bottom_navbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import ramble.sokol.sberafisha.ui.theme.ColorTextHint
import ramble.sokol.sberafisha.ui.theme.ColorTrueNav
import ramble.sokol.sberafisha.ui.theme.White
import com.ramcosta.composedestinations.annotation.Destination
import ramble.sokol.sberafisha.actually.view.screens.ActuallyScreen
import ramble.sokol.sberafisha.afisha.view.screens.AfishaScreen
import ramble.sokol.sberafisha.profile.view.screens.ProfileScreen
import ramble.sokol.sberafisha.R
import ramble.sokol.sberafisha.routes.view.screens.RouteScreen

@Destination
@Composable
fun BottomMenuScreen(
    navigator: DestinationsNavigator
){

    var selectedItem by rememberSaveable {
        mutableIntStateOf(1)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BottomNavigation (
            modifier = Modifier
                .wrapContentSize()
                .clip(RoundedCornerShape(30.dp, 30.dp, 0.dp, 0.dp)),
            elevation = 4.dp,
            backgroundColor = White,
        ){

            BottomNavigationItem(
                selected = selectedItem == 0,
                onClick = {
                    selectedItem = 0
                },
                icon = {
                    Icon(
                        modifier = Modifier
                            .width(24.dp)
                            .height(24.dp),
                        painter = painterResource(id = R.drawable.logo_afisha),
                        contentDescription = "Poster",
                        tint = if (selectedItem == 0) ColorTrueNav else ColorTextHint
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = R.string.text_poster),
                        color = if (selectedItem == 0) ColorTrueNav else ColorTextHint,
                        style = TextStyle(
                            fontSize = 10.sp,
                            lineHeight = 12.sp,
                            fontFamily = FontFamily(Font(R.font.mont_semibold)),
                            fontWeight = FontWeight(700)
                        )
                        )
                }
            )
            BottomNavigationItem(
                selected = selectedItem == 1,
                onClick = {
                    selectedItem = 1
                },
                icon = {
                    Icon(
                        modifier = Modifier
                            .width(24.dp)
                            .height(24.dp),
                        painter = painterResource(id = R.drawable.logo_route),
                        contentDescription = "Route",
                        tint = if (selectedItem == 1) ColorTrueNav else ColorTextHint
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = R.string.text_router),
                        color = if (selectedItem == 1) ColorTrueNav else ColorTextHint,
                        style = TextStyle(
                            fontSize = 10.sp,
                            lineHeight = 12.sp,
                            fontFamily = FontFamily(Font(R.font.mont_semibold)),
                            fontWeight = FontWeight(700)
                        )
                    )
                }
            )
            BottomNavigationItem(
                selected = selectedItem == 2,
                onClick = {
                    selectedItem = 2
                },
                icon = {
                    Icon(
                        modifier = Modifier
                            .width(24.dp)
                            .height(24.dp),
                        painter = painterResource(id = R.drawable.icon_actually),
                        contentDescription = "Actually",
                        tint = if (selectedItem == 2) ColorTrueNav else ColorTextHint
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = R.string.text_actually),
                        color = if (selectedItem == 2) ColorTrueNav else ColorTextHint,
                        style = TextStyle(
                            fontSize = 10.sp,
                            lineHeight = 12.sp,
                            fontFamily = FontFamily(Font(R.font.mont_semibold)),
                            fontWeight = FontWeight(700)
                        )
                    )
                }
            )
            BottomNavigationItem(
                selected = selectedItem == 3,
                onClick = {
                    selectedItem = 3
                },
                icon = {
                    Icon(
                        modifier = Modifier
                            .width(23.dp)
                            .height(23.dp),
                        painter = painterResource(id = R.drawable.logo_profile),
                        contentDescription = "Profile",
                        tint = if (selectedItem == 3) ColorTrueNav else ColorTextHint
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = R.string.text_profile),
                        color = if (selectedItem == 3) ColorTrueNav else ColorTextHint,
                        style = TextStyle(
                            fontSize = 10.sp,
                            lineHeight = 12.sp,
                            fontFamily = FontFamily(Font(R.font.mont_semibold)),
                            fontWeight = FontWeight(700)
                        )
                    )
                }
            )
        }
    }

    when (selectedItem){
        0 -> AfishaScreen(navigator)
        1 -> RouteScreen(navigator)
        2 -> ActuallyScreen(navigator)
        3 -> ProfileScreen(navigator)
    }

}