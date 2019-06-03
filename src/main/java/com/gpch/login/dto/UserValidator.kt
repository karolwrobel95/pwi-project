package com.gpch.login.dto

import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.gpch.login.model.User
import org.springframework.validation.Errors
import org.springframework.validation.ValidationUtils
import org.springframework.validation.Validator


class UserValidator : Validator {

    override fun supports(clazz: Class<*>): Boolean {
        return User::class.java == clazz
    }

    override fun validate(obj: Any?, e: Errors) {
        ValidationUtils.rejectIfEmpty(e, "name", "name.empty")
        val user = obj as User

        val phoneUtil = PhoneNumberUtil.getInstance()
        try {
            val number = phoneUtil.parse(user.phone, "PL")
        } catch (exc: NumberParseException) {
            e.rejectValue("phoneNumber", "Bad phone number")
        }
    }
}