package com.samarait.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.samarait.dto.RequestDto;
import com.samarait.dto.ResponseDto;
import com.samarait.model.Patient;
import com.samarait.service.ServicePatientImpl;

//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/patients") 
//@Tag(name ="Patient Controller", description = "APIs for patient operations")
public class PatientController {
	
	@Autowired
	private ServicePatientImpl  servicePatientImpl;
	
	@PostMapping
	public ResponseEntity<ResponseDto> createPatient(@RequestBody RequestDto requestDto) {
		ResponseDto responseDto =servicePatientImpl .createPatient(requestDto);
		
		return ResponseEntity.ok().body(responseDto);
		
	}
	
	@GetMapping
	public ResponseEntity<List<ResponseDto>> getAllPatients(){
		
		List<ResponseDto>	list = servicePatientImpl.getAllPatient();
		
		
		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseDto> getPatient(@PathVariable Integer id){
		
		ResponseDto responseDto =  servicePatientImpl.getPatientById(id);
		System.out.println("data is comming from the database");
		
		return ResponseEntity.ok().body(responseDto);
			
		
	}
	
	@GetMapping("/email/{email}")
	public ResponseEntity<ResponseDto> getPatientEmail(@PathVariable String email){
		ResponseDto responseDto	 = servicePatientImpl.getPatientByEmail(email);
		
		
		return ResponseEntity.ok().body(responseDto);
	}
	
//	@Operation(
//			summary = "Delete a patient by ID",
//			description = "Delete a patient recorf if the ID exists"
//			)
//	
//	@ApiResponses({
//		@ApiResponse(responseCode = "204", description = "Patient deleted successfully"),
//		@ApiResponse(responseCode = "404", description = "Patient not found"),
//		@ApiResponse(responseCode = "500", description = "Internal server error")
//	})
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePatient(@PathVariable Integer id){
		
		servicePatientImpl.deletePatient(id);
		
		return ResponseEntity.noContent().build();
		
		
	}

	
	@PutMapping("/{id}")
	public ResponseEntity<ResponseDto> updatePatient(
	        @PathVariable Integer id,
	        @RequestBody RequestDto requestDto) {
	    
	    ResponseDto responseDto = servicePatientImpl.updatePatient(id, requestDto);
	    return ResponseEntity.ok(responseDto);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
