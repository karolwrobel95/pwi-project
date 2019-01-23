package com.gpch.login.repository;

import com.gpch.login.model.Event;
import com.gpch.login.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface testRepo extends JpaRepository<Event,Integer> {

//    List<Event> findAllByPlaceEqualsAndStartDateIsNear(Place place);


}
