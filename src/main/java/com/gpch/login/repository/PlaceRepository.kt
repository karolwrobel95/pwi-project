package com.gpch.login.repository

import com.gpch.login.model.Event
import com.gpch.login.model.Place
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PlaceRepository : JpaRepository<Place, Int> {

    fun findAllByVerifiedIsTrue(): List<Place>

    fun findAllByStreetLike(street: String?): List<Place>

    @Query("""
        select * from place
        where id = :id
    """,nativeQuery = true)
    fun getPlaceById(@Param("id") id: Int) : Place

    @Query("""
select
  id,
  street,
  contact_number,
  email,
  name,
  sports,
  website,
  verified,
  street_no,
  city
from place p
where p.street like :street and p.street_no = :streetNo and p.city like :city
            """,nativeQuery = true)
    fun checkIfPlaceAlreadyExist(@Param("street") street: String?, @Param("streetNo") streetNo: Int?, @Param("city") city: String?): List<Place>


    @Query("""select * from event
    where id = :place and sport = :sport
      and ((start_date between :startDate and :endDate)
      or (end_date between :startDate and :endDate)
      or start_date = :startDate
      or end_date = :endDate);""",nativeQuery = true)
fun checkIfPlaceAvailable(@Param("place")place: Int, @Param("sport")sport:Place.Sport, @Param("startDate")startDate: Date?, @Param("endDate")endDate: Date?): List<Event>
}
