package com.gpch.login.service

import com.gpch.login.model.User
import com.gpch.login.repository.RoleRepository
import com.gpch.login.repository.UserRepository
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Transactional
@Service("userService")
class UserService constructor(private val userRepository: UserRepository,
            private val roleRepository: RoleRepository,
            private val bCryptPasswordEncoder: BCryptPasswordEncoder) {

    fun findUserByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }

    fun saveUser(user: User): User {
        user.password = bCryptPasswordEncoder.encode(user.password)
        user.active = 1
        val userRole = roleRepository.findByRole("ADMIN")
        user.roles = HashSet(Arrays.asList(userRole))
        return userRepository.save(user)
    }

    fun changeCredentials(userModel: User){
        val auth = SecurityContextHolder.getContext().authentication
        val user = findUserByEmail(auth.name)
        if(!userModel.password.isNullOrBlank())
            user?.password = bCryptPasswordEncoder.encode(userModel.password)
        user?.apply {
            email=userModel.email
            name=userModel.name
            lastName=userModel.lastName
            dateOfBirth=userModel.dateOfBirth
            street=userModel.street
            city=userModel.city
            country=userModel.country
            postCode=userModel.postCode
            phone=userModel.phone
            sex=userModel.sex
        }
    }

}