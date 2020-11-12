package com.hotel.core.api.restaurant

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.util.*

data class CreateRestaurantCommand(
    @TargetAggregateIdentifier
    val restaurantId: UUID,
    val name: String
)