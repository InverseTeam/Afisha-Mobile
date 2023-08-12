package ramble.sokol.sberafisha.routes.view.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ramble.sokol.sberafisha.R
import ramble.sokol.sberafisha.ui.theme.AdTextButton
import ramble.sokol.sberafisha.ui.theme.BackButtonSearch
import ramble.sokol.sberafisha.ui.theme.ColorBackgroundButton
import ramble.sokol.sberafisha.ui.theme.ColorBackgroundTextField
import ramble.sokol.sberafisha.ui.theme.ColorTextField
import ramble.sokol.sberafisha.ui.theme.ColorTextSecond
import ramble.sokol.sberafisha.ui.theme.White

@Composable
fun ButtonWithoutSurvey(
    text: String,
    onClick: () -> Unit
)
{
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = ColorBackgroundTextField,
                shape = RoundedCornerShape(size = 16.dp)
            ),
        colors = ButtonDefaults.buttonColors(
            containerColor = ColorBackgroundTextField
        ),
        onClick = onClick

    ){
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            text = text,
            style = TextStyle(
                fontSize = 16.sp,
                lineHeight = 16.sp,
                fontWeight = FontWeight(700),
                color = AdTextButton,
                fontFamily = FontFamily(Font(R.font.mont_semibold)),
                textAlign = TextAlign.Center
            )
        )
    }
}