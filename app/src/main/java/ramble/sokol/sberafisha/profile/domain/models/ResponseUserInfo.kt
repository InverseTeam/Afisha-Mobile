package ramble.sokol.sberafisha.profile.domain.models

import com.google.gson.annotations.SerializedName

data class ResponseUserInfo(

    @SerializedName("firstname")
    var firstname: String,

    @SerializedName("lastname")
    var lastname: String,

    @SerializedName("age")
    var age: String

)
