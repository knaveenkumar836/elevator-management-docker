package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.pojo.Destination;

@Repository
public interface DestRepo extends JpaRepository<Destination, Integer> {

	Iterable<Destination> findByElevatorId(Integer elevator);  
	Iterable<Destination> findByHotelId(Integer hotelId);
}  