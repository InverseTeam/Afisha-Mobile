package ramble.sokol.sberafisha.profile.domain.utils

import com.google.gson.JsonObject
import ramble.sokol.sberafisha.profile.domain.models.ResponseUserInfo
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH

interface APIProfile {

    @PATCH("api/users/auth/users/me/")
    fun patchMyAccount(@Header("Authorization") token: String, @Body body: JsonObject): Call<ResponseUserInfo>

    @GET("api/users/auth/users/me/")
    fun getMyAccount(@Header("Authorization") token: String): Call<ResponseUserInfo>
}