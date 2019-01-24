package com.gpch.login.controller

import com.gpch.login.dto.EventAddDTO
import com.gpch.login.model.Place
import com.gpch.login.model.Rating
import com.gpch.login.repository.EventRepository
import com.gpch.login.repository.PlaceRepository
import com.gpch.login.repository.RatingRepository
import com.gpch.login.repository.UserRepository
import com.gpch.login.service.EventService
import com.gpch.login.service.RatingService
import com.gpch.login.service.UserService
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@Controller
@RequestMapping("/event/")
class EventController constructor(
        private val eventRepository: EventRepository,
        private val placeRepository: PlaceRepository,
        private val eventService: EventService,
        private val userService: UserService,

        //do mocka
        private  val userRepository: UserRepository,
        private  val ratingService: RatingService
){

    @GetMapping("/list/")
    fun showEventList(model: Model):String{
        model.addAttribute("events", eventRepository.findAll())
        return "event_list"
    }

    @GetMapping("/create/")
    fun createEvent(@ModelAttribute eventAddDTO: EventAddDTO, model: Model): String{
        model.addAttribute("places", placeRepository.findAll())
        model.addAttribute("sports", Place.Sport.values())  //TODO dynamiczne pobieranie dostepnych sportow w zaleznosci od wybranego miejsca
        return "event_create"
    }

    @PostMapping("/create/")
    fun saveEvent(@Valid @ModelAttribute eventAddDTO: EventAddDTO, result: BindingResult, model: Model): String{
        if (result.hasErrors()){
            model.addAttribute("places", placeRepository.findAll())
            model.addAttribute("sports", Place.Sport.values())  //TODO dynamiczne pobieranie dostepnych sportow w zaleznosci od wybranego miejsca
            return "event_create"}
        eventService.saveEventDetails(eventAddDTO,result)

        println(eventAddDTO)
        return "event_create" //TODO zmienic link
    }

    @GetMapping("/{id}/")
    fun showEventDetails(@PathVariable id: String, model : Model ): String{
        val current = eventRepository.findById(id.toInt()).get()
        model.addAttribute("model",current)
        return "event_detail"
    }

    @GetMapping("/{id}/rating/")
    fun getRating(@PathVariable id: Int, model: Model): String{
//        val currentEvent = eventService.getEventById(id)
        //mock do widoku
        val participans =  userRepository.findAll()
        //val participans = currentEvent.participants;
        model.addAttribute("participans",participans)
        return "rating"
    }

    @PostMapping("/{eventId}/rating/{score}/{userId}/")
    fun saveRating(@PathVariable eventId: Int,@PathVariable score: Int,@PathVariable userId: Int, res : HttpServletResponse) : String{
        var user = userRepository.findById(userId).get()
        var event = eventRepository.findById(eventId).get()
        ratingService.saveEventDetails(score,user.id,event.sport!!)
        user.addRating(Rating())
        return "redirect: ../rating"

    }
}