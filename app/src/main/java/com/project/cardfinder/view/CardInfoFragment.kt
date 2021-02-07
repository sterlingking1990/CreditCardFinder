package com.project.cardfinder.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.project.cardfinder.R
import com.project.cardfinder.network.NetworkConnectivity
import com.project.cardfinder.util.Status
import com.project.cardfinder.viewmodel.CardInfoViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


@AndroidEntryPoint
class CardInfoFragment : Fragment() {
    private val cardInfoViewModel by viewModels<CardInfoViewModel>()
    lateinit var tvBank:TextView
    lateinit var tvCountry:TextView
    lateinit var tvBrand:TextView
    lateinit var tvCardType:TextView
    lateinit var tvCardNumber:TextView
    lateinit var etCardNumberInput:EditText
    lateinit var tvNetworkInfo:TextView

    lateinit var imgBackground:ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card_info, container, false)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tvBank = view.findViewById(R.id.tvBankName)
        tvCardType = view.findViewById(R.id.tvCardType)
        tvBrand = view.findViewById(R.id.tvBrand)
        tvCardNumber = view.findViewById(R.id.tvCardNumber)
        tvCountry = view.findViewById(R.id.tvCountry)
        etCardNumberInput = view.findViewById(R.id.et_card_number)
        etCardNumberInput.addTextChangedListener(textWatcher)
        tvNetworkInfo = view.findViewById(R.id.tvNetworkInfo)
        imgBackground = view.findViewById(R.id.imgCardBackground)

        etCardNumberInput.setOnTouchListener(View.OnTouchListener { _, event ->
            val DRAWABLE_RIGHT = 2
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= etCardNumberInput.right - etCardNumberInput.compoundDrawables[DRAWABLE_RIGHT].bounds.width()) {
                    // your action here
                    clearText()
                    return@OnTouchListener true
                }
            }
            false
        })

        checkInternet()
        loadInitialTextOnCard()
    }



    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            if(start == 0){
                loadInitialTextOnCard()
            }
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            displayCardNumberAndFindCardInfo(s, count)
        }
    }


    private fun displayCardNumberAndFindCardInfo(eachChar: CharSequence?, count: Int){
        tvCardNumber.text = eachChar
        if (eachChar != null || count>1) {
            getCardInfoDetails(eachChar!!)
        }
    }


    private fun getCardInfoDetails(cardBin: CharSequence){
        cardInfoViewModel.getCardInfo(cardBin.toString())

        cardInfoViewModel.cardInfo.observe(viewLifecycleOwner, {
            if (it.status == Status.SUCCESS) {
                if (it.data != null) {
                    tvBank.text = it?.data?.bank?.name
                    tvCountry.text = it?.data?.country?.name
                    tvBrand.text = it?.data?.brand
                    tvCardNumber.text = etCardNumberInput.text
                    tvCardType.text = it?.data?.type
                    imgBackground.setImageResource(getRandomImage())
                } else {
                    tvNetworkInfo.visibility = View.VISIBLE
                    tvNetworkInfo.text = it.message
                    loadInitialTextOnCard()
                }
            } else {
                tvNetworkInfo.visibility = View.VISIBLE
                tvNetworkInfo.text = it.message
                loadInitialTextOnCard()
            }
        })
    }

    private fun checkInternet() {
        val networkConnectivity = NetworkConnectivity(requireContext())
        networkConnectivity.observe(viewLifecycleOwner, {
            if (it) {
                tvNetworkInfo.visibility = View.INVISIBLE
            } else {
                tvNetworkInfo.visibility = View.VISIBLE
            }
        })
    }

    private fun clearText(){
        if(etCardNumberInput.text.toString().isNotEmpty()){
            etCardNumberInput.setText("")
        }
    }

    private fun loadInitialTextOnCard(){
        tvBank.text = getString(R.string.bank_name)
        tvCardType.text = getString(R.string.card_type)
        tvBrand.text = getString(R.string.brand)
        tvCardNumber.text = getText(R.string.card_number)
        tvCountry.text = getString(R.string.country)
    }

    private fun getRandomImage(): Int {
        val r = Random()
        val imageList=listOf(R.drawable.ic_creditcard_blue,R.drawable.ic_creditcard_grey,
            R.drawable.ic_creditcard_red,R.drawable.ic_creditcard_purple,R.drawable.ic_card_background)
        val randomNumber = r.nextInt(listOf(R.drawable.ic_creditcard_blue,R.drawable.ic_creditcard_grey,
        R.drawable.ic_creditcard_red,R.drawable.ic_creditcard_purple,R.drawable.ic_card_background).size)
        return imageList[randomNumber]
    }


}