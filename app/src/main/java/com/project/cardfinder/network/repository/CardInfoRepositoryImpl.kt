package com.project.cardfinder.network.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.project.cardfinder.model.response.Card
import com.project.cardfinder.network.API
import com.project.cardfinder.util.Resource
import com.project.cardfinder.util.UNABLE_TO_ESTABLISH_CONNECTION
import kotlinx.coroutines.withContext
import retrofit2.Call
import java.lang.Exception
import javax.inject.Inject

class CardInfoRepositoryImpl @Inject constructor(
    private val api: API
): CardInfoRepository {

    private var _cardInfo = MutableLiveData<Resource<Card>>()

    override fun observableCardInfo(): MutableLiveData<Resource<Card>> {
        return _cardInfo
    }

    override suspend fun getCardInfo(iin:String){
        try{
            val response = api.getCardInfo(iin)
            if(response.isSuccessful){
                if(response.body()!=null) {
                    _cardInfo.postValue(Resource.success(response.body()))
                }else {
                    _cardInfo.postValue(
                        Resource.success(
                            null,
                        )
                    )
                }
            }
            else{
                _cardInfo.postValue(
                    Resource.error(
                        "",
                        null
                    )
                )
            }

        }catch (e:Exception){
            _cardInfo.postValue(Resource.error(UNABLE_TO_ESTABLISH_CONNECTION,null))
        }
    }
}