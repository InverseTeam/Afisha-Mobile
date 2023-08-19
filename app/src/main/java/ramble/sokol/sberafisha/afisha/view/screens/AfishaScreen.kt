package ramble.sokol.sberafisha.afisha.view.screens

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import ramble.sokol.sberafisha.R
import ramble.sokol.sberafisha.afisha.model.data.AllEventsItem
import ramble.sokol.sberafisha.afisha.model.data.ResponseEvents
import ramble.sokol.sberafisha.afisha.model.service.APIAfisha
import ramble.sokol.sberafisha.afisha.view.components.ButtonDateAfisha
import ramble.sokol.sberafisha.afisha.view.components.CardEvents
import ramble.sokol.sberafisha.afisha.view.components.CardEventsResponse
import ramble.sokol.sberafisha.afisha.view.components.ProgressBarAfisha
import ramble.sokol.sberafisha.afisha.view_model.AllEventsViewModel
import ramble.sokol.sberafisha.destinations.CurrentEventsScreenDestination
import ramble.sokol.sberafisha.model_project.FirstEntryManager
import ramble.sokol.sberafisha.model_project.RetrofitHelper
import ramble.sokol.sberafisha.model_project.TokenManager
import ramble.sokol.sberafisha.ui.theme.ColorTextField
import ramble.sokol.sberafisha.ui.theme.TextTitle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDate
import java.util.Calendar
import java.util.Date


private lateinit var firstEntryManager: FirstEntryManager
private lateinit var apiAfisha: APIAfisha
private lateinit var listRecommendEvents: ArrayList<ResponseEvents>
private lateinit var checkRecommend: MutableState<Boolean>
private lateinit var listConcert: ArrayList<ResponseEvents>
private lateinit var checkConcert: MutableState<Boolean>
private lateinit var listCinema: ArrayList<ResponseEvents>
private lateinit var checkCinema: MutableState<Boolean>
private lateinit var listTheater: ArrayList<ResponseEvents>
private lateinit var checkTheater: MutableState<Boolean>
private lateinit var tokenManager: TokenManager
private lateinit var currentDate: MutableState<String>

@SuppressLint("CoroutineCreationDuringComposition")
@Destination
@Composable
fun AfishaScreen(
    navigator: DestinationsNavigator
){

    val context = LocalContext.current

    firstEntryManager = FirstEntryManager(context)

    tokenManager = TokenManager(context = context)

    val checkRegistration = firstEntryManager.getFirstTest()

    if (checkRegistration == false){
        val allEventsViewModel  = hiltViewModel<AllEventsViewModel>()
        val allEvents by allEventsViewModel.allEvents.collectAsState()
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

            //Log.d("MyLog", allEvents.toString())

            LazyColumn(modifier = Modifier.fillMaxWidth()){
                if (allEvents.isEmpty()){
                    item {
                        ProgressBarAfisha()
                    }
                }
                else if (allEvents[0].id.toInt() == -1){
                    Toast.makeText(context, R.string.text_toast_no_internet, Toast.LENGTH_SHORT).show()
                }else{
                    items(allEvents){ allEvents: AllEventsItem ->
                        CardEvents(event = allEvents){
                            navigator.popBackStack()
                            navigator.navigate(CurrentEventsScreenDestination(allEvents.id))
                        }
                    }

                }
            }
        }
    }else{

        listRecommendEvents = arrayListOf()

        checkRecommend = remember {
            mutableStateOf(false)
        }

        listConcert = arrayListOf()

        checkConcert = remember {
            mutableStateOf(false)
        }

        listCinema = arrayListOf()

        checkCinema = remember {
            mutableStateOf(false)
        }

        listTheater = arrayListOf()

        checkTheater = remember {
            mutableStateOf(false)
        }

        apiAfisha = RetrofitHelper.getInstance().create(APIAfisha::class.java)

        val dayI = LocalDate.now().dayOfMonth
        val dayS = if (dayI < 10) "0$dayI" else dayI.toString()
        val monthI = LocalDate.now().monthValue
        val monthS = if (monthI < 10) "0$monthI" else monthI.toString()

        currentDate = remember {
            mutableStateOf("${LocalDate.now().year}-$monthS-$dayS")
        }

        val year: Int
        val month: Int
        val day: Int

        val calendar = Calendar.getInstance()
        year = calendar.get((Calendar.YEAR))
        month = calendar.get((Calendar.MONTH))
        day = calendar.get((Calendar.DAY_OF_MONTH))
        calendar.time = Date()

        val datePicker = DatePickerDialog(
            context, {
                _: DatePicker, year: Int, month: Int, day: Int ->
                val dayS2 = if (day < 10) "0$day" else "$day"
                val monthS2 = if (month < 10) "0${month + 1}" else "${month + 1}"
                currentDate.value = "$year-$monthS2-$dayS2"
                checkCinema.value = false
                checkRecommend.value = false
                checkConcert.value = false
                checkTheater.value = false
            }, year, month, day
        )

        datePicker.datePicker.minDate = System.currentTimeMillis() - 1000;

        getData(context, currentDate.value, null)
        getData(context, currentDate.value, 10)
        getData(context, currentDate.value, 26)
        getData(context, currentDate.value, 20)

        ser(navigator = navigator, datePicker = datePicker, context)

    }
}

@Composable
private fun ser(navigator: DestinationsNavigator, datePicker: DatePickerDialog, context: Context){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 60.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 32.dp, end = 32.dp)
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

            Spacer(modifier = Modifier.padding(top = 16.dp))

            ButtonDateAfisha(text = currentDate.value) {
                datePicker.show()
            }

            Spacer(modifier = Modifier.padding(top = 16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.CenterEnd
                ) {

                    Column(modifier = Modifier.padding(end = 25.dp)) {
                        Image(
                            modifier = Modifier
                                .width(83.45267.dp)
                                .height(62.63379.dp),
                            painter = painterResource(id = R.drawable.icon_text_recommendation),
                            contentDescription = "icon recommend"
                        )
                    }

                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(id = R.string.text_recommendations),
                        style = TextStyle(
                            fontSize = 32.sp,
                            lineHeight = 35.2.sp,
                            fontFamily = FontFamily(Font(R.font.mont_bold)),
                            fontWeight = FontWeight(800),
                            color = ColorTextField,
                            textAlign = TextAlign.Center
                        )
                    )
                }

                Spacer(modifier = Modifier.padding(top = 10.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    if (!checkRecommend.value) {
                        ProgressBarAfisha()
                    } else {
                        LazyRow() {
                            Log.d("MyLog", "LazyRow")
                            items(listRecommendEvents) { event: ResponseEvents ->
                                CardEventsResponse(event = event) {
                                    navigator.popBackStack()
                                    navigator.navigate(CurrentEventsScreenDestination(event.id))
                                }
                            }
                        }
                    }
                }


                Spacer(modifier = Modifier.padding(top = 24.dp))

                Text(
                    text = stringResource(id = R.string.text_concerts),
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.mont_bold)),
                        fontWeight = FontWeight(800),
                        color = TextTitle,
                        letterSpacing = 0.18.sp,
                    )
                )

                Spacer(modifier = Modifier.padding(top = 15.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    if (!checkConcert.value) {
                        ProgressBarAfisha()
                    } else {
                        LazyRow() {
                            Log.d("MyLog", "LazyRowConcert")
                            items(listConcert) { event: ResponseEvents ->
                                CardEventsResponse(event = event) {
                                    navigator.popBackStack()
                                    navigator.navigate(CurrentEventsScreenDestination(event.id))
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.padding(top = 32.dp))

//                Text(
//                    text = stringResource(id = R.string.text_exposition),
//                    style = TextStyle(
//                        fontSize = 18.sp,
//                        fontFamily = FontFamily(Font(R.font.mont_bold)),
//                        fontWeight = FontWeight(800),
//                        color = TextTitle,
//                        letterSpacing = 0.18.sp,
//                    )
//                )
//
//                Spacer(modifier = Modifier.padding(top = 15.dp))
//
//                // lazyrow
//
//                Spacer(modifier = Modifier.padding(top = 32.dp))

//                Text(
//                    text = stringResource(id = R.string.text_lectures),
//                    style = TextStyle(
//                        fontSize = 18.sp,
//                        fontFamily = FontFamily(Font(R.font.mont_bold)),
//                        fontWeight = FontWeight(800),
//                        color = TextTitle,
//                        letterSpacing = 0.18.sp,
//                    )
//                )
//
//                Spacer(modifier = Modifier.padding(top = 15.dp))
//
//                // lazyrow
//
//                Spacer(modifier = Modifier.padding(top = 32.dp))

                Text(
                    text = stringResource(id = R.string.text_cinema),
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.mont_bold)),
                        fontWeight = FontWeight(800),
                        color = TextTitle,
                        letterSpacing = 0.18.sp,
                    )
                )

                Spacer(modifier = Modifier.padding(top = 15.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    if (!checkCinema.value) {
                        ProgressBarAfisha()
                    } else {
                        LazyRow() {
                            Log.d("MyLog", "LazyRowCinema")
                            items(listCinema) { event: ResponseEvents ->
                                CardEventsResponse(event = event) {
                                    navigator.popBackStack()
                                    navigator.navigate(CurrentEventsScreenDestination(event.id))
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.padding(top = 32.dp))

                Text(
                    text = stringResource(id = R.string.text_theater),
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.mont_bold)),
                        fontWeight = FontWeight(800),
                        color = TextTitle,
                        letterSpacing = 0.18.sp,
                    )
                )

                Spacer(modifier = Modifier.padding(top = 15.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
                ) {
                    if (!checkTheater.value) {
                        ProgressBarAfisha()
                    } else {
                        LazyRow() {
                            Log.d("MyLog", "LazyRowTheater")
                            items(listTheater) { event: ResponseEvents ->
                                CardEventsResponse(event = event) {
                                    navigator.popBackStack()
                                    navigator.navigate(CurrentEventsScreenDestination(event.id))
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.padding(top = 32.dp))

//                Text(
//                    text = stringResource(id = R.string.text_sport),
//                    style = TextStyle(
//                        fontSize = 18.sp,
//                        fontFamily = FontFamily(Font(R.font.mont_bold)),
//                        fontWeight = FontWeight(800),
//                        color = TextTitle,
//                        letterSpacing = 0.18.sp,
//                    )
//                )
//
//                Spacer(modifier = Modifier.padding(top = 15.dp))
//
//                // lazyrow
//
//                Spacer(modifier = Modifier.padding(top = 32.dp))

            }
        }
    }
}

private fun getData(context: Context, date: String, category: Int?){
    val call = apiAfisha.getEventsFilter(date, category, "Token ${tokenManager.getToken()!!}")

    call.enqueue(object : Callback<ArrayList<ResponseEvents>> {
        override fun onResponse(call: Call<ArrayList<ResponseEvents>>, response: Response<ArrayList<ResponseEvents>>) {
            if (response.isSuccessful) {
                when (category) {
                    10 -> {
                        val responseBody = response.body()
                        listConcert = responseBody!!
                        //Log.d("MyLog", listRecommendEvents.toString())
                        checkConcert.value = listConcert.isNotEmpty()
                        //Log.d("MyLog", checkRecommend.value.toString())
                    }
                    26 -> {
                        val responseBody = response.body()
                        listCinema = responseBody!!
                        //Log.d("MyLog", listRecommendEvents.toString())
                        checkCinema.value = listCinema.isNotEmpty()
                        //Log.d("MyLog", checkRecommend.value.toString())
                    }
                    20 ->{
                        val responseBody = response.body()
                        listTheater = responseBody!!
                        //Log.d("MyLog", listRecommendEvents.toString())
                        checkTheater.value = listTheater.isNotEmpty()
                        //Log.d("MyLog", checkRecommend.value.toString())
                    }
                    null -> {
                        val responseBody = response.body()
                        listRecommendEvents = responseBody!!
                        //Log.d("MyLog", listRecommendEvents.toString())
                        checkRecommend.value = listRecommendEvents.isNotEmpty()
                        Log.d("MyLog", checkRecommend.value.toString())
                    }
                }
            } else {
                Log.d("MyLog", response.toString())
                Toast.makeText(context, R.string.text_appeared_error, Toast.LENGTH_SHORT).show()
            }
        }

        override fun onFailure(call: Call<ArrayList<ResponseEvents>>, t: Throwable) {
            Toast.makeText(context, R.string.text_toast_no_internet, Toast.LENGTH_SHORT).show()
        }
    })

}