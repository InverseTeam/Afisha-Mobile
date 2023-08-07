package ramble.sokol.sberafisha.authentication_and_splash.view.screens

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.google.gson.JsonObject
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.CoroutineExceptionHandler
import ramble.sokol.sberafisha.R
import ramble.sokol.sberafisha.authentication_and_splash.domain.model.ResponseAuth
import ramble.sokol.sberafisha.authentication_and_splash.domain.utils.APIAuth
import ramble.sokol.sberafisha.authentication_and_splash.view.components.ButtonForEntry
import ramble.sokol.sberafisha.authentication_and_splash.view.components.ButtonForEntrySber
import ramble.sokol.sberafisha.authentication_and_splash.view.components.ButtonForEntryToRegistration
import ramble.sokol.sberafisha.authentication_and_splash.view.components.TextInputEmailEntry
import ramble.sokol.sberafisha.authentication_and_splash.view.components.TextInputPasswordEntry
import ramble.sokol.sberafisha.destinations.BottomMenuScreenDestination
import ramble.sokol.sberafisha.destinations.EntryScreenDestination
import ramble.sokol.sberafisha.model_request.RetrofitHelper
import ramble.sokol.sberafisha.model_request.TokenManager
import ramble.sokol.sberafisha.ui.theme.ColorActionText
import ramble.sokol.sberafisha.ui.theme.ColorText
import ramble.sokol.sberafisha.ui.theme.Error
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private lateinit var apiAuth: APIAuth
private lateinit var coroutineExceptionHandler: CoroutineExceptionHandler
private lateinit var tokenManager: TokenManager
private lateinit var incorrectData: MutableState<Boolean>

@Destination
@Composable
fun RegistrationScreen(
    navigator: DestinationsNavigator
) {

    val mContext = LocalContext.current

    tokenManager = TokenManager(mContext)

    coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
        throwable.printStackTrace()
    }

    apiAuth = RetrofitHelper.getInstance().create(APIAuth::class.java)

    incorrectData = remember {
        mutableStateOf(false)
    }

    var emailError by remember {
        mutableStateOf(false)
    }

    var emailIncorrect by remember {
        mutableStateOf(false)
    }

    var passwordError by remember {
        mutableStateOf(false)
    }

    var passwordLength by remember {
        mutableStateOf(false)
    }

    var passwordConfirmationError by remember {
        mutableStateOf(false)
    }

    var similarPassword by remember {
        mutableStateOf(false)
    }

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var passwordConfirmation by remember {
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
                emailError = false
                passwordError = false
                passwordConfirmationError = false
                emailIncorrect = false
                similarPassword = false
                passwordLength = false
                incorrectData.value = false
                email = it
            },
            borderWidth = if (emailError) 1 else 0,
            color = if (emailError) Error else Color.Transparent,
            interactionSource = remember { MutableInteractionSource() }
                .also { interactionSource ->
                    LaunchedEffect(interactionSource) {
                        interactionSource.interactions.collect {
                            if (it is PressInteraction.Release) {
                                emailError = false
                                passwordError = false
                                passwordConfirmationError = false
                                emailIncorrect = false
                                similarPassword = false
                                passwordLength = false
                                incorrectData.value = false
                            }
                        }
                    }
                }
        )

        if (incorrectData.value){
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, top = 8.dp)){

                Text(
                    text = stringResource(id = R.string.text_email_exists),
                    style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 24.sp,
                        fontFamily = FontFamily(Font(R.font.mont_semibold)),
                        fontWeight = FontWeight(400),
                        color = Error,
                        textAlign = TextAlign.Center,
                    )
                )
            }
        }

        if (emailIncorrect){
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, top = 8.dp)){

                Text(
                    text = stringResource(id = R.string.text_incorrect_email),
                    style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 24.sp,
                        fontFamily = FontFamily(Font(R.font.mont_semibold)),
                        fontWeight = FontWeight(400),
                        color = Error,
                        textAlign = TextAlign.Center,
                    )
                )
            }
        }

        Spacer(modifier = Modifier.padding(top = 8.dp))

        TextInputPasswordEntry(
            text = password,
            onValueChange = {
                emailError = false
                passwordError = false
                passwordConfirmationError = false
                emailIncorrect = false
                similarPassword = false
                passwordLength = false
                incorrectData.value = false
                password = it
            },
            borderWidth = if (passwordError) 1 else 0,
            color = if (passwordError) Error else Color.Transparent,
            interactionSource = remember { MutableInteractionSource() }
                .also { interactionSource ->
                    LaunchedEffect(interactionSource) {
                        interactionSource.interactions.collect {
                            if (it is PressInteraction.Release) {
                                emailError = false
                                passwordError = false
                                passwordConfirmationError = false
                                emailIncorrect = false
                                similarPassword = false
                                passwordLength = false
                                incorrectData.value = false
                            }
                        }
                    }
                }
        )

        if (passwordLength){
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, top = 8.dp)){

                Text(
                    text = stringResource(id = R.string.text_password_length),
                    style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 24.sp,
                        fontFamily = FontFamily(Font(R.font.mont_semibold)),
                        fontWeight = FontWeight(400),
                        color = Error,
                        textAlign = TextAlign.Center,
                    )
                )
            }
        }

        Spacer(modifier = Modifier.padding(top = 8.dp))

        TextInputPasswordEntry(
            text = passwordConfirmation,
            onValueChange = {
                emailError = false
                passwordError = false
                passwordConfirmationError = false
                emailIncorrect = false
                similarPassword = false
                passwordLength = false
                incorrectData.value = false
                passwordConfirmation = it
            },
            borderWidth = if (passwordConfirmationError) 1 else 0,
            color = if (passwordConfirmationError) Error else Color.Transparent,
            interactionSource = remember { MutableInteractionSource() }
                .also { interactionSource ->
                    LaunchedEffect(interactionSource) {
                        interactionSource.interactions.collect {
                            if (it is PressInteraction.Release) {
                                emailError = false
                                passwordError = false
                                passwordConfirmationError = false
                                emailIncorrect = false
                                similarPassword = false
                                passwordLength = false
                                incorrectData.value = false
                            }
                        }
                    }
                }
        )

        if (similarPassword){
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, top = 8.dp)){

                Text(
                    text = stringResource(id = R.string.text_similar_password),
                    style = TextStyle(
                        fontSize = 12.sp,
                        lineHeight = 24.sp,
                        fontFamily = FontFamily(Font(R.font.mont_semibold)),
                        fontWeight = FontWeight(400),
                        color = Error,
                        textAlign = TextAlign.Center,
                    )
                )
            }
        }

        Spacer(modifier = Modifier.padding(top = 8.dp))

        ButtonForEntry(text = stringResource(id = R.string.text_register)){
            if (password.isEmpty()) passwordError = true
            if (email.isEmpty()) emailError = true
            if (passwordConfirmation.isEmpty()) passwordConfirmationError = true
            if (!isValidEmail(email) && !email.isEmpty()) emailIncorrect = true
            if (password != passwordConfirmation) similarPassword = true
            if (password.length < 8 && !password.isEmpty()) passwordLength = true
            if (!email.isEmpty() && !password.isEmpty() && !passwordConfirmation.isEmpty() &&
                isValidEmail(email) && password == passwordConfirmation && password.length >= 8) {
                registration(mContext, navigator, email, password)
            }

        }

        Spacer(modifier = Modifier.padding(top = 153.dp))

        ButtonForEntryToRegistration(text = stringResource(id = R.string.text_entrance)) {
            navigator.popBackStack()
            navigator.navigate(EntryScreenDestination)
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

private fun isValidEmail(email: String): Boolean {
    val emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
    return email.matches(emailRegex)
}

fun registration(context: Context, navigator: DestinationsNavigator, email: String, password: String){
    val body = JsonObject().apply {
        addProperty("email", email)
        addProperty("password", password)
    }
    val call = apiAuth.createAccount(body)

    call.enqueue(object : Callback<ResponseAuth> {
        override fun onResponse(call: Call<ResponseAuth>, response: Response<ResponseAuth>) {
            if (response.isSuccessful) {
                getToken(context, email, password)
                Toast.makeText(context, R.string.text_successful_registration, Toast.LENGTH_SHORT).show()
                navigator.popBackStack()
                navigator.navigate(BottomMenuScreenDestination)
            } else {
                incorrectData.value = true
            }
        }

        override fun onFailure(call: Call<ResponseAuth>, t: Throwable) {
            Toast.makeText(context, R.string.text_toast_no_internet, Toast.LENGTH_SHORT).show()
        }
    })
}

private fun getToken(context: Context, email: String, password: String){
    val body = JsonObject().apply {
        addProperty("email", email)
        addProperty("password", password)
    }
    val call = apiAuth.entryAndGetToken(body)

    call.enqueue(object : Callback<ResponseAuth> {
        override fun onResponse(call: Call<ResponseAuth>, response: Response<ResponseAuth>) {
            if (response.isSuccessful) {
                val responseBody = response.body()
                tokenManager.saveToken(responseBody!!.authToken)
            } else {
                Toast.makeText(context, R.string.text_appeared_error, Toast.LENGTH_SHORT).show()
            }
        }

        override fun onFailure(call: Call<ResponseAuth>, t: Throwable) {
            Toast.makeText(context, R.string.text_toast_no_internet, Toast.LENGTH_SHORT).show()
        }
    })

}