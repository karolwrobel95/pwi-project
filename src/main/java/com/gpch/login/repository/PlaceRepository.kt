package com.gpch.login.repository

import com.gpch.login.model.Place
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface PlaceRepository : JpaRepository<Place, Int> {

    fun findAllByVerifiedIsTrue(): List<Place>

    fun findAllByStreetLike(street: String?): List<Place>

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
}
