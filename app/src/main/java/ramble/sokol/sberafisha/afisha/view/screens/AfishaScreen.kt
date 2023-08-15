package ramble.sokol.sberafisha.afisha.view.screens

import android.annotation.SuppressLint
import android.content.Context
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
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.CoroutineExceptionHandler
import ramble.sokol.sberafisha.R
import ramble.sokol.sberafisha.afisha.model.data.AllEventsItem
import ramble.sokol.sberafisha.afisha.model.data.ResponseEvents
import ramble.sokol.sberafisha.afisha.model.service.APIAfisha
import ramble.sokol.sberafisha.afisha.view.components.CardEvents
import ramble.sokol.sberafisha.afisha.view_model.AllEventsViewModel
import ramble.sokol.sberafisha.authentication_and_splash.view.components.ProgressBarAuth
import ramble.sokol.sberafisha.model_project.FirstEntryManager
import ramble.sokol.sberafisha.model_project.RetrofitHelper
import ramble.sokol.sberafisha.ui.theme.TextTitle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


private lateinit var firstEntryManager: FirstEntryManager
private lateinit var listEvents: ArrayList<ResponseEvents>
private lateinit var apiAfisha: APIAfisha
private lateinit var check: MutableState<Boolean>


@SuppressLint("CoroutineCreationDuringComposition")
@Destination
@Composable
fun AfishaScreen(){

    val context = LocalContext.current

    firstEntryManager = FirstEntryManager(context)

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

            Log.d("MyLog", allEvents.toString())

            LazyColumn(modifier = Modifier.fillMaxWidth()){
                if (allEvents.isEmpty()){
                    item {
                        ProgressBarAuth()
                    }
                }
                else if (allEvents[0].id.toInt() == -1){
                    Toast.makeText(context, R.string.text_toast_no_internet, Toast.LENGTH_SHORT).show()
                }else{
                    items(allEvents){ allEvents: AllEventsItem ->
                        CardEvents(event = allEvents)
                    }

                }
            }
        }
    }else{
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



        }
    }

}