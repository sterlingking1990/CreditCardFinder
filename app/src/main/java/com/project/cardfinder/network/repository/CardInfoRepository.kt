package com.project.cardfinder.network.repository

import com.project.cardfinder.model.response.Card
import retrofit2.Call

interface CardInfoRepository {

    suspend fun getCardInfo(iin:String): Call<Card>
}