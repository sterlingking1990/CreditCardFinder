package com.project.cardfinder.network.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.project.cardfinder.model.response.Bank
import com.project.cardfinder.model.response.Card
import com.project.cardfinder.model.response.Country
import com.project.cardfinder.model.response.NumberPojo
import com.project.cardfinder.util.Resource

class FakeCardInfoRepository:CardInfoRepository {

    private val _observableCardInfo = MutableLiveData<Resource<Card>>()

    var observableCardInfo = _observableCardInfo

    private var cardNumberEmpty = ""

    private var cardNumberLessThan4="123"


    fun setCardNumberEmpty(iin:String){
        cardNumberEmpty = iin
    }

    fun setCardNumberLessThan4(iin: String){
        cardNumberLessThan4 = iin
    }

    override fun observableCardInfo(): MutableLiveData<Resource<Card>> {
        return _observableCardInfo
    }

    override suspend fun getCardInfo(iin: String) {
        setCardNumberEmpty("")
        setCardNumberLessThan4("123")

        if(cardNumberEmpty==""){
            observableCardInfo.postValue(Resource.success(null))
        }

        if (cardNumberLessThan4.length in 1..3){
            observableCardInfo.postValue(Resource.error("Error",null))
        }
    }


}