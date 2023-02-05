package com.lucy.devproblem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucy.devproblem.exception.CustomException;
import com.lucy.devproblem.model.Client;
import com.lucy.devproblem.repository.ClientRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClientService {
	@Autowired ClientRepository clientRepository; 
	
	public Client saveClientDetails(Client clientRequest) {
		
		Client client = null;
	
		try {
			
			if(this.validateIDNumber(clientRequest.getIdNumber())) {
				if(this.validateMobileNumber(clientRequest.getMobileNumber())) {
					
					client = clientRepository.findByIdNumber(clientRequest.getIdNumber());
					
					if (client != null && client.getId() != clientRequest.getId()) {
						
						throw new CustomException("The ID number: " + clientRequest.getIdNumber() + " already exist");
					} else {
						
						client = clientRepository.findByMobileNumber(clientRequest.getMobileNumber());
						if (client != null && client.getId() != clientRequest.getId()) {

							throw new CustomException("The mobile number: " + clientRequest.getMobileNumber() + " already exist");
						} else {
							
							client = clientRepository.save(clientRequest);
						}
					}
				}
			}
			
			return client;
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw e;
		}
    }

	public Boolean validateIDNumber(String IDNumber) {
		
		if(IDNumber.length() == 13) {
			return this.validateRSAID(IDNumber);
		}else {
			throw new CustomException("The ID number you entered is incorrect, Please correct and try again");
		}
    }

	public Boolean validateMobileNumber(String mobileNumber) {
		boolean isValid = mobileNumber.matches("^(\\+27|0)[6-8][0-9]{8}$");
		
		if(isValid) {
			return true;
		}else {
			throw new CustomException("The mobile number you entered is incorrect, Please correct and try again");
		}
    }
	
	public Boolean validateRSAID(String identityNumber) {

	    int checkDigits = 0;
		char[] idCharArray = identityNumber.toString().toCharArray();
	    
	    for (int i = 1; i <= idCharArray.length; i++) {
	    	
	        int digit = Character.getNumericValue(idCharArray[idCharArray.length - i]);
	        if ((i % 2) != 0) {
	        	checkDigits += digit;
	        } else {
	        	checkDigits += digit < 5 ? digit * 2 : digit * 2 - 9;
	        }
	    }
	    
	    if(checkDigits % 10 == 0) {
	    	return true;
	    } else {
	    	throw new CustomException("The ID number you entered is incorrect, Please correct and try again");
	    }
    }
}
