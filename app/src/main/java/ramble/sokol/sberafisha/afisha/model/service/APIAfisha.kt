package ramble.sokol.sberafisha.afisha.model.service

import ramble.sokol.sberafisha.afisha.model.data.ResponseEvents
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface APIAfisha {

    // get request for all events
    @GET("api/events/")
    suspend fun getAllEvents(): Response<ArrayList<ResponseEvents>>
}