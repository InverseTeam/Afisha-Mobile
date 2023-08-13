package ramble.sokol.sberafisha.afisha.domain.models

import com.google.gson.annotations.SerializedName

data class ResponseEvents(

    @SerializedName("name")
    val name: String,

    @SerializedName("cover")
    val cover: String,

    @SerializedName("platform")
    val platform: ModelPlatform,

    @SerializedName("start_date")
    val startDate: String

    )
