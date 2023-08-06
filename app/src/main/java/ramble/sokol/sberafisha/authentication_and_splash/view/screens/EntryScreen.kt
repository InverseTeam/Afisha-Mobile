package ramble.sokol.sberafisha.authentication_and_splash.view.screens

import android.content.Context
import android.os.Looper
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.lifecycleScope
import com.google.gson.JsonObject
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ramble.sokol.sberafisha.R
import ramble.sokol.sberafisha.RetrofitHelper
import ramble.sokol.sberafisha.authentication_and_splash.domain.model.ResponseAuth
import ramble.sokol.sberafisha.authentication_and_splash.domain.utils.APIAuth
import ramble.sokol.sberafisha.authentication_and_splash.view.components.ButtonForEntry
import ramble.sokol.sberafisha.authentication_and_splash.view.components.ButtonForEntrySber
import ramble.sokol.sberafisha.authentication_and_splash.view.components.ButtonForEntryToRegistration
import ramble.sokol.sberafisha.authentication_and_splash.view.components.TextInputEmailEntry
import ramble.sokol.sberafisha.authentication_and_splash.view.components.TextInputPasswordEntry
import ramble.sokol.sberafisha.destinations.BottomMenuScreenDestination
import ramble.sokol.sberafisha.destinations.RegistrationScreenDestination
import ramble.sokol.sberafisha.ui.theme.ColorActionText
import ramble.sokol.sberafisha.ui.theme.ColorText
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Timer
import java.util.logging.Handler
import kotlin.concurrent.timerTask


lateinit var apiAuth: APIAuth
private lateinit var coroutineExceptionHandler: CoroutineExceptionHandler
private val scope = CoroutineScope(Dispatchers.Default)

@Destination
@Composable
fun EntryScreen(
    navigator: DestinationsNavigator
){

    val mContext = LocalContext.current

     coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
        throwable.printStackTrace()
    }

    apiAuth = RetrofitHelper.getInstance().create(APIAuth::class.java)

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier
                .width(167.dp)
                .height(24.dp),
            painter = painterResource(id = R.drawable.image_text_auth),
            contentDescription = "image_text_for_auth"
        )
        
        Spacer(modifier = Modifier.padding(top = 16.dp))

        TextInputEmailEntry(
            text = email,
            onValueChange = {
                email = it
            }
        )

        Spacer(modifier = Modifier.padding(top = 8.dp))

        TextInputPasswordEntry(
            text = password,
            onValueChange = {
                password = it
            }
        )

        Spacer(modifier = Modifier.padding(top = 8.dp))
        
        ButtonForEntry(text = stringResource(id = R.string.text_button_entry)){
            entry(mContext, navigator, email, password)
        }

        Spacer(modifier = Modifier.padding(top = 8.dp))

        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
            ){
            
            Text(
                text = stringResource(id = R.string.text_forget_the_password),
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 24.sp,
                    fontFamily = FontFamily(Font(R.font.mont_semibold)),
                    fontWeight = FontWeight(700),
                    color = ColorText,
                    textAlign = TextAlign.Center
                )
            )

            Spacer(modifier = Modifier.padding(2.dp))

            Text(
                modifier = Modifier.clickable {
                    // click
                },
                text = stringResource(id = R.string.text_recover),
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 24.sp,
                    fontFamily = FontFamily(Font(R.font.mont_semibold)),
                    fontWeight = FontWeight(700),
                    color = ColorActionText,
                    textAlign = TextAlign.Center
                )
            )

        }

        Spacer(modifier = Modifier.padding(top = 6.dp))
        
        ButtonForEntrySber(text = stringResource(id = R.string.text_button_entry_sberid)) {
            // click
        }

        Spacer(modifier = Modifier.padding(top = 123.dp))

        ButtonForEntryToRegistration(text = stringResource(id = R.string.text_registration)) {
            navigator.popBackStack()
            navigator.navigate(RegistrationScreenDestination)
        }
        
        Spacer(modifier = Modifier.padding(top = 25.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
            ){
            Text(
                text = stringResource(id = R.string.text_start_agreement),
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 24.sp,
                    fontFamily = FontFamily(Font(R.font.mont_semibold)),
                    fontWeight = FontWeight(700),
                    color = ColorText,
                    textAlign = TextAlign.Center
                )
            )

            Spacer(modifier = Modifier.padding(2.dp))

            Text(
                modifier = Modifier.clickable {
                    // click
                },
                text = stringResource(id = R.string.text_end_agreement),
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 24.sp,
                    fontFamily = FontFamily(Font(R.font.mont_semibold)),
                    fontWeight = FontWeight(700),
                    color = ColorActionText,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}

fun entry(context: Context, navigator: DestinationsNavigator, email: String, password: String){
    val body = JsonObject().apply {
        addProperty("email", email)
        addProperty("password", password)
    }
    val call = apiAuth.entryAndGetToken(body)

    call.enqueue(object : Callback<ResponseAuth> {
        override fun onResponse(call: Call<ResponseAuth>, response: Response<ResponseAuth>) {
            if (response.isSuccessful) {
                val responseBody = response.body()
                Log.d("MyLog", responseBody!!.authToken)
                Toast.makeText(context, "Вы успешно вошли", Toast.LENGTH_SHORT).show()
                navigator.popBackStack()
                navigator.navigate(BottomMenuScreenDestination)
            } else {
                Log.d("MyLog", "not successful ${response.toString()}")
            }
        }

        override fun onFailure(call: Call<ResponseAuth>, t: Throwable) {
            Log.d("MyLog", t.message.toString())
        }
    })

}

//suspend fun entry(context: Context, email: String, password: String, navigator: DestinationsNavigator): Boolean {
//    var res = false
//    GlobalScope.async(Dispatchers.Default) {
//        val body = JsonObject().apply {
//            addProperty("email", email)
//            addProperty("password", password)
//        }
//        val result = apiAuth.entryAndGetToken(body)
//        if (result.isSuccessful){
//            Log.d("MyLog", result.body().toString())
//            res = true
//        }else{
//            Log.d("MyLog", result.message())
//        }
//    }.await()
//    Log.d("MyLOg", res.toString())
//    return res
//}
