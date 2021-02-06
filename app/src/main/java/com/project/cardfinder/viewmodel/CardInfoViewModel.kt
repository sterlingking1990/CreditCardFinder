package com.project.cardfinder.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.project.cardfinder.model.response.Card
import com.project.cardfinder.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CardInfoViewModel @ViewModelInject constructor(var cardApi:ApiService): ViewModel(){

    private var _cardInfo = MutableLiveData<Card>()
    val cardInfo: LiveData<Card> get() = _cardInfo


    private var _success = MutableLiveData<Boolean>()
    val success:LiveData<Boolean> get() = _success

    private var _description = MutableLiveData<String>()
    val description:LiveData<String> get() = _description



    fun getCardInfo(iin:String){

        val cardInfo = cardApi.api.getCardInfo(iin)
        cardInfo.enqueue(
            object : Callback<Card> {
                override fun onFailure(call: Call<Card>, t: Throwable) {
                    _success.value = false
                    _description.value = "Could not establish connection"
                }

                override fun onResponse(call: Call<Card>, response: Response<Card>) {
                    if(response.isSuccessful) {
                        _success.value = true
                        _cardInfo.value = response.body()
                    }
                    else{
                        _success.value = false
                        _description.value = response.message()
                    }
                }
            }
        )
    }
}