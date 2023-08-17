package ramble.sokol.sberafisha.authentication_and_splash.view.screens

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import ramble.sokol.sberafisha.authentication_and_splash.view.components.ButtonForEntryToRegistration
import ramble.sokol.sberafisha.authentication_and_splash.view.components.ProgressBarAuth
import ramble.sokol.sberafisha.authentication_and_splash.view.components.TextInputAgeEntry
import ramble.sokol.sberafisha.authentication_and_splash.view.components.TextInputEmailEntry
import ramble.sokol.sberafisha.authentication_and_splash.view.components.TextInputNameEntry
import ramble.sokol.sberafisha.authentication_and_splash.view.components.TextInputPasswordEntry
import ramble.sokol.sberafisha.authentication_and_splash.view.components.TextInputSurnameEntry
import ramble.sokol.sberafisha.destinations.EntryScreenDestination
import ramble.sokol.sberafisha.destinations.StartTestScreenDestination
import ramble.sokol.sberafisha.model_project.FirstEntryManager
import ramble.sokol.sberafisha.model_project.RetrofitHelper
import ramble.sokol.sberafisha.model_project.RoleManager
import ramble.sokol.sberafisha.model_project.TokenManager
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
private lateinit var firstEntryManager: FirstEntryManager
private lateinit var roleManager: RoleManager
private lateinit var progressEntryState: MutableState<Boolean>
private lateinit var roleMain: MutableState<Int>

@Destination
@Composable
fun RegistrationScreen(
    navigator: DestinationsNavigator
) {

    val mContext = LocalContext.current

    tokenManager = TokenManager(mContext)

    firstEntryManager = FirstEntryManager(mContext)

    roleManager = RoleManager(mContext)

    coroutineExceptionHandler = CoroutineExceptionHandler{_, throwable ->
        throwable.printStackTrace()
    }

    apiAuth = RetrofitHelper.getInstance().create(APIAuth::class.java)

    incorrectData = remember {
        mutableStateOf(false)
    }

    progressEntryState = remember {
        mutableStateOf(false)
    }

    roleMain = remember {
        mutableIntStateOf(0)
    }

    var emailError by remember {
        mutableStateOf(false)
    }

    val emailIncorrect = remember {
        mutableStateOf(false)
    }

    val nameError = remember {
        mutableStateOf(false)
    }

    val surnameError = remember {
        mutableStateOf(false)
    }

    val ageError = remember {
        mutableStateOf(false)
    }

    var ageErrorIncorrect by remember {
        mutableStateOf(false)
    }

    var passwordError by remember {
        mutableStateOf(false)
    }

    var passwordLength by remember {
        mutableStateOf(false)
    }

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var name by remember {
        mutableStateOf("")
    }

    var surname by remember {
        mutableStateOf("")
    }

    var age by remember {
        mutableStateOf("")
    }

    var passwordVis by remember {
        mutableStateOf(false)
    }

    val icon = if (passwordVis) 
        painterResource(id = R.drawable.password_true)
    else 
        painterResource(id = R.drawable.password_false)

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
                passwordLength = false
                nameError.value = false
                surnameError.value = false
                ageError.value = false
                incorrectData.value = false
                ageErrorIncorrect = false
                emailIncorrect.value = false
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
                                passwordLength = false
                                nameError.value = false
                                surnameError.value = false
                                ageError.value = false
                                ageErrorIncorrect = false
                                incorrectData.value = false
                                emailIncorrect.value = false
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

        if (emailIncorrect.value){
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
                passwordLength = false
                incorrectData.value = false
                nameError.value = false
                surnameError.value = false
                ageError.value = false
                ageErrorIncorrect = false
                ageErrorIncorrect = false
                emailIncorrect.value = false
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
                                passwordLength = false
                                nameError.value = false
                                surnameError.value = false
                                ageError.value = false
                                incorrectData.value = false
                                ageErrorIncorrect = false
                                emailIncorrect.value = false
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

        TextInputNameEntry(
            text = name,
            onValueChange = {
                emailError = false
                passwordError = false
                passwordLength = false
                incorrectData.value = false
                nameError.value = false
                surnameError.value = false
                ageError.value = false
                ageErrorIncorrect = false
                emailIncorrect.value = false
                name = it
            },
            borderWidth = if (nameError.value) 1 else 0,
            color = if (nameError.value) Error else Color.Transparent,
            interactionSource = remember { MutableInteractionSource() }
                .also { interactionSource ->
                    LaunchedEffect(interactionSource) {
                        interactionSource.interactions.collect {
                            if (it is PressInteraction.Release) {
                                emailError = false
                                passwordError = false
                                passwordLength = false
                                incorrectData.value = false
                                nameError.value = false
                                surnameError.value = false
                                ageError.value = false
                                ageErrorIncorrect = false
                                emailIncorrect.value = false
                            }
                        }
                    }
                }
        )

        Spacer(modifier = Modifier.padding(top = 8.dp))

        TextInputSurnameEntry(
            text = surname,
            onValueChange = {
                emailError = false
                passwordError = false
                passwordLength = false
                incorrectData.value = false
                nameError.value = false
                surnameError.value = false
                ageError.value = false
                ageErrorIncorrect = false
                emailIncorrect.value = false
                surname = it
            },
            borderWidth = if (surnameError.value) 1 else 0,
            color = if (surnameError.value) Error else Color.Transparent,
            interactionSource = remember { MutableInteractionSource() }
                .also { interactionSource ->
                    LaunchedEffect(interactionSource) {
                        interactionSource.interactions.collect {
                            if (it is PressInteraction.Release) {
                                emailError = false
                                passwordError = false
                                passwordLength = false
                                nameError.value = false
                                surnameError.value = false
                                ageError.value = false
                                incorrectData.value = false
                                ageErrorIncorrect = false
                                emailIncorrect.value = false
                            }
                        }
                    }
                }
        )

        Spacer(modifier = Modifier.padding(top = 8.dp))

        TextInputAgeEntry(
            text = age,
            onValueChange = {
                emailError = false
                passwordError = false
                passwordLength = false
                incorrectData.value = false
                nameError.value = false
                surnameError.value = false
                ageError.value = false
                ageErrorIncorrect = false
                emailIncorrect.value = false
                age = it
            },
            borderWidth = if (ageError.value) 1 else 0,
            color = if (ageError.value) Error else Color.Transparent,
            interactionSource = remember { MutableInteractionSource() }
                .also { interactionSource ->
                    LaunchedEffect(interactionSource) {
                        interactionSource.interactions.collect {
                            if (it is PressInteraction.Release) {
                                emailError = false
                                passwordError = false
                                passwordLength = false
                                nameError.value = false
                                surnameError.value = false
                                ageError.value = false
                                ageErrorIncorrect = false
                                incorrectData.value = false
                                emailIncorrect.value = false
                            }
                        }
                    }
                }
        )

        if (ageErrorIncorrect){
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 4.dp, top = 8.dp)){

                Text(
                    text = stringResource(id = R.string.text_incorrect_age),
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

        if (progressEntryState.value){
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ){
                ProgressBarAuth()
            }
        }else {
            ButtonForEntry(text = stringResource(id = R.string.text_register)) {
                if (password.isEmpty()) passwordError = true
                if (email.isEmpty()) emailError = true
                if (name.isEmpty()) nameError.value = true
                if (surname.isEmpty()) surnameError.value = true
                if (age.isEmpty()) ageError.value = true
                if (!age.isEmpty() && (age.toInt() > 100 || age.toInt() < 10)) ageErrorIncorrect = true
                if (!isValidEmail(email) && !email.isEmpty()) emailIncorrect.value = true
                if (password.length < 8 && password.isNotEmpty()) passwordLength = true
                if (email.isNotEmpty() && password.isNotEmpty() && password.length >= 8
                    && isValidEmail(email) && name.isNotEmpty() && surname.isNotEmpty()
                    && age.isNotEmpty() && (age.toInt() in 10..100)
                ) {
                    progressEntryState.value = true
                    Log.d("MyLog", "Click")
                    registration(mContext, navigator, email, password, name, surname, age)
                }

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
                    Toast.makeText(mContext, R.string.text_into_developing, Toast.LENGTH_SHORT).show()
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

    BackHandler {
        navigator.popBackStack()
        navigator.navigate(EntryScreenDestination)
    }

}

private fun isValidEmail(email: String): Boolean {
    val emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
    return email.matches(emailRegex)
}

//private fun getRoles(context: Context){
//    val call = apiAuth.getAllRoles()
//    call.enqueue(object : Callback<List<ResponseAuth>> {
//        override fun onResponse(call: Call<List<ResponseAuth>>, response: Response<List<ResponseAuth>>) {
//            if (response.isSuccessful) {
//                val responseBody = response.body()
//                if (responseBody != null) {
//                    for (role in responseBody){
//                        if (role.name == "Пользователь"){
//                            roleMain.value = role.id + 1
//                            Log.d("MyLog", roleMain.value.toString())
//                        }
//                    }
//
//                }
//            } else {
//                Toast.makeText(context, R.string.text_appeared_error, Toast.LENGTH_SHORT).show()
//                Log.d("MyLog", response.message())
//            }
//        }
//
//        override fun onFailure(call: Call<List<ResponseAuth>>, t: Throwable) {
//            Toast.makeText(context, R.string.text_toast_no_internet, Toast.LENGTH_SHORT).show()
//            progressEntryState.value = false
//        }
//    })
//}

fun registration(
    context: Context, navigator: DestinationsNavigator, email: String, password: String,
    name: String, surname: String, age: String
){
    //getRoles(context)
    //Log.d("MyLog", roleMain.toString())
    val body = JsonObject().apply {
        addProperty("email", email)
        addProperty("password", password)
        addProperty("firstname", name)
        addProperty("lastname", surname)
        addProperty("age", age)
        addProperty("role", 1)
    }
    val call = apiAuth.createAccount(body)

    call.enqueue(object : Callback<ResponseAuth> {
        override fun onResponse(call: Call<ResponseAuth>, response: Response<ResponseAuth>) {
            if (response.isSuccessful) {
                getToken(context, email, password)
                firstEntryManager.saveFirstEntry(true)
                progressEntryState.value = false
                Toast.makeText(context, R.string.text_successful_registration, Toast.LENGTH_SHORT).show()
                navigator.popBackStack()
                navigator.navigate(StartTestScreenDestination)
            } else {
                Log.d("MyLog", response.toString())
                incorrectData.value = true
                progressEntryState.value = false
            }
        }

        override fun onFailure(call: Call<ResponseAuth>, t: Throwable) {
            Toast.makeText(context, R.string.text_toast_no_internet, Toast.LENGTH_SHORT).show()
            progressEntryState.value = false
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
                progressEntryState.value = false
            }
        }

        override fun onFailure(call: Call<ResponseAuth>, t: Throwable) {
            Toast.makeText(context, R.string.text_toast_no_internet, Toast.LENGTH_SHORT).show()
            progressEntryState.value = false
        }
    })

}