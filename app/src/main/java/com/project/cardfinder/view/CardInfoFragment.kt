package com.project.cardfinder.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.project.cardfinder.R
import com.project.cardfinder.viewmodel.CardInfoViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CardInfoFragment : Fragment() {
    private val cardInfoViewModel by viewModels<CardInfoViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        cardInfoViewModel.getCardInfo("45717360")

        cardInfoViewModel.cardInfo.observe(viewLifecycleOwner, Observer {

        })
    }

}