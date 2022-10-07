package com.example.demo.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.pojo.Elevator1;

@Repository
public interface ElevatorRepo extends JpaRepository<Elevator1, Integer> {
	
Iterable<Elevator1> findByHotelId(Integer hotelId);  
	
	@Modifying
	@Query("update Elevator1 e set e.currentFloor = :floor where e.elevatorId = :id")
	int setCurrentFloorForElevator(@Param(value = "floor") int floor,@Param(value = "id") int id);
	
} 