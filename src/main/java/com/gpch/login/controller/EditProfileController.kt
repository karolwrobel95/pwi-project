package com.gpch.login.controller

import com.gpch.login.model.User
import com.gpch.login.repository.UserRepository
import com.gpch.login.service.UserService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.validation.Valid

@Controller
@RequestMapping("/profile/")
class EditProfileController constructor(
    private val userService: UserService,
    private val userRepository : UserRepository) {


    @GetMapping("/edit/")
    fun showProfile(@ModelAttribute userModel :User, model: Model): String {
        val auth = SecurityContextHolder.getContext().authentication
        val user = userService.findUserByEmail(auth.name)
        user!!.password=""
        model.addAttribute(user)
        return "my_profile"
    }


    @PostMapping("/edit/")
    fun changePassword (@Valid @ModelAttribute user: User, result: BindingResult, model: Model):String {
        if(result.hasErrors())
            return "my_profile"
        userService.changeCredentials(user)
        model.addAttribute("passwordChanged","Hasło zostało zmienione")
        return "my_profile"

    }
}