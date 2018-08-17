package id.example.footballmatchschedule.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
* Created by dimassaputra on 8/15/18.
*/
class Client {
    companion object {
        fun getClient() : Retrofit{
            return Retrofit.Builder()
                    .baseUrl("https://www.thesportsdb.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
    }
}