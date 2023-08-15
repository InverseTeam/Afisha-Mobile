package ramble.sokol.sberafisha.authentication_and_splash.domain.model

import com.google.gson.annotations.SerializedName

data class ResponseAuth(

    @SerializedName("auth_token")
    var authToken: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("id")
    val id: Int

)
