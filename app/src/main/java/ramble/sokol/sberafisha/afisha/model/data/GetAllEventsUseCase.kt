package ramble.sokol.sberafisha.afisha.model.data

import ramble.sokol.sberafisha.afisha.model.service.AfishaRepository
import javax.inject.Inject

class GetAllEventsUseCase @Inject constructor(
    private val afishaRepository: AfishaRepository
) {

    suspend operator fun invoke(): List<AllEventsItem> {

        return afishaRepository.getAllEvents().shuffled()

    }

}