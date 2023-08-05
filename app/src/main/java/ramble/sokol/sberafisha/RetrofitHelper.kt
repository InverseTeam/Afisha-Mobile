package ramble.sokol.sberafisha

import ramble.sokol.sberafisha.authentication_and_splash.domain.utils.APIAuth
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    private const val BASE_URL = "https://inverse-tracker.store/"

    fun getInstance(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

}