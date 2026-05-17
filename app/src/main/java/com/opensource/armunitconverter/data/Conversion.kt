package com.opensource.armunitconverter.data

data class Conversion(
    val id: Int,
    val description: String,
    val convertFrom: String,
    val convertTO: String,
    val multiplyBy: Double
)