package com.gpch.login.dto

import org.springframework.stereotype.Component
import java.time.LocalDate
import java.util.*
import javax.validation.constraints.*

@Component
data class UserEditDTO constructor(
        @get:Size(min = 2, max = 15)
        var password: String? = null,
        @Email(message = "Email should be valid")
        var email: String? = null,
        @get:Min(value = 1, message = "Every month has at least 1 day")
        @get:Max(value = 31, message = "No more than 31 days in month")
        val day: Int?= null,
        @get:Min(value = 1, message = "I dont know that month")
        @get:Max(value = 12, message = "Only 12 months in year")
        val month: Int?= null,
        @get:Min(value = 1900, message = "You still alive?")
        @get:Max(value = 2019, message = "Are you from the future?")
        val year: Int?= null,

        @get:NotBlank
        val street: String? = null,
        @get:NotBlank
        val city: String? = null,
        @get:NotBlank
        val country: String? = null,
        @get:NotBlank
        val postCode: String? = null,
        @get:NotBlank
        val phone: String? = null

)