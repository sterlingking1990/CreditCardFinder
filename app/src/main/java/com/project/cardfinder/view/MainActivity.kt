package com.project.cardfinder.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.project.cardfinder.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_CardFinder)
        setContentView(R.layout.activity_main)

        val fragment = CardInfoFragment()
        openFragment(fragment)

    }



    private fun openFragment(fragment: Fragment?) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fr_cardInfo, fragment!!)
            .addToBackStack(null)
            .commit()
    }
}