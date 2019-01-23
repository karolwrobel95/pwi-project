package com.gpch.login.dto

import com.gpch.login.model.Place
import org.springframework.stereotype.Component
import java.util.*
import javax.validation.constraints.NotBlank

@Component
data class EventAddDTO constructor(
        @get:NotBlank(message = "Proszę podać datę rozpoczęcia!")
    var startDate: Date? = null,
        @get:NotBlank(message = "Proszę podać czas trwania wydarzenia!")
    var duration: Int? = null,
        @get:NotBlank(message = "Proszę podać maksymalną liczbę uczestników!")
    var maxUsersAmount: Int? = null,
        @get:NotBlank(message = "Proszę podać minimalna liczbę uczestników!")
    var minUsersAmount: Int? = null,
    var description: String? = null,
        @get:NotBlank(message = "Proszę wybrać miejsce!")
    var place: Place? = null
)
