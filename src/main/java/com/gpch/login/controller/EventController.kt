package com.gpch.login.controller

import com.gpch.login.dto.EventAddDTO
import com.gpch.login.dto.RatingAddDTO
import com.gpch.login.model.User
import com.gpch.login.repository.EventRepository
import com.gpch.login.repository.PlaceRepository
import com.gpch.login.repository.UserRepository
import com.gpch.login.service.EventService
import com.gpch.login.service.UserService
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
        private val eventService: EventService,

        //do mocka
        private  val userRepository: UserRepository
){

    @GetMapping("/list/")
    fun showEventList(model: Model):String{
        model.addAttribute("events", eventRepository.findAll())
        return "event_list"
    }

    @GetMapping("/create/")
    fun createEvent(@ModelAttribute eventAddDTO: EventAddDTO, model: Model): String{
        model.addAttribute("places", placeRepository.findAll())
        return "event_create"
    }

    @PostMapping("/create/")
    fun saveEvent(@Valid @ModelAttribute eventAddDTO: EventAddDTO,result: BindingResult,authentication: Authentication, model: Model): String{
        if(result.hasErrors())
            return "event_create"
        //val verifyPlace = eventService.checkPlaceAvailability(eventAddDTO.place!!)
        eventService.saveEventDetails(eventAddDTO)
        return "redirect: ../{id}/"
    }

    @GetMapping("/{id}/")
    fun showEventDetails(@PathVariable id: String, model : Model ): String{
        val current = eventRepository.findById(id.toInt()).get()
        model.addAttribute("model",current)
        return "event_detail"
    }

    @GetMapping("/{id}/rating/")
    fun getRating(@PathVariable id: Int, model: Model): String{
        val currentEvent = eventService.getEventById(id)
        //mock do widoku
        val participans =  userRepository.findAll()
        //val participans = currentEvent.participants;
        model.addAttribute("participans",participans)
        return "rating"
    }

    @PostMapping("/{userId}/{score}")
    fun SaveRating(@PathVariable userId : Int, @PathVariable score: Boolean){
        val user =  userRepository.findById(userId)
    }
}