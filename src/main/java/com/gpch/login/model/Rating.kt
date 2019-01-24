package com.gpch.login.model

import org.springframework.format.annotation.DateTimeFormat
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "rating")
data class Rating constructor(
        @Id
        @Column(name = "rating_id")
        val ratingId: Int? = null,

        @Column(name = "sport")
        val sport: Place.Sport? = null,

        @Column(name = "score")
        var score: Int? = null
)