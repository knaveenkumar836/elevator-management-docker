package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.InvalidNumber;
import com.example.demo.pojo.Elevator1;
import com.example.demo.repository.ElevatorRepo;


@Service
public class ElevatorService {
	
	@Autowired
	ElevatorRepo elevatorRepository;
		
	@Value("${app.elevator.max.numbers}")
	Integer max_elevators;
	@Value("${app.elevator.max.floors}")
	Integer max_floors;
	@Value("${app.elevator.min.floors}")
	Integer min_floors;

	public List<Elevator1> getAllElevator() {
		List<Elevator1> elevators = new ArrayList<Elevator1>();
		elevatorRepository.findAll().forEach(elevator -> elevators.add(elevator));
		return elevators;
	}
	
	public List<Elevator1> getAllHotelById(Integer hotelId) {
		List<Elevator1> elevators = new ArrayList<Elevator1>();
		elevatorRepository.findByHotelId(hotelId).forEach(elevator -> elevators.add(elevator));
		return elevators;
	}

	public Elevator1 getElevatorById(int id) {
		return elevatorRepository.findById(id).get();
	}

	public void save(Elevator1 elevator) {
		elevatorRepository.save(elevator);
	}
	
	public void updateFloors(Integer floor, Elevator1 elevator) {
		elevatorRepository.setCurrentFloorForElevator(floor,elevator.getElevatorId());
	}
	
	public String addElevator(Elevator1 elevator,Integer numberOfElevators) throws InvalidNumber  {
		if (numberOfElevators < 0)
			throw new InvalidNumber("Elevator number must be positive");
		if (this.getAllHotelById(elevator.getHotelId()).size() > max_elevators)
			throw new InvalidNumber("Number of Elevator exceeds the allowed limit ");
		if (elevator.getNumberOfFloors() > max_floors)
			throw new InvalidNumber("Floor do not exits");
		if (elevator.getNumberOfFloors() < min_floors)
			throw new InvalidNumber("Floor do not exits");
		List<Elevator1> allElevators= getAllElevator();
		for (int idx = allElevators.size()+1; idx < numberOfElevators+allElevators.size()+1; idx++) {	
			elevatorRepository.save(new Elevator1(idx,elevator.getHotelId(),0,elevator.getNumberOfFloors()));
		}
		return numberOfElevators + " new Elevators Added";
	}

	public void delete(int id) {
		elevatorRepository.deleteById(id);
	}
}