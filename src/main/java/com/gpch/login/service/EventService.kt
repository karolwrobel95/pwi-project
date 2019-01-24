package com.gpch.login.service

import com.gpch.login.dto.EventAddDTO
import com.gpch.login.model.Event
import com.gpch.login.model.Place
import com.gpch.login.repository.EventRepository
import com.gpch.login.repository.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component


@Component
class EventService constructor(
        private val eventRepository: EventRepository,
        private val userRepository: UserRepository
) {

    fun getEventById(id : Int) : Event{
        return eventRepository.findById(id).get()
    }

    fun checkPlaceAvailability(place: Place) {

    }

    fun saveEventDetails(dto: EventAddDTO) {
        val auth = SecurityContextHolder.getContext().authentication.principal as UserDetails
        val user = userRepository.findByEmail(auth.username) //zamiast username uzywamy adresu email
        val event = Event().apply {
            createdBy = user
            startDate = dto.startDate
            duration = dto.duration
            maxUsersAmount = dto.maxUsersAmount
            minUsersAmount = dto.minUsersAmount
            currentUserNo = 0
            description = dto.description
            participants = mutableListOf(user)
            eventState = Event.State.PREPARING
            place = dto.place
        }
        eventRepository.save(event)

    }
}