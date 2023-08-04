package ramble.sokol.sberafisha.authentication_and_splash.domain.util

import com.google.gson.JsonObject
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface APIAuth {

    @POST("api/users/auth/token/login/")
    suspend fun entryAndGetToken(@Body body: JsonObject): Response<JsonObject>

}