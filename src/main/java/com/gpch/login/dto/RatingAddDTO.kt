package com.gpch.login.dto

import com.gpch.login.model.Event
import com.gpch.login.model.Place
import com.gpch.login.model.User
import org.springframework.stereotype.Component
import java.util.*
import javax.persistence.Column
import javax.validation.constraints.NotBlank

@Component
data class  RatingAddDTO constructor(

        var score: Int? = null,
        var user: User? = null

)
