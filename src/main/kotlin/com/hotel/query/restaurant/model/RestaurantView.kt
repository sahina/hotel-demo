package com.hotel.query.restaurant.model

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class RestaurantView(
    @Id
    val restaurantId: UUID,
    val name: String,
)

