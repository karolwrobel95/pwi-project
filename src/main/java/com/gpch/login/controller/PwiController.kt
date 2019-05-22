package com.gpch.login.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class PwiController {

    @RequestMapping("/pwi")
    fun getOnePage(): String {
        return "pwi"
    }
}