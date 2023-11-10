package ramble.sokol.sberafisha.start_test.view.components

import android.view.MotionEvent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ramble.sokol.sberafisha.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BoxTest(
    text: String,
    colorBorder: Color,
    colorBackground: Color,
    colorText: Color,
    onClick: () -> Unit
){

    val selected = remember { mutableStateOf(false) }
    val scale = animateFloatAsState(if (selected.value) 0.85f else 1f, label = "")

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = colorBorder,
                shape = RoundedCornerShape(size = 9.dp)
            )
            .background(
                color = colorBackground ,
                shape = RoundedCornerShape(size = 9.dp)
            )
            .pointerInteropFilter {
                when (it.action) {
                    MotionEvent.ACTION_DOWN -> {
                        selected.value = true }

                    MotionEvent.ACTION_UP  -> {
                        selected.value = false
                        onClick()
                    }
                }
                true
            },
    ){

        Row (
            modifier = Modifier
                .padding(horizontal = 28.dp, vertical = 28.dp)
        ){
            Text(
                text = text,
                style = TextStyle(
                    fontSize = 15.sp,
                    lineHeight = 17.1.sp,
                    fontFamily = FontFamily(Font(R.font.mont_semibold)),
                    fontWeight = FontWeight(700),
                    color = colorText,
                )
            )
        }

    }
}