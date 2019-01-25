package com.gpch.login.dto

import org.springframework.stereotype.Component
import javax.validation.constraints.Size

@Component
data class UserEditDTO constructor(
        @get:Size(min = 2, max = 15)
        var password: String? = null
)