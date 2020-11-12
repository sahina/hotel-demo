package com.hotel.query.restaurant.repository

import com.hotel.query.restaurant.model.RestaurantView
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface RestaurantViewRepository : JpaRepository<RestaurantView, UUID>