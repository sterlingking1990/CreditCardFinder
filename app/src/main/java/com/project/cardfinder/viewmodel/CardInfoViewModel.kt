package com.project.cardfinder.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.project.cardfinder.model.response.Card
import com.project.cardfinder.network.repository.CardInfoRepository
import com.project.cardfinder.util.Resource
import kotlinx.coroutines.launch

class CardInfoViewModel @ViewModelInject constructor(var cardApi:CardInfoRepository): ViewModel(){


    val cardInfo: LiveData<Resource<Card>> get() = cardApi.observableCardInfo()

    fun getCardInfo(iin:String){
        viewModelScope.launch {
                cardApi.getCardInfo(iin)
        }
    }
}