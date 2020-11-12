package com.hotel.query.restaurant.handler

import com.hotel.core.api.restaurant.FindAllRestaurantsQuery
import com.hotel.core.api.restaurant.FindRestaurantQuery
import com.hotel.core.api.restaurant.RestaurantCreatedEvent
import com.hotel.query.restaurant.model.RestaurantView
import com.hotel.query.restaurant.repository.RestaurantViewRepository
import org.axonframework.config.ProcessingGroup
import org.axonframework.eventhandling.EventHandler
import org.axonframework.queryhandling.QueryHandler
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
@ProcessingGroup("restaurant")
class RestaurantProjector {
    @Autowired
    lateinit var repository: RestaurantViewRepository

    @EventHandler
    fun on(event: RestaurantCreatedEvent) {
        LOGGER.info("EVENT HANDLER RestaurantProjector")

        val view = RestaurantView(
            restaurantId = event.restaurantId,
            name = event.name
        )
        repository.save(view)
    }

    @QueryHandler
    fun handle(query: FindRestaurantQuery): Optional<RestaurantView> {
        return repository.findById(query.restaurantId)
    }

    @QueryHandler
    fun handle(query: FindAllRestaurantsQuery): MutableList<RestaurantView> {
        return repository.findAll()
    }

    companion object {
        val LOGGER: Logger = LoggerFactory.getLogger(RestaurantProjector::class.java)
    }
}