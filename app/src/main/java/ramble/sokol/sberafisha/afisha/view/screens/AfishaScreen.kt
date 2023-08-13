package ramble.sokol.sberafisha.afisha.view.screens

import android.annotation.SuppressLint
import android.content.Context
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ramble.sokol.sberafisha.R
import ramble.sokol.sberafisha.afisha.domain.models.ResponseEvents
import ramble.sokol.sberafisha.afisha.domain.utils.APIAfisha
import ramble.sokol.sberafisha.afisha.view.components.CardEvents
import ramble.sokol.sberafisha.model_project.FirstEntryManager
import ramble.sokol.sberafisha.model_project.RetrofitHelper
import ramble.sokol.sberafisha.ui.theme.TextTitle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Timer
import java.util.logging.Handler
import kotlin.concurrent.timerTask


private lateinit var firstEntryManager: FirstEntryManager
private lateinit var listEvents: ArrayList<ResponseEvents>
private lateinit var apiAfisha: APIAfisha
private lateinit var coroutineExceptionHandler: CoroutineExceptionHandler
private lateinit var check: MutableState<Boolean>


@SuppressLint("CoroutineCreationDuringComposition")
@Destination
@Composable
fun AfishaScreen(){

    val context = LocalContext.current

    firstEntryManager = FirstEntryManager(context)

    listEvents = ArrayList()

    coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    apiAfisha = RetrofitHelper.getInstance().create(APIAfisha::class.java)

    check = remember {
        mutableStateOf(false)
    }

    val coroutineScope = rememberCoroutineScope()

    getData(context)



    Log.d("MyLog", "After")

    ser()

//    val c = Calendar.getInstance()
//    val year = c.get(Calendar.YEAR)
//    val month = c.get(Calendar.MONTH)
//    val day = c.get(Calendar.DAY_OF_MONTH)
//    val context = LocalContext.current
//
//    var date by remember {
//        mutableStateOf("")
//    }
//
//    val datePickerDialog = DatePickerDialog(
//        context, { d, year1, month1, day1 ->
//            val month = month + 1
//            date = "$day1 - $month - $year1"
//        }, year, month, day
//    )

}
@Composable
private fun ser(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 32.dp, end = 32.dp, bottom = 60.dp),
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

        Spacer(modifier = Modifier.padding(top = 20.dp))

        if (check.value){
            LazyColumn(modifier = Modifier.fillMaxWidth()){
                items(listEvents){ event ->
                    Log.d("MyLog", event.name)
                    CardEvents(event)
                }
            }
        }
    }
}

private fun getData(context: Context){
    val call = apiAfisha.getAllEvents()

    call.enqueue(object : Callback<ArrayList<ResponseEvents>> {
        override fun onResponse(call: Call<ArrayList<ResponseEvents>>, response: Response<ArrayList<ResponseEvents>>) {
            if (response.isSuccessful) {
                val responseBody = response.body()
                listEvents = responseBody!!
                Log.d("MyLog", "finish")
                check.value = true
            } else {
                Toast.makeText(context, R.string.text_appeared_error, Toast.LENGTH_SHORT).show()
            }
        }

        override fun onFailure(call: Call<ArrayList<ResponseEvents>>, t: Throwable) {
            Log.d("MyLog", t.message!!)
            Toast.makeText(context, R.string.text_toast_no_internet, Toast.LENGTH_SHORT).show()
        }
    })
}

