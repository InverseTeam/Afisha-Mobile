package ramble.sokol.sberafisha.profile.view.screens

import DropDownLanguageProfile
import android.content.Context
import android.widget.Toast
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
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
import ramble.sokol.sberafisha.authentication_and_splash.view.components.ButtonForEntry
import ramble.sokol.sberafisha.authentication_and_splash.view.components.ButtonForEntryToRegistration
import ramble.sokol.sberafisha.destinations.BottomMenuScreenDestination
import ramble.sokol.sberafisha.destinations.EntryScreenDestination
import ramble.sokol.sberafisha.destinations.ProfileScreenDestination
import ramble.sokol.sberafisha.destinations.RegistrationScreenDestination
import ramble.sokol.sberafisha.model_project.FirstEntryManager
import ramble.sokol.sberafisha.model_project.RetrofitHelper
import ramble.sokol.sberafisha.model_project.TokenManager
import ramble.sokol.sberafisha.profile.domain.models.ResponseUserInfo
import ramble.sokol.sberafisha.profile.domain.utils.APIProfile
import ramble.sokol.sberafisha.profile.view.components.ButtonChangeProfile
import ramble.sokol.sberafisha.profile.view.components.ButtonExitProfile
import ramble.sokol.sberafisha.profile.view.components.ButtonForEntryProfile
import ramble.sokol.sberafisha.profile.view.components.TextInputAgeProfile
import ramble.sokol.sberafisha.profile.view.components.TextInputNameProfile
import ramble.sokol.sberafisha.profile.view.components.TextInputSurnameProfile
import ramble.sokol.sberafisha.ui.theme.ColorTextField
import ramble.sokol.sberafisha.ui.theme.ColorTextHint
import ramble.sokol.sberafisha.ui.theme.TextTitle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private lateinit var apiProfile: APIProfile
private lateinit var coroutineExceptionHandler: CoroutineExceptionHandler
private lateinit var tokenManager: TokenManager
private lateinit var firstEntryManager: FirstEntryManager
private lateinit var name: MutableState<String>
private lateinit var surname: MutableState<String>
private lateinit var age: MutableState<String>

@Destination
@Composable
fun ProfileScreen(
    navigator: DestinationsNavigator
){

    val mContext = LocalContext.current

    tokenManager = TokenManager(mContext)

    firstEntryManager = FirstEntryManager(mContext)

    val checkRegistration = firstEntryManager.getFirstTest()

    if (checkRegistration == false) {

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp, start = 33.dp, end = 33.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
            ) {

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.text_entry_account),
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.mont_bold)),
                    fontWeight = FontWeight(800),
                    color = TextTitle,
                    letterSpacing = 0.24.sp,
                    textAlign = TextAlign.Start
                )
            )

            Spacer(modifier = Modifier.padding(top = 8.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.text_profile_description),
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontFamily = FontFamily(Font(R.font.mont_semibold)),
                    fontWeight = FontWeight(700),
                    color = ColorTextHint,
                    textAlign = TextAlign.Start
                )
            )

            Spacer(modifier = Modifier.padding(top = 24.dp))

            ButtonForEntryProfile(text = stringResource(id = R.string.text_button_entry)) {
                navigator.navigate(EntryScreenDestination)
            }
            
            Spacer(modifier = Modifier.padding(top = 8.dp))

            ButtonForEntryToRegistration(text = stringResource(id = R.string.text_registration)) {
                navigator.navigate(RegistrationScreenDestination)
            }
        }

    }else {
        coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
        }

        apiProfile = RetrofitHelper.getInstance().create(APIProfile::class.java)

        name = remember {
            mutableStateOf("")
        }

        surname = remember {
            mutableStateOf("")
        }

        age = remember {
            mutableStateOf("")
        }

        var language by remember {
            mutableStateOf("Русский")
        }

        var expanded by remember { mutableStateOf(false)}

        getData(mContext)

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(top = 16.dp, start = 32.dp, end = 32.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.text_profile),
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.mont_bold)),
                    fontWeight = FontWeight(800),
                    color = TextTitle,
                    letterSpacing = 0.24.sp,
                    textAlign = TextAlign.Left
                )
            )

            Spacer(modifier = Modifier.padding(top = 24.dp))

            TextInputNameProfile(
                text = name.value,
                onValueChange = {
                    name.value = it
                },
                interactionSource = remember { MutableInteractionSource() }
                    .also { interactionSource ->
                        LaunchedEffect(interactionSource) {
                            interactionSource.interactions.collect {
                                if (it is PressInteraction.Release) {
                                    // click on textfield
                                }
                            }
                        }
                    }
            )

            Spacer(modifier = Modifier.padding(top = 8.dp))

            TextInputSurnameProfile(
                text = surname.value,
                onValueChange = {
                    surname.value = it
                },
                interactionSource = remember {
                    MutableInteractionSource()
                }
                    .also { interactionSource ->
                        LaunchedEffect(interactionSource) {
                            interactionSource.interactions.collect {
                                if (it is PressInteraction.Release) {
                                    // click on textfield
                                }
                            }
                        }
                    }
            )

            Spacer(modifier = Modifier.padding(top = 8.dp))

            TextInputAgeProfile(
                text = age.value,
                onValueChange = {
                    age.value = it
                },
                interactionSource = remember {
                    MutableInteractionSource()
                }
                    .also { interactionSource ->
                        LaunchedEffect(interactionSource) {
                            interactionSource.interactions.collect {
                                if (it is PressInteraction.Release) {
                                    // click on textfield
                                }
                            }
                        }
                    }
            )

            Spacer(modifier = Modifier.padding(top = 8.dp))

            // drop down
            DropDownLanguageProfile()

            Spacer(modifier = Modifier.padding(top = 8.dp))

            ButtonChangeProfile(
                text = stringResource(id = R.string.text_save)
            ) {
                patchData(mContext)
            }

            Spacer(modifier = Modifier.padding(top = 8.dp))

            ButtonExitProfile(
                text = stringResource(id = R.string.text_exit)
            ) {
                tokenManager.saveToken("")
                firstEntryManager.saveFirstEntry(false)
                firstEntryManager.saveFirstTest(false)
                navigator.popBackStack()
                navigator.navigate(BottomMenuScreenDestination)
            }

            Spacer(modifier = Modifier.padding(top = 32.dp))

        }
    }
}

private fun getData(context: Context){
    val call = apiProfile.getMyAccount("Token ${tokenManager.getToken()}")

    call.enqueue(object : Callback<ResponseUserInfo> {
        override fun onResponse(call: Call<ResponseUserInfo>, response: Response<ResponseUserInfo>) {
            if (response.isSuccessful) {
                val responseBody = response.body()
                age.value = responseBody!!.age
                if (age.value == null){
                    age.value = ""
                }
                name.value = responseBody.firstname
                surname.value = responseBody.lastname
            } else {
                Toast.makeText(context, R.string.text_appeared_error, Toast.LENGTH_SHORT).show()
            }
        }

        override fun onFailure(call: Call<ResponseUserInfo>, t: Throwable) {
            Toast.makeText(context, R.string.text_toast_no_internet, Toast.LENGTH_SHORT).show()
        }
    })

}

private fun patchData(context: Context){

    val body = JsonObject().apply {
        addProperty("firstname", name.value)
        addProperty("lastname", surname.value)
        addProperty("age", age.value)
    }

    val call = apiProfile.patchMyAccount("Token ${tokenManager.getToken()}", body)

    call.enqueue(object : Callback<ResponseUserInfo> {
        override fun onResponse(call: Call<ResponseUserInfo>, response: Response<ResponseUserInfo>) {
            if (response.isSuccessful) {
                Toast.makeText(context, R.string.text_data_upgrade, Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, R.string.text_appeared_error, Toast.LENGTH_SHORT).show()
            }
        }

        override fun onFailure(call: Call<ResponseUserInfo>, t: Throwable) {
            Toast.makeText(context, R.string.text_toast_no_internet, Toast.LENGTH_SHORT).show()
        }
    })

}