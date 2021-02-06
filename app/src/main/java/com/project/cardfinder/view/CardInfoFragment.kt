package com.project.cardfinder.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.project.cardfinder.R
import com.project.cardfinder.network.NetworkConnectivity
import com.project.cardfinder.viewmodel.CardInfoViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.w3c.dom.Text


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

        loadInitialTextOnCard()
    }



    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            if(start==0){
                loadInitialTextOnCard()
            }
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            displayCardNumberAndFindCardInfo(s,count)
        }
    }


    private fun displayCardNumberAndFindCardInfo(eachChar:CharSequence?, count:Int){
        tvCardNumber.text = eachChar
        if (eachChar != null || count>1) {
            getCardInfoDetails(eachChar!!)
        }
    }


    private fun getCardInfoDetails(cardBin:CharSequence){
        cardInfoViewModel.getCardInfo(cardBin.toString())

        cardInfoViewModel.success.observe(viewLifecycleOwner,{
            if(!it){
                tvNetworkInfo.visibility=View.VISIBLE
                tvNetworkInfo.text = cardInfoViewModel.description.value
                loadInitialTextOnCard()
            }
        })

        cardInfoViewModel.cardInfo.observe(viewLifecycleOwner, {

                tvBank.text = it?.bank?.name
                tvCountry.text = it?.country?.name
                tvBrand.text = it?.brand
                tvCardNumber.text = etCardNumberInput.text
                tvCardType.text = it?.type
        })

        val networkConnectivity = NetworkConnectivity(requireContext())
        networkConnectivity.observe(viewLifecycleOwner, {
            if (it) {
                tvNetworkInfo.visibility = View.INVISIBLE
            }
            else{
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
        tvBank.text = "bank name"
        tvCardType.text = "card type"
        tvBrand.text = "card brand"
        tvCardNumber.text = "00000000"
        tvCountry.text = "country"
    }


}