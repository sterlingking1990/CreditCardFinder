package com.project.cardfinder.network

import com.project.cardfinder.model.response.Card
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface API {

    @GET("{iin}")
    suspend fun getCardInfo(@Path("iin") iin:String): Response<Card>
}