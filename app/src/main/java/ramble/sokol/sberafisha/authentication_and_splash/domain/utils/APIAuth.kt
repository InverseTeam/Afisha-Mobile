package ramble.sokol.sberafisha.authentication_and_splash.domain.utils

import com.google.gson.JsonObject
import ramble.sokol.sberafisha.authentication_and_splash.domain.model.ResponseAuth
import ramble.sokol.sberafisha.profile.domain.models.ResponseUserInfo
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST

interface APIAuth {

    @POST("api/users/auth/token/login/")
    fun entryAndGetToken(@Body body: JsonObject): Call<ResponseAuth>

    @POST("api/users/auth/users/")
    fun createAccount(@Body body: JsonObject): Call<ResponseAuth>
}