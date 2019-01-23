package com.gpch.login.dto

import com.gpch.login.model.Place
import org.springframework.stereotype.Component
import javax.validation.constraints.NotBlank

@Component
data class PlaceAddDTO constructor(
    @get:NotBlank(message = "Nazwa nie może być pusta")
    var name: String? = null,
    @get:NotBlank(message = "Proszę podać nazwę ulicy")
    var street: String? = null,
    var streetNo: Int? = null,
    @get:NotBlank(message = "Proszę podać nazwę miasta")
    var city: String? = null,
    var contactNumber: Int? = null,
    var email: String? = null,
    var website: String? = null,
    var verified: Boolean? = null,
    var sports: MutableList<Place.Sport>? = null
)