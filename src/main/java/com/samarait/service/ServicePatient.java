package com.samarait.service;




import java.util.List;

import com.samarait.dto.RequestDto;
import com.samarait.dto.ResponseDto;


public interface ServicePatient {
	
	List<ResponseDto>getAllPatient();
	ResponseDto getPatientById(Integer id);
	ResponseDto getPatientByEmail(String email);
	
	ResponseDto getPatientByEmailAndName(String email, String name);
	
	ResponseDto createPatient(RequestDto requestDto);
	
	void deletePatient(Integer id);
	
	ResponseDto updatePatient(Integer id, RequestDto requestDto);
	
	

}
