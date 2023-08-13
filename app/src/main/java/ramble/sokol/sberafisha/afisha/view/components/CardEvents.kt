package ramble.sokol.sberafisha.afisha.view.components

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.CoroutineExceptionHandler
import ramble.sokol.sberafisha.R
import ramble.sokol.sberafisha.afisha.domain.models.ResponseEvents
import ramble.sokol.sberafisha.afisha.domain.utils.APIAfisha
import ramble.sokol.sberafisha.model_project.RetrofitHelper
import ramble.sokol.sberafisha.profile.domain.models.ResponseUserInfo
import ramble.sokol.sberafisha.profile.domain.utils.APIProfile
import ramble.sokol.sberafisha.ui.theme.ColorBackgroundButtonSber
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun CardEvents(
    event: ResponseEvents
){

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 32.dp, end = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){

    }

    Image(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(size = 16.dp)
            ),
        painter = rememberAsyncImagePainter("https://inverse-tracker.store/${event.cover}"),
        contentDescription = "image events",
        contentScale = ContentScale.Crop)
    
    Spacer(modifier = Modifier.padding(top = 12.dp))

    Text(
        modifier = Modifier.fillMaxWidth(),
        text = event.name,
        style = TextStyle(
            fontSize = 19.7.sp,
            lineHeight = 21.67.sp,
            fontFamily = FontFamily(Font(R.font.mont_bold)),
            fontWeight = FontWeight(700),
            color = Color(0xFF222222),
        ),
        textAlign = TextAlign.Start
    )

    Spacer(modifier = Modifier.padding(top = 8.dp))

    Text(
        text = "${event.startDate}  ·  ${event.platform.name}",
        style = TextStyle(
            fontSize = 16.89.sp,
            lineHeight = 18.58.sp,
            fontFamily = FontFamily(Font(R.font.mont_semibold)),
            fontWeight = FontWeight(500),
            color = Color(0xFF6B6B6B),
        )
    )
    
    Spacer(modifier = Modifier.padding(top = 20.dp))
}
