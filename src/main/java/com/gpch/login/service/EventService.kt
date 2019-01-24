package com.gpch.login.service

import com.gpch.login.dto.EventAddDTO
import com.gpch.login.model.Event
import com.gpch.login.model.Place
import com.gpch.login.repository.EventRepository
import com.gpch.login.repository.PlaceRepository
import com.gpch.login.repository.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import org.springframework.validation.BindingResult
import java.text.SimpleDateFormat
import java.util.*


@Component
class EventService constructor(
        private val eventRepository: EventRepository,
        private val userRepository: UserRepository,
        private val placeRepository: PlaceRepository
) {
    fun checkPlaceAvailability(place: Place,sport: Place.Sport, start: Date, end: Date) =
            placeRepository.checkIfPlaceAvailable(place.id!!, sport,start,end)

    fun saveEventDetails(dto: EventAddDTO, result: BindingResult) {
        val placement = placeRepository.getPlaceById(dto.place!!)
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm")
        val startTime = formatter.parse(dto.date +" "+ dto.start)
        val endTime = formatter.parse(dto.date +" "+ dto.end)
        val placeAvailable = checkPlaceAvailability(placement,dto.sport!!,startTime,endTime)
        if(placeAvailable.isNotEmpty() )
            return result.rejectValue("date","dateOccupied")
        val auth = SecurityContextHolder.getContext().authentication.principal as UserDetails
        val user = userRepository.findByEmail(auth.username) //zamiast username uzywamy adresu email
        val event = Event().apply {
            createdBy = user
            startDate = startTime
            endDate = endTime
            maxUsersAmount = dto.maxUsersAmount
            minUsersAmount = dto.minUsersAmount
            currentUserNo = 1
            description = dto.description
            participants = mutableListOf(user)
            eventState = Event.State.PREPARING
            place = placement
            sport = dto.sport
        }
        eventRepository.save(event)
    }
}