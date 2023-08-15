package ramble.sokol.sberafisha.afisha.model.data

data class AllEventsItem(
    val id: Long,
    val name: String,
    val cover: String,
    val platform: ModelPlatform,
    val startDate: String
)

fun ResponseEvents.toAllEventsItem() = AllEventsItem(id, name, cover, platform, startDate)