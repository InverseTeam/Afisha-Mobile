package ramble.sokol.sberafisha.afisha.view.screens

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.annotation.Destination
import ramble.sokol.sberafisha.R
import ramble.sokol.sberafisha.afisha.model.data.ResponseEvents
import ramble.sokol.sberafisha.afisha.model.service.APIAfisha
import ramble.sokol.sberafisha.destinations.AfishaScreenDestination
import ramble.sokol.sberafisha.destinations.BottomMenuScreenDestination
import ramble.sokol.sberafisha.destinations.WarningAuthScreenDestination
import ramble.sokol.sberafisha.model_project.FirstEntryManager
import ramble.sokol.sberafisha.model_project.RetrofitHelper
import ramble.sokol.sberafisha.model_project.TokenManager
import ramble.sokol.sberafisha.profile.domain.models.ResponseUserInfo
import ramble.sokol.sberafisha.profile.domain.utils.APIProfile
import ramble.sokol.sberafisha.profile.view.components.ButtonPushkin
import ramble.sokol.sberafisha.ui.theme.BackColumn
import ramble.sokol.sberafisha.ui.theme.ColorTextField
import ramble.sokol.sberafisha.ui.theme.ColorTextHint
import ramble.sokol.sberafisha.ui.theme.ColorTextSecond
import ramble.sokol.sberafisha.ui.theme.CurrentEventHint
import ramble.sokol.sberafisha.ui.theme.CurrentEventName
import ramble.sokol.sberafisha.ui.theme.TextTitle
import ramble.sokol.sberafisha.ui.theme.White
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private lateinit var apiAfisha: APIAfisha
private lateinit var cover: MutableState<String>
private lateinit var name: MutableState<String>
private lateinit var price: MutableState<String>
private lateinit var date: MutableState<String>
private lateinit var time: MutableState<String>
private lateinit var platform: MutableState<String>
private lateinit var description: MutableState<String>
private lateinit var pushkin: MutableState<Boolean>
private lateinit var firstEntryManager: FirstEntryManager
private lateinit var tokenManager: TokenManager

@Composable
@Destination
fun CurrentEventsScreen (
    navigator: DestinationsNavigator,
    id: Long
) {
    val mContext = LocalContext.current

    firstEntryManager = FirstEntryManager(context = mContext)

    tokenManager = TokenManager(context = mContext)

    apiAfisha = RetrofitHelper.getInstance().create(APIAfisha::class.java)

    cover = remember {
        mutableStateOf("")
    }

    name = remember {
        mutableStateOf("")
    }

    price = remember {
        mutableStateOf("")
    }

    date = remember {
        mutableStateOf("")
    }

    time = remember {
        mutableStateOf("")
    }

    description = remember {
        mutableStateOf("")
    }

    platform = remember {
        mutableStateOf("")
    }

    pushkin = remember {
        mutableStateOf(false)
    }

    getData(mContext, id.toInt())

    //Log.d("MyLog", id.toString())

    var clickFavorite by remember {
        mutableStateOf(false)
    }

    val iconFavorite = if (clickFavorite)
        painterResource(id = R.drawable.favorite_true)
    else
        painterResource(id = R.drawable.favorite_false)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackColumn) ,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = White,
                    shape = RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp)
                )
                .padding(bottom = 12.dp, top = 12.dp)
        ) {
            Box(
                modifier = Modifier.padding(start = 27.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Image(
                    modifier = Modifier
                        .padding(1.dp)
                        .width(28.dp)
                        .height(28.dp)
                        .clickable {
                            navigator.popBackStack()
                            navigator.navigate(BottomMenuScreenDestination())
                        },
                    painter = painterResource(id = R.drawable.image_button_back),
                    contentDescription = "back before start"
                )
            }
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.text_event),
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.mont_bold)),
                        fontWeight = FontWeight(800),
                        color = ColorTextField,
                        textAlign = TextAlign.Center,
                        letterSpacing = 0.2.sp,
                    )
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                contentAlignment = Alignment.TopEnd
            ){
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(16.dp)),
                    painter = rememberAsyncImagePainter("https://inverse-tracker.store/${cover.value}"),
                    contentDescription = "image current events",
                    contentScale = ContentScale.Crop
                )

                Column(modifier = Modifier.padding(end = 15.dp, top = 15.dp)) {
                    Image(
                        modifier = Modifier.clickable {
                            if (firstEntryManager.getFirstTest() == true){
                                if (clickFavorite){
                                    putRemoveFavorite(context = mContext, id = id.toInt())
                                }else{
                                    putFavorite(context = mContext, id = id.toInt())
                                }
                                clickFavorite = !clickFavorite
                            }else {
                                navigator.popBackStack()
                                navigator.navigate(WarningAuthScreenDestination)
                            }

                        },
                        painter = iconFavorite,
                        contentDescription = "icon favorite")
                }
            }

            Spacer(modifier = Modifier.padding(top = 16.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = name.value,
                    style = TextStyle(
                        fontSize = 20.sp,
                        lineHeight = 22.sp,
                        fontFamily = FontFamily(Font(R.font.mont_bold)),
                        fontWeight = FontWeight(800),
                        color = CurrentEventName,
                    )
                )

                Text(
                    text = "${price.value}₽",
                    style = TextStyle(
                        fontSize = 20.sp,
                        lineHeight = 22.sp,
                        fontFamily = FontFamily(Font(R.font.mont_bold)),
                        fontWeight = FontWeight(800),
                        color = ColorTextSecond,
                        textAlign = TextAlign.Right,
                    )
                )
            }

            Spacer(modifier = Modifier.padding(top = 4.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "${date.value}, ${time.value} · ${platform.value}",
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 17.6.sp,
                    fontFamily = FontFamily(Font(R.font.mont_semibold)),
                    fontWeight = FontWeight(700),
                    color = CurrentEventHint,
                    textAlign = TextAlign.Start
                )
            )

            Spacer(modifier = Modifier.padding(top = 32.dp))

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.text_about_events),
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.mont_bold)),
                    fontWeight = FontWeight(800),
                    color = TextTitle,
                    letterSpacing = 0.18.sp,
                    textAlign = TextAlign.Start
                )
            )

            Spacer(modifier = Modifier.padding(top = 4.dp))

            Text(
                text = description.value,
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontFamily = FontFamily(Font(R.font.mont_semibold)),
                    fontWeight = FontWeight(700),
                    color = ColorTextHint,
                )
            )

            Spacer(modifier = Modifier.padding(top = 24.dp))

            if (pushkin.value) {
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painterResource(id = R.drawable.image_with_pushkin),
                    contentDescription = "with pushkin"
                )
            }else{

                var enabled by remember {
                    mutableStateOf(true)
                }

                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painterResource(id = R.drawable.image_without_pushkin),
                    contentDescription = "without pushkin"
                )

                Spacer(modifier = Modifier.padding(top = 8.dp))

                ButtonPushkin(text = stringResource(id = R.string.text_pushkin), enabled = enabled) {
                    if (firstEntryManager.getFirstTest() == true){
                        putPushkin(mContext, id.toInt())
                        enabled = false
                    }else{
                        navigator.popBackStack()
                        navigator.navigate(WarningAuthScreenDestination)
                    }
                }
            }
            
            Spacer(modifier = Modifier.padding(top = 15.dp))

        }
    }

    BackHandler {
        navigator.popBackStack()
        navigator.navigate(BottomMenuScreenDestination)
    }
}

private fun getData(context: Context, id: Int){
    val call = apiAfisha.getCurrentEvent(id)

    call.enqueue(object : Callback<ResponseEvents> {
        override fun onResponse(call: Call<ResponseEvents>, response: Response<ResponseEvents>) {
            if (response.isSuccessful) {
                val responseBody = response.body()
                cover.value = responseBody!!.cover
                name.value = responseBody.name
                price.value = responseBody.price
                time.value = responseBody.time
                date.value = responseBody.startDate
                description.value = responseBody.description
                platform.value = responseBody.platform.name
                pushkin.value = responseBody.pushkin_payment
            } else {
                Toast.makeText(context, R.string.text_appeared_error, Toast.LENGTH_SHORT).show()
            }
        }

        override fun onFailure(call: Call<ResponseEvents>, t: Throwable) {
            Toast.makeText(context, R.string.text_toast_no_internet, Toast.LENGTH_SHORT).show()
        }
    })

}

private fun putPushkin(context: Context, id: Int){

    val call = apiAfisha.putWantPushkin(id, "Token ${tokenManager.getToken()!!}")

    call.enqueue(object : Callback<ResponseEvents> {
        override fun onResponse(call: Call<ResponseEvents>, response: Response<ResponseEvents>) {
            if (response.isSuccessful) {
                Toast.makeText(context, R.string.text_voice_count, Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(context, R.string.text_appeared_error, Toast.LENGTH_SHORT).show()
            }
        }

        override fun onFailure(call: Call<ResponseEvents>, t: Throwable) {
            Toast.makeText(context, R.string.text_toast_no_internet, Toast.LENGTH_SHORT).show()
        }
    })

}

private fun putFavorite(context: Context, id: Int){

    val call = apiAfisha.putFavoriteEvent(id, "Token ${tokenManager.getToken()!!}")

    call.enqueue(object : Callback<ResponseEvents> {
        override fun onResponse(call: Call<ResponseEvents>, response: Response<ResponseEvents>) {
            if (response.isSuccessful) {
                Toast.makeText(context, R.string.text_add_to_favorite, Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(context, R.string.text_appeared_error, Toast.LENGTH_SHORT).show()
            }
        }

        override fun onFailure(call: Call<ResponseEvents>, t: Throwable) {
            Toast.makeText(context, R.string.text_toast_no_internet, Toast.LENGTH_SHORT).show()
        }
    })

}

private fun putRemoveFavorite(context: Context, id: Int){

    val call = apiAfisha.putRemoveFavoriteEvent(id, "Token ${tokenManager.getToken()!!}")

    call.enqueue(object : Callback<ResponseEvents> {
        override fun onResponse(call: Call<ResponseEvents>, response: Response<ResponseEvents>) {
            if (response.isSuccessful) {
                Toast.makeText(context, R.string.text_remove_to_favorite, Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(context, R.string.text_appeared_error, Toast.LENGTH_SHORT).show()
            }
        }

        override fun onFailure(call: Call<ResponseEvents>, t: Throwable) {
            Toast.makeText(context, R.string.text_toast_no_internet, Toast.LENGTH_SHORT).show()
        }
    })

}