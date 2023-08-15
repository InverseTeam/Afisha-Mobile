package ramble.sokol.sberafisha.model_project

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ramble.sokol.sberafisha.afisha.model.service.APIAfisha
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RetrofitHelper {

    // basic link to api
    private const val BASE_URL = "https://inverse-tracker.store/"


    @Singleton
    @Provides
    fun getInstance(): Retrofit{

        // create Retrofit
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    @Singleton
    @Provides
    fun provideApiAfisha(retrofit: Retrofit): APIAfisha {

        // create object APIAfisha
        return retrofit.create(APIAfisha::class.java)
    }

}