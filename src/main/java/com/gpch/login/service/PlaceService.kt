package com.gpch.login.service

import com.gpch.login.dto.PlaceAddDTO
import com.gpch.login.model.Place
import com.gpch.login.repository.PlaceRepository
import org.springframework.stereotype.Service
import org.springframework.validation.BindingResult

@Service
class PlaceService constructor(
        private val placeRepository: PlaceRepository
) {

    fun placesList(): List<Place> {
        return placeRepository.findAllByVerifiedIsTrue()
    }

    fun verifyAndSavePlace(place: PlaceAddDTO, result: BindingResult) {
        val count = placeRepository.checkIfPlaceAlreadyExist(place.street,place.streetNo,place.city)
        if (count.isNotEmpty()) result.rejectValue("street", "placeAlreadyExistExist")
        else {
            placeRepository.save(Place().apply {
                name = place.name
                street = place.street
                streetNo = place.streetNo
                city = place.city
                email = place.email
                contactNumber = place.contactNumber
                sports = place.sports
                website = place.website
                verified = false
            })
            println("Dodano nowe miejsce $place")
        }
    }
}
