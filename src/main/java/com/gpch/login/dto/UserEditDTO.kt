package com.gpch.login.dto

import org.springframework.stereotype.Component

@Component
data class UserEditDTO constructor(
        var password: String? = null
)