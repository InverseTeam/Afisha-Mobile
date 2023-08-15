package ramble.sokol.sberafisha.afisha.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import ramble.sokol.sberafisha.R
import ramble.sokol.sberafisha.afisha.model.data.AllEventsItem
import ramble.sokol.sberafisha.afisha.model.data.ResponseEvents

@Composable
fun CardEvents(
    event: AllEventsItem
){

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 32.dp, end = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){

    }

    Column (modifier = Modifier
        .fillMaxWidth()
        .height(150.dp)
        .background(
            color = Color.Transparent,
            shape = RoundedCornerShape(size = 16.dp)
        )){
        Image(
            painter = rememberAsyncImagePainter("https://inverse-tracker.store/${event.cover}"),
            contentDescription = "image events",
            contentScale = ContentScale.Crop
        )
    }
    
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
