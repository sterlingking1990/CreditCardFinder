package com.project.cardfinder.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.project.cardfinder.MainCoroutineRule
import com.project.cardfinder.getOrAwaitValueTest
import com.project.cardfinder.model.response.Card
import com.project.cardfinder.network.repository.FakeCardInfoRepository
import com.project.cardfinder.util.Status
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.net.ssl.SSLEngineResult

@ExperimentalCoroutinesApi
class CardInfoViewModelTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private lateinit var cardInfoViewModel: CardInfoViewModel

    @Before
    fun setUp(){
        cardInfoViewModel = CardInfoViewModel(FakeCardInfoRepository())
    }

    @Test
    fun `enter empty card number returns null`(){
        cardInfoViewModel.getCardInfo("")

        val observableValue = cardInfoViewModel.cardInfo.getOrAwaitValueTest()

        assertEquals(observableValue.data, null)

    }

    @Test
    fun `enter card number less than 4 return`(){
        cardInfoViewModel.getCardInfo("506")

        val observableValue = cardInfoViewModel.cardInfo.getOrAwaitValueTest()

        assertEquals(observableValue.message, "Error")
    }

}
