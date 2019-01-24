package com.gpch.login.controller


import com.gpch.login.dto.PlaceAddDTO
import com.gpch.login.repository.PlaceRepository
import com.gpch.login.service.PlaceService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*

import javax.validation.Valid

@Controller
@RequestMapping("/places/")
class PlaceController(
        private val placeService: PlaceService,
        private val placeRepository: PlaceRepository
) {

    @GetMapping("/list/")
    fun placeList(model: Model): String {

        //wyłączone żeby można było testować widok listy
        //val placesList = placeService.placesList()
        val placesList = placeService.getALLPlaces()
        println("PlaceList: $placesList")
        model.addAttribute("placeList",placesList )

        return "place_list"
    }

    @GetMapping("/add/")
    fun showAddPlaceForm(@ModelAttribute placeAddDTO: PlaceAddDTO): String {
        return "place_add"
    }

    @PostMapping("/add/")
    fun addPlace(@Valid @ModelAttribute placeAddDTO: PlaceAddDTO, result: BindingResult): String {
        placeService.verifyAndSavePlace(placeAddDTO, result)
        return if(result.hasErrors())
            "place_add"
        else
            "redirect:../list/"
    }

    @GetMapping("/{id}/")
    fun showPlaceDetails(@PathVariable id: String, model : Model ): String{
        val current = placeRepository.findById(id.toInt()).get()
        model.addAttribute("model",current)
        return "place_detail"
    }
}
