package com.hotel.web.controllers

import com.hotel.core.api.restaurant.CreateRestaurantCommand
import com.hotel.core.api.restaurant.FindAllRestaurantsQuery
import com.hotel.query.restaurant.model.RestaurantView
import com.hotel.web.models.CreateRestaurantRequest
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.messaging.responsetypes.ResponseTypes
import org.axonframework.queryhandling.QueryGateway
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/restaurant")
class RestaurantController {
    @Autowired
    lateinit var commandGateway: CommandGateway

    @Autowired
    lateinit var queryGateway: QueryGateway

    @PostMapping("/create")
    fun create(
        @RequestBody request: CreateRestaurantRequest
    ): ResponseEntity<UUID> {
        val restaurantId = UUID.randomUUID()
        val command = CreateRestaurantCommand(
            restaurantId = restaurantId,
            name = request.name
        )
        commandGateway.send<CreateRestaurantCommand>(command)

        return ResponseEntity(restaurantId, HttpStatus.CREATED)
    }

    @GetMapping("/list")
    fun getList(): List<RestaurantView> {
        val future = queryGateway.query(
            FindAllRestaurantsQuery(),
            ResponseTypes.multipleInstancesOf(RestaurantView::class.java)
        )

        return future.get()
    }

    companion object {
        val LOGGER: Logger = LoggerFactory.getLogger(RestaurantController::class.java)
    }
}