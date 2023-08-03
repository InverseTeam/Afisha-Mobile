package ramble.sokol.sberafisha.authentication_and_splash.view.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ramble.sokol.sberafisha.R
import ramble.sokol.sberafisha.ui.theme.ColorBackgroundButton
import ramble.sokol.sberafisha.ui.theme.ColorBackgroundButtonSber
import ramble.sokol.sberafisha.ui.theme.White

@Composable
fun ButtonForEntrySber(
    text: String,
    onClick: () -> Unit
)
{
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = ColorBackgroundButtonSber,
                shape = RoundedCornerShape(size = 16.dp)
            ),
        colors = ButtonDefaults.buttonColors(
            containerColor = ColorBackgroundButtonSber
        ),
        onClick = onClick

    ){

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                modifier = Modifier
                    .width(24.dp)
                    .height(24.dp),
                painter = painterResource(id = R.drawable.sber_logo_for_button),
                contentDescription = null
            )

            Spacer(modifier = Modifier.padding(start = 4.dp))

            Text(
                text = text,
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 16.sp,
                    fontWeight = FontWeight(700),
                    color = White,
                    fontFamily = FontFamily(Font(R.font.mont_semibold)),
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}