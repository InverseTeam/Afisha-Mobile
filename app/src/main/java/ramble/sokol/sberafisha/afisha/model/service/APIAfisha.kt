package ramble.sokol.sberafisha.afisha.model.service

import ramble.sokol.sberafisha.afisha.model.data.ResponseEvents
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIAfisha {

    // get request for all events
    @GET("api/events/")
    suspend fun getAllEvents(): Response<ArrayList<ResponseEvents>>

    // get events with filter
    @GET("api/events/filter/")
    suspend fun getEventsFilter(@Query("date") date: String, @Query("category") category: Int): Call<ArrayList<ResponseEvents>>

    // get current event
    @GET("api/events/{id}/")
    fun getCurrentEvent(@Path("id") id: Int): Call<ResponseEvents>
}