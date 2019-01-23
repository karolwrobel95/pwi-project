package com.gpch.login.repository

import com.gpch.login.model.Event
import org.springframework.data.jpa.repository.JpaRepository

interface EventRepository : JpaRepository<Event,Int> {

}