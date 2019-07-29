package com.apps.ahfreelancing.marvelapp.data.provider

import com.apps.ahfreelancing.marvelapp.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Ahmed Hassan on 7/26/2019.
 */

//Singleton Provider of retrofit
object RetrofitProvider {
    private var retrofit: Retrofit? = null

    fun getRetrofit(): Retrofit? {
        if(retrofit == null){
            val gson = GsonBuilder()
                .setLenient()
                .create()

            //To log the response for testing
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor).build()

            retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(BuildConfig.API_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                //.addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()
        }

        return retrofit
    }
}