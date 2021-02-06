package com.project.cardfinder.model.response

data class Card(
    val number: NumberPojo?,
    val schema: String?,
    val type: String?,
    val brand: String?,
    val prepaid: String?,
    val country: Country?,
    val bank: Bank?
)