package ramble.sokol.sberafisha.profile.domain.utils

import com.google.gson.JsonObject
import ramble.sokol.sberafisha.afisha.model.data.ResponseEvents
import ramble.sokol.sberafisha.profile.domain.models.ResponseUserInfo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH

interface APIProfile {

    // patch for change user
    @PATCH("api/users/auth/users/me/")
    fun patchMyAccount(@Header("Authorization") token: String, @Body body: JsonObject): Call<ResponseUserInfo>

    // get for users info
    @GET("api/users/auth/users/me/")
    fun getMyAccount(@Header("Authorization") token: String): Call<ResponseUserInfo>

    @GET("api/events/favorites/")
    fun getFavorites(@Header("Authorization") token: String): Call<ArrayList<ResponseEvents>>
}