package com.gpch.login.controller

import com.gpch.login.model.User
import com.gpch.login.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.ModelAndView

import javax.validation.Valid

@Controller
class LoginController constructor(
        private val userService: UserService
) {

    @RequestMapping(value = ["/", "/login"], method = [RequestMethod.GET])
    fun login(): ModelAndView {
        val modelAndView = ModelAndView()
        modelAndView.viewName = "login"
        return modelAndView
    }


    @RequestMapping(value = "/registration", method = [RequestMethod.GET])
    fun registration(): ModelAndView {
        val modelAndView = ModelAndView()
        val user = User()
        modelAndView.addObject("user", user)
        modelAndView.viewName = "registration"
        return modelAndView
    }

    @RequestMapping(value = "/registration", method = [RequestMethod.POST])
    fun createNewUser(@Valid user: User, bindingResult: BindingResult): ModelAndView {
        val modelAndView = ModelAndView()
        if (bindingResult.hasErrors()) {
            modelAndView.viewName = "registration"
            if (user.email.isNotBlank()) {
                val userExists = userService.findUserByEmail(user.email)
                if (userExists?.id != null) {
                    bindingResult.rejectValue("email", "error.user", "There is already a user registered with the email provided")
                }
            }
        } else {
            userService.saveUser(user)
            modelAndView.addObject("successMessage", "User has been registered successfully")
            modelAndView.addObject("user", User())
            modelAndView.viewName = "registration"
        }
        return modelAndView
    }

    @RequestMapping(value = "/home", method = [RequestMethod.GET])
    fun home(): ModelAndView {
        val modelAndView = ModelAndView()
        val auth = SecurityContextHolder.getContext().authentication
        val user = userService.findUserByEmail(auth.name)
        modelAndView.addObject("userName", "Welcome " + user!!.name + " " + user.lastName + " (" + user.email + ")")
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role")
        modelAndView.viewName = "admin/home"
        return modelAndView
    }


}
