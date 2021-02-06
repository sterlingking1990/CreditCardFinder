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

    private lateinit var iin:Card



    fun getCardInfo(iin:String){
        Log.d("iin",iin.toString())
        val cardInfo = cardApi.api.getCardInfo(iin)
        cardInfo.enqueue(
            object : Callback<Card> {
                override fun onFailure(call: Call<Card>, t: Throwable) {
                    _success.value = false
                    Log.d("FAILURE", t.message.toString())
                }

                override fun onResponse(call: Call<Card>, response: Response<Card>) {
                    _success.value = true
                    _cardInfo.value = response.body()
                    Log.d("SUCCESS", cardInfo.toString())
                }
            }
        )
    }
}