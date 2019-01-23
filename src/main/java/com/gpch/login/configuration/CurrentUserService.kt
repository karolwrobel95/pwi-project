/*
package com.gpch.login.configuration

import com.gpch.login.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

class CurrentUserService constructor(
        private val userRepository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(email: String?): UserDetails {
        val user = userRepository.findByEmail(email)
        user?.let{ return CurentUserDetails(user)}
        throw UsernameNotFoundException(email)
    }
}*/
