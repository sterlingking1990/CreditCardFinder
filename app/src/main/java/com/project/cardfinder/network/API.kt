package com.project.cardfinder.network

import com.project.cardfinder.model.response.Card
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface API {

    @GET("{iin}")
    fun getCardInfo(@Path("iin") iin:String): Call<Card>
}