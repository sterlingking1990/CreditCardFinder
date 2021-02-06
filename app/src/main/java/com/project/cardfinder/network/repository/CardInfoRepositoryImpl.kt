package com.project.cardfinder.network.repository

import com.project.cardfinder.model.response.Card
import com.project.cardfinder.network.API
import retrofit2.Call
import javax.inject.Inject

class CardInfoRepositoryImpl @Inject constructor(
    private val api: API
): CardInfoRepository {
    override suspend fun getCardInfo(iin:String): Call<Card> {
        return api.getCardInfo(iin)
    }
}