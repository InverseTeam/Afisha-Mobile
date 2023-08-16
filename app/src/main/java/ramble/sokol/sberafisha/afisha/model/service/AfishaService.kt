package ramble.sokol.sberafisha.afisha.model.service

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ramble.sokol.sberafisha.afisha.model.data.ResponseEvents
import javax.inject.Inject

class AfishaService @Inject constructor(
    private val apiAfisha: APIAfisha
){

    // request to receive all events
    suspend fun getAllEvents(): List<ResponseEvents>{
        return withContext(Dispatchers.IO){
            val events = apiAfisha.getAllEvents()
            events.body() ?: emptyList()
        }
    }
}