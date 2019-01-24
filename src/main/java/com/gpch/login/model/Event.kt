package com.gpch.login.model

import org.springframework.format.annotation.DateTimeFormat
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "event")
data class Event constructor(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "event_id")
        val eventId: Int? = null,

        @OneToOne
        @JoinColumn(name = "created_by")
        var createdBy: User? = null,

        @Column(name = "start_date")
        @DateTimeFormat(pattern = "yyyy/MM/dd hh:mm")
        var startDate: Date? = null,

        @Column(name = "end_date")
        @DateTimeFormat(pattern = "yyyy/MM/dd hh:mm")
        var endDate: Date? = null,

        @Column(name = "max_users_amount")
        var maxUsersAmount: Int? = null,

        @Column(name = "min_users_amount")
        var minUsersAmount: Int? = null,

        @Column(name = "current_users_no")
        var currentUserNo: Int? = null,

        @Column
        var description: String? = null,

        @Column
        var sport: Place.Sport? = null,


//        @OneToMany(mappedBy = "event", cascade = [CascadeType.ALL], orphanRemoval = true)
        @ManyToMany(cascade = [CascadeType.ALL])
        @JoinTable(name = "event_user", joinColumns = [JoinColumn(name = "user_id")], inverseJoinColumns = [JoinColumn(name = "event_id")])
        var participants: MutableList<User>? = null,

        @Column(name = "event_state")
        @Enumerated(EnumType.STRING)
        var eventState: State? = null,

//        @Column(name = "event_placement")
//        @OneToOne(cascade = [CascadeType.MERGE])
//        @PrimaryKeyJoinColumn

        @ManyToOne
        @JoinColumn(name = "id")
        var place: Place? = null,

        @Column(name = "sport")
        @Enumerated(EnumType.STRING)
        var sport: Place.Sport? = null

) {
    enum class State (displayName : String){
        PREPARING("W przygotowaniu"),
        IN_PROGRESS("Trwa"),
        DONE("Zakończony")
    }
}