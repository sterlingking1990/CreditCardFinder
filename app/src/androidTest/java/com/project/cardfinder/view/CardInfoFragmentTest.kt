package com.project.cardfinder.view

import android.view.View
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.android.material.textfield.TextInputLayout
import com.project.cardfinder.R
import com.project.cardfinder.launchFragmentInHiltContainer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.internal.hamcrest.HamcrestArgumentMatcher


const val CREDIT_CARD_NUMBER="5061"
const val CARD_NUMBER_IS_8_DIGIT ="12345678"

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class CardInfoFragmentTest {

    @Before
    fun setUp(){
        val scenario = launchFragmentInHiltContainer<CardInfoFragment>()
    }


    @Test
    fun backgroundImageIsDisplayedOnLaunch(){
        onView(withId(R.id.imgCardBackground)).check(matches(isDisplayed()))
    }

    @Test
    fun creditCardEditInputIsDisplayedOnLaunch(){
        onView(withId(R.id.et_card_number)).check(matches(isDisplayed()))
    }

    @Test
    fun creditCardNumberCanBeAcceptedInEditInput(){
        onView(withId(R.id.et_card_number))
            .perform(ViewActions.typeText(CREDIT_CARD_NUMBER))
        Espresso.closeSoftKeyboard()
    }

    @Test
    fun cardInputEditTextIsZerosByDefault(){

        onView(withId(R.id.tvCardNumber)).check(matches(withText("00000000")))
    }

    @Test
    fun bankNameIsBankOnLaunch(){
        onView(withId(R.id.tvBankName)).check(matches(withText("Bank")))
    }

    @Test
    fun brandNameIsBrandOnLaunch(){
        onView(withId(R.id.tvBrand)).check(matches(withText("brand")))
    }

    @Test
    fun countryNameIsCountryOnLaunch(){
        onView(withId(R.id.tvCountry)).check(matches(withText("Country")))
    }

    @Test
    fun cardTypeIsCardOnLaunch(){
        onView(withId(R.id.tvCardType)).check(matches(withText("Card")))
    }

    @Test
    fun errorStateViewIsInvisibleOnLaunch(){
        onView(withId(R.id.tvNetworkInfo)).check(matches(not(isDisplayed())))
    }

    

}