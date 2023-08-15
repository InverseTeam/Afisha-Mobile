package ramble.sokol.sberafisha.afisha.model.service

import ramble.sokol.sberafisha.afisha.model.data.AllEventsItem
import ramble.sokol.sberafisha.afisha.model.data.toAllEventsItem
import javax.inject.Inject

class AfishaRepository @Inject constructor(
    private val afishaService: AfishaService
){

    suspend fun getAllEvents(): List<AllEventsItem> {

        return afishaService.getAllEvents().map {
            it.toAllEventsItem()
        }
    }

}