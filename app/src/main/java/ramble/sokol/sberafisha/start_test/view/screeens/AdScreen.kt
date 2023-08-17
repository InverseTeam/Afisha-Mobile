package ramble.sokol.sberafisha.start_test.view.screeens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.Modifier
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
import ramble.sokol.sberafisha.destinations.BottomMenuScreenDestination
import ramble.sokol.sberafisha.destinations.StartTestScreenDestination
import ramble.sokol.sberafisha.model_project.FirstEntryManager
import ramble.sokol.sberafisha.profile.view.components.ButtonForEntryProfile
import ramble.sokol.sberafisha.start_test.view.components.ButtonForAdToApp
import ramble.sokol.sberafisha.ui.theme.AdText
import ramble.sokol.sberafisha.ui.theme.ColorTextSecond
import ramble.sokol.sberafisha.ui.theme.White

private lateinit var firstEntryManager: FirstEntryManager

@Composable
@Destination
fun AdScreen(
    navigator: DestinationsNavigator
){


    val mContext = LocalContext.current

    firstEntryManager = FirstEntryManager(mContext)

    Column(modifier = Modifier
        .fillMaxSize()
        .background(White)
        .padding(top = 100.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            text = stringResource(id = R.string.text_ad_title),
            style = TextStyle(
                fontSize = 22.77.sp,
                lineHeight = 22.2.sp,
                fontFamily = FontFamily(Font(R.font.mont_bold)),
                fontWeight = FontWeight(800),
                color = ColorTextSecond,
                textAlign = TextAlign.Center,
            )
        )

        Spacer(modifier = Modifier.padding(top = 8.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            text = stringResource(id = R.string.text_ad_date),
            style = TextStyle(
                fontSize = 22.77.sp,
                lineHeight = 22.2.sp,
                fontFamily = FontFamily(Font(R.font.mont_bold)),
                fontWeight = FontWeight(800),
                color = AdText,
                textAlign = TextAlign.Center,
            )
        )
        
        Spacer(modifier = Modifier.padding(top = 30.dp))

        Row (modifier = Modifier
            .fillMaxWidth()
            .padding(start = 80.dp),
            horizontalArrangement = Arrangement.End){
            Image(
                modifier = Modifier
                    .width(497.dp)
                    .height(374.dp),
                painter = painterResource(id = R.drawable.image_back_ad),
                contentDescription = "ad back")
        }

        Spacer(modifier = Modifier.padding(top = 10.dp))

        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)) {
            ButtonForEntryProfile(text = stringResource(id = R.string.text_about_events)) {
                Toast.makeText(mContext, R.string.text_into_developing, Toast.LENGTH_SHORT).show()
            }

            Spacer(modifier = Modifier.padding(top = 8.dp))

            Log.d("MyLog", "${firstEntryManager.getFirstEntry()} ${firstEntryManager.getFirstTest()}")

            ButtonForAdToApp(text = stringResource(id = R.string.text_to_app)) {
                if (firstEntryManager.getFirstTest() == false && firstEntryManager.getFirstEntry() == true){
                    navigator.popBackStack()
                    navigator.navigate(StartTestScreenDestination)
                }else{
                    navigator.popBackStack()
                    navigator.navigate(BottomMenuScreenDestination)
//            }
                }
            }
        }


    }

}