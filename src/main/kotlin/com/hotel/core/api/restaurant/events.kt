package com.hotel.core.api.restaurant

import java.util.*

data class RestaurantCreatedEvent(
    val restaurantId: UUID,
    val name: String
)