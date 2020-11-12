package com.hotel.core.models

import java.math.BigDecimal

data class Money(val amount: BigDecimal) {

    fun add(delta: Money): Money {
        return Money(amount.add(delta.amount))
    }

    fun isGreaterThanOrEqual(other: Money): Boolean {
        return amount >= other.amount
    }

    fun multiply(x: Int): Money {
        return Money(amount.multiply(BigDecimal(x)))
    }
}

data class Phone(
    val countryCode: String,
    val phoneNumber: String
) {
    fun normalize(): String {
        return "${countryCode}${phoneNumber}"
    }
}