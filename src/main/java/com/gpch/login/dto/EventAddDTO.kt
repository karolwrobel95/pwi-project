package com.gpch.login.dto

import com.gpch.login.model.Place
import org.springframework.stereotype.Component
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Component
data class EventAddDTO constructor(
        @get:NotBlank
        var date: String? = null,
        @get:NotBlank
        var start: String? = null,
        @get:NotBlank
        var end: String? = null,
        @get:NotNull
        @get:Min(2)
        var maxUsersAmount: Int? = null,
        @get:NotNull
        @get:Min(2)
        var minUsersAmount: Int? = null,
        @get:NotBlank
        var description: String? = null,
        var place: Int? = null,
        var sport: Place.Sport? = null
)
