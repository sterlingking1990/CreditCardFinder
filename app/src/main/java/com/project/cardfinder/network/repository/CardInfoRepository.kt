package com.project.cardfinder.network.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.project.cardfinder.model.response.Card
import com.project.cardfinder.util.Resource
import retrofit2.Call

interface CardInfoRepository {

    fun observableCardInfo():MutableLiveData<Resource<Card>>

    suspend fun getCardInfo(iin:String)


}