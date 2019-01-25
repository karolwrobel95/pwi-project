package com.gpch.login.controller

import com.gpch.login.dto.UserEditDTO
import com.gpch.login.model.User
import com.gpch.login.repository.UserRepository
import com.gpch.login.service.UserService
import javafx.scene.control.TextFormatter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import javax.validation.Valid

@Controller
@RequestMapping("/profile/")
class EditProfileController constructor(
    private val userService: UserService,
    private val userRepository : UserRepository) {


    @GetMapping("/edit/")
    fun showProfile(@ModelAttribute userEditDTO :UserEditDTO, model: Model): String {

        return "my_profile"
    }


    @PostMapping("/edit/")
    fun changePassword (@Valid @ModelAttribute userEditDTO: UserEditDTO, result: BindingResult, model: Model):String {
        if(result.hasErrors())
            return "my_profile"
        val auth = SecurityContextHolder.getContext().authentication
        val user = userService.findUserByEmail(auth.name)
        user.password = userEditDTO.password
        userService.saveUser(user)
        model.addAttribute("passwordChanged","Hasło zostało zmienione")
        return "my_profile"

    }
}