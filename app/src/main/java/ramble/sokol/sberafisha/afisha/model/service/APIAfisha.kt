package ramble.sokol.sberafisha.afisha.model.service

import ramble.sokol.sberafisha.afisha.model.data.ResponseEvents
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface APIAfisha {

    // get request for all events
    @GET("api/events/")
    suspend fun getAllEvents(): Response<ArrayList<ResponseEvents>>

    // get events with filter
    @GET("api/events/filter/")
    fun getEventsFilter(@Query("date") date: String, @Query("category") category: Int?, @Header("Authorization") token: String): Call<ArrayList<ResponseEvents>>

    // get current event
    @GET("api/events/{id}/")
    fun getCurrentEvent(@Path("id") id: Int): Call<ResponseEvents>

    //put for plus pushkin
    @PUT("api/events/{id}/pushkin_wants/add/")
    fun putWantPushkin(@Path("id") id: Int, @Header("Authorization") token: String): Call<ResponseEvents>

    // put for add favorite event
    @PUT("api/events/favorites/add/{id}/")
    fun putFavoriteEvent(@Path("id") id: Int, @Header("Authorization") token: String): Call<ResponseEvents>

    // put for remove favorite event
    @PUT("api/events/favorites/add/{id}/")
    fun putRemoveFavoriteEvent(@Path("id") id: Int, @Header("Authorization") token: String): Call<ResponseEvents>
}