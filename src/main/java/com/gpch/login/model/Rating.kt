package com.gpch.login.model

import org.springframework.format.annotation.DateTimeFormat
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "rating")
data  class Rating constructor(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "rating_id")
        val ratingId: Int? = null,

        @Column(name = "sport")
        var sport: Place.Sport? = null,

        @Column(name = "score")
        var score: Int? = null,

        @Column(name = "user_id")
        var userId: Int? = null
)