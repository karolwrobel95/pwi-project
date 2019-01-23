package com.gpch.login.controller

import com.gpch.login.dto.EventAddDTO
import com.gpch.login.repository.EventRepository
import com.gpch.login.repository.PlaceRepository
import com.gpch.login.service.EventService
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Controller
@RequestMapping("/event/")
class EventController constructor(
        private val eventRepository: EventRepository,
        private val placeRepository: PlaceRepository,
        private val eventService: EventService
){

    @GetMapping("/list/")
    fun showEventList(model: Model):String{
        model.addAttribute("events", eventRepository.findAll())
        return "event_list"
    }

    @GetMapping("/create/")
    fun createEvent(model: Model): String{
        model.addAttribute("places", placeRepository.findAll())
        return "event_create"
    }

    @PostMapping("/create/")
    fun saveEvent(@Valid @ModelAttribute eventAddDTO: EventAddDTO,result: BindingResult,authentication: Authentication, model: Model): String{
        if(result.hasErrors())
            return "event_create"
        val verifyPlace = eventService.checkPlaceAvailability(eventAddDTO.place!!)
        eventService.saveEventDetails(eventAddDTO)
        println("mlody to pizda")
        return "redirect: ../{id}/"
    }

    @GetMapping("/{id}/")
    fun showEventDetails(@PathVariable id: Int): String{
        return "event_details"
    }
}