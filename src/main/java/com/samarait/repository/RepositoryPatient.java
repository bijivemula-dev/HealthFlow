package com.samarait.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.samarait.model.Patient;

public interface RepositoryPatient extends JpaRepository<Patient, Integer>{
	
	
	Optional<Patient> findByEmail(String email);

	 Optional<Patient>findByPhoneNumber(String phoneNumber);
	 
//	 @Query("SELECT p FROM Patient WHERE p.email = :email AND p.name = :name")
//	 Optional<Patient> findByEmailAndName(
//			 @Param("email")String email, 
//			 @Param("name") String name);
	 
	 
	 Optional<Patient> findByNameContainingIgnoreCase(String namePart);

}
