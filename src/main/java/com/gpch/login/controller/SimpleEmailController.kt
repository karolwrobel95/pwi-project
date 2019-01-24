////package com.quickprogrammingtips.springboot
//package com.gpch.login.controller
//
//import com.gpch.login.repository.EventRepository
//import com.gpch.login.repository.UserRepository
//import javax.mail.internet.MimeMessage
//
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.mail.javamail.JavaMailSender
//import org.springframework.mail.javamail.MimeMessageHelper
//import org.springframework.stereotype.Controller
//import org.springframework.web.bind.annotation.PathVariable
//import org.springframework.web.bind.annotation.RequestMapping
//import org.springframework.web.bind.annotation.ResponseBody
//
//@Controller
//@RequestMapping("/mail/")
//public class SimpleEmailController
//constructor
//(
//        private val eventRepository : EventRepository,
//        private val userRepository : UserRepository,
//        private val sender: JavaMailSender
//)
//{
//
//    @RequestMapping("/simpleemail/{userId}/{type}/{eventId}/")
//    @ResponseBody
//    fun home(@PathVariable userId : Int, @PathVariable type : Int, @PathVariable eventId : Int): String {
//        try {
//            sendEmail(userId,type, eventId)
//            return "Email Sent!"
//        } catch (ex: Exception) {
//            return "Error in sending email: $ex"
//        }
//    }
//    @RequestMapping("/simpleemail/{userId}/")
//    @ResponseBody
//    fun home(@PathVariable userId : Int): String {
//        try {
//            sendEmail(userId)
//            return "Email Sent!"
//        } catch (ex: Exception) {
//            return "Error in sending email: $ex"
//        }
//    }
//    @Throws(Exception::class)
//    private fun sendEmail(userId : Int, type : Int, eventId: Int) {
//        val message = sender!!.createMimeMessage()
//        val helper = MimeMessageHelper(message)
//        val event = eventRepository.findById(eventId).get()
//        val user = userRepository.findById(userId).get()
//        when(type)
//        {
//            0 ->
//            {
//                helper.setTo(user.email)
//                helper.setText(user.name + "dołączyłeś do wydarzenia: " + event.description)
//                helper.setSubject("Dołączenie do wydarzenia")
//            }
//            1 ->
//            {
//                helper.setTo(user.email)
//                helper.setText(user.name + "Wydarzenie "+ event.description +" zostało usunięte")
//                helper.setSubject("Usunięto wydarzenie")
//            }
//        }
//        sender.send(message)
//    }
//}