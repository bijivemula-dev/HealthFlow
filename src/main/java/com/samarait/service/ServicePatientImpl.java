package com.samarait.service;


import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;


import com.samarait.configurations.AppConfiguration;
import com.samarait.customException.EmailAlreadyExistException;
import com.samarait.dto.RequestDto;
import com.samarait.dto.ResponseDto;
import com.samarait.model.Patient;
import com.samarait.repository.RepositoryPatient;
import com.samarait.customException.PatientNotFoundException;

@Service
public class ServicePatientImpl implements ServicePatient{

	private static final String PATIENTS_CACHE = "patients";
	private static final String PATIENT_BY_ID_CACHE = "patientById";
	private static final String PATIENT_BY_EMAIL_CACHE = "patientByEmail";
	private static final Logger logger = LoggerFactory.getLogger(ServicePatientImpl.class);


	private final ModelMapper modelMapper;
	
	private RepositoryPatient repositoryPatient;
	
	private AppConfiguration appConfiguration;
	
	
	
	public ServicePatientImpl(RepositoryPatient repositoryPatient, AppConfiguration appConfiguration, ModelMapper modelMapper) {
		this.repositoryPatient = repositoryPatient;
		this.appConfiguration = appConfiguration;
		this.modelMapper = modelMapper;
	}
	
	
	@CacheEvict(value= {PATIENTS_CACHE, PATIENT_BY_ID_CACHE, PATIENT_BY_EMAIL_CACHE}, allEntries = true)
	@Override
	public ResponseDto createPatient(RequestDto requestDto) {
		
		repositoryPatient.findByEmail(requestDto.getEmail())
				.ifPresent( pati-> {
					throw new EmailAlreadyExistException("email already present");
				});
		

		
		
	  Patient patient = 	modelMapper.map(requestDto, Patient.class);
	  repositoryPatient.save(patient);
	  logger.info("New patient registered");
		
		return modelMapper.map(patient, ResponseDto.class);
	}

	@Cacheable(value=PATIENTS_CACHE, key="'all'")
	@Override
	public List<ResponseDto> getAllPatient() {
		
		List<Patient> patients = repositoryPatient.findAll();
		System.out.println("data is comming from the database");
		logger.info("get all patients");
	  return  patients.stream()
	    .map(patient -> modelMapper.map(patient, ResponseDto.class))
	    .collect(Collectors.toList());
	
	
	}


	@Cacheable(value=PATIENT_BY_ID_CACHE, key="#id")
	@Override
	public ResponseDto getPatientById(Integer id) {
		 logger.debug("Database access - Fetching patient with ID: {}", id);
	    Patient patient = 	repositoryPatient.findById(id)
		        .orElseThrow(()  -> new PatientNotFoundException("Patient not found with ID: " + id));
	    
	    return modelMapper.map(patient, ResponseDto.class);

	
	}
	

	@Cacheable(value=PATIENT_BY_EMAIL_CACHE, key="#email")
	@Override
	public ResponseDto getPatientByEmail(String email) {
	Patient patient = 	repositoryPatient.findByEmail(email).orElseThrow(() ->
			new EmailAlreadyExistException("email not found " + email)
		);
		return modelMapper.map(patient, ResponseDto.class);
	}
	
	


	@Override
	@Caching(evict = {
			@CacheEvict(value = PATIENT_BY_ID_CACHE, key="#id"),
			@CacheEvict(value = PATIENTS_CACHE, allEntries = true)
	})
	public void deletePatient(Integer id) {
		
		if(!repositoryPatient.existsById(id)) {
			throw new PatientNotFoundException("patient not found with id " + id);
		}
		
		repositoryPatient.deleteById(id);
	}
	
	

    @Caching(evict = {
            @CacheEvict(value = PATIENT_BY_ID_CACHE, key = "#id"),
            @CacheEvict(value = PATIENT_BY_EMAIL_CACHE, key = "#result.email"),
            @CacheEvict(value = PATIENTS_CACHE, allEntries = true)
        })
	@Override
	public ResponseDto updatePatient(Integer id, RequestDto requestDto) {
	    Patient existingPatient = repositoryPatient.findById(id)
	            .orElseThrow(() -> new PatientNotFoundException("Patient not found with ID: " + id));
	
	    modelMapper.map(requestDto, existingPatient);
	    

	    Patient updatedPatient = repositoryPatient.save(existingPatient);
	    
	    return modelMapper.map(updatedPatient, ResponseDto.class);
	}
	

     
    
     
      
	

	@Override
	public ResponseDto getPatientByEmailAndName(String email, String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
