package com.gpch.login.service

import com.gpch.login.dto.EventAddDTO
import com.gpch.login.dto.PlaceAddDTO
import com.gpch.login.dto.RatingAddDTO
import com.gpch.login.model.Event
import com.gpch.login.model.Place
import com.gpch.login.model.Rating
import com.gpch.login.repository.EventRepository
import com.gpch.login.repository.RatingRepository
import com.gpch.login.repository.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component


@Component
class RatingService constructor(
        private val ratingRepository: RatingRepository
) {



    fun saveEventDetails(sc : Int, uId: Int, sp : Place.Sport) {
        val rat = Rating().apply {
            score = sc
            userId = uId
            sport = sp
        }
        ratingRepository.save(rat)

    }
}