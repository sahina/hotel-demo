package com.hotel.domain.restaurant

import com.hotel.core.api.restaurant.CreateRestaurantCommand
import com.hotel.core.api.restaurant.RestaurantCreatedEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.spring.stereotype.Aggregate
import java.util.*

@Aggregate
class RestaurantAggregate {
    @AggregateIdentifier
    lateinit var restaurantId: UUID
    lateinit var name: String

    constructor()

    @CommandHandler
    constructor(cmd: CreateRestaurantCommand) {
        val event = RestaurantCreatedEvent(
            restaurantId = cmd.restaurantId,
            name = cmd.name
        )

        AggregateLifecycle.apply(event)
    }

    @EventSourcingHandler
    fun on(event: RestaurantCreatedEvent) {
        restaurantId = event.restaurantId
        name = event.name
    }
}