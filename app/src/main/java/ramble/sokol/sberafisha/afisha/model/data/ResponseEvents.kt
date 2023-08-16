package ramble.sokol.sberafisha.afisha.model.data

import com.google.gson.annotations.SerializedName

data class ResponseEvents(

    @SerializedName("id")
    val id: Long,

    @SerializedName("name")
    val name: String,

    @SerializedName("cover")
    val cover: String,

    @SerializedName("platform")
    val platform: ModelPlatform,

    @SerializedName("date")
    val startDate: String,

    @SerializedName("price")
    val price: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("pushkin_payment")
    val pushkin_payment: Boolean,

    @SerializedName("time")
    val time: String

    )
