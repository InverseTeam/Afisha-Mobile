package ramble.sokol.sberafisha.afisha.view.screens

import android.app.DatePickerDialog
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
import com.maxkeppeker.sheets.core.models.base.rememberSheetState
import com.maxkeppeler.sheets.calendar.CalendarDialog
import com.maxkeppeler.sheets.calendar.models.CalendarConfig
import com.maxkeppeler.sheets.calendar.models.CalendarSelection
import com.maxkeppeler.sheets.calendar.models.CalendarStyle
import com.ramcosta.composedestinations.annotation.Destination
import ramble.sokol.sberafisha.R
import ramble.sokol.sberafisha.afisha.view.components.ButtonDatePickPoster
import ramble.sokol.sberafisha.ui.theme.TextTitle
import ramble.sokol.sberafisha.ui.theme.TextTitlePoster
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun AfishaScreen(){

    val c = Calendar.getInstance()
    val year = c.get(Calendar.YEAR)
    val month = c.get(Calendar.MONTH)
    val day = c.get(Calendar.DAY_OF_MONTH)
    val context = LocalContext.current

    var date by remember {
        mutableStateOf("")
    }

    val datePickerDialog = DatePickerDialog(
        context, { d, year1, month1, day1 ->
            val month = month + 1
            date = "$day1 - $month - $year1"
        }, year, month, day
    )

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(top = 16.dp, start = 32.dp, end = 32.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.text_poster),
            style = TextStyle(
                fontSize = 24.sp,
                fontFamily = FontFamily(Font(R.font.mont_bold)),
                fontWeight = FontWeight(800),
                color = TextTitle,
                letterSpacing = 0.24.sp,
                textAlign = TextAlign.Left
            )
        )

        Spacer(modifier = Modifier.padding(top = 22.dp))

        ButtonDatePickPoster(text = "fse") {
            datePickerDialog.show()
        }
        
        Spacer(modifier = Modifier.padding(top = 30.dp))

        Box(contentAlignment = Alignment.CenterEnd) {
            Image(
                modifier = Modifier
                    .width(83.45267.dp)
                    .height(62.63379.dp),
                painter = painterResource(id = R.drawable.icon_text_recommendation),
                contentDescription = "image for text recommend"
            )

            Text(
                text = stringResource(id = R.string.text_recommendations),
                style = TextStyle(
                    fontSize = 32.sp,
                    lineHeight = 35.2.sp,
                    fontFamily = FontFamily(Font(R.font.mont_bold)),
                    fontWeight = FontWeight(800),
                    color = TextTitlePoster,
                )
            )
        }
    }
}