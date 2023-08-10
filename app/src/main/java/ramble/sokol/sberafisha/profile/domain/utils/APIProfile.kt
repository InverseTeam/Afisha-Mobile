package ramble.sokol.sberafisha.profile.domain.utils

import ramble.sokol.sberafisha.profile.domain.models.ResponseUserInfo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface APIProfile {

    //@PATCH("api/users/auth/users/me/")

    @GET("api/users/auth/users/me/")
    fun getMyAccount(@Header("Authorization") token: String): Call<ResponseUserInfo>
}