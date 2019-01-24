package com.gpch.login.model


import javax.persistence.*


@Entity
@Table(name = "place")
data class Place constructor(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "Id")
        var id: Int? = null,
        @Column(name = "name")
        var name: String? = null,
        @Column(name = "street")
        var street: String? = null,
        @Column(name = "street_no")
        var streetNo: Int? = null,
        @Column(name = "city")
        var city: String? = null,
        @Column(name = "contact_number")
        var contactNumber: Int? = null,
        @Column(name = "email")
        var email: String? = null,
        @Column(name = "website")
        var website: String? = null,
        @Column(name = "verified")
        var verified: Boolean? = null,
        @ElementCollection(targetClass = Sport::class)
//        @JoinTable(name = "tblInterests", joinColumns = [JoinColumn(name = "personID")])
        @Column(name = "sports")
        @Enumerated(EnumType.STRING)
        var sports: MutableList<Sport>? = null

) {
    enum class Sport(val displayName: String) {
        FOOTBALL("Piłka nożna"),
        VOLLEYBALL("Siatkówka"),
        BASKETBALL("Koszykówka");


//            override fun toString(): String {
//                    return this.dn
//            }


    }

}
