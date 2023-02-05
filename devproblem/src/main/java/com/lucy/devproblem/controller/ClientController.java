package com.lucy.devproblem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lucy.devproblem.model.Client;
import com.lucy.devproblem.repository.ClientRepository;
import com.lucy.devproblem.service.ClientService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
@RequestMapping(value = "/client", produces = "application/json")
public class ClientController {
	
	@Autowired ClientRepository clientRepository; 
	@Autowired ClientService clientService;
	
	@PostMapping("/save")
	@ApiOperation(value = "Creating the client")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully saved batch"),
			@ApiResponse(code = 400, message = "There's a problem with the request sent", response = Exception.class),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal error occured", response = Exception.class) })
	public @ResponseBody ResponseEntity<Object> 
	saveClient( @RequestParam(name = "firstname", required = true) String firstname, @RequestParam(name = "lastname", required = true) String lastname, @RequestParam(name = "idNumber", required = true) String idNumber, @RequestParam(name = "mobileNumber") String mobileNumber, @RequestParam(name = "physicalAddress") String physicalAddress) throws Exception {
		Client client = new Client();
		client.setFirstname(firstname);
		client.setIdNumber(idNumber);
		client.setLastname(lastname);
		client.setMobileNumber(mobileNumber);
		client.setPhysicalAddress(physicalAddress);
		
		try{
			Client clientResponse = clientService.saveClientDetails(client);
			return new ResponseEntity<Object>(clientResponse,HttpStatus.CREATED);
		}catch (Exception e) {
			return new ResponseEntity<>(new Exception(e.getLocalizedMessage()),HttpStatus.BAD_REQUEST);
		}
	}
	
	@PatchMapping("/update/{id}")
	@ApiOperation(value = "Updating the client")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully saved batch"),
			@ApiResponse(code = 400, message = "There's a problem with the request sent", response = Exception.class),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal error occured", response = Exception.class) })
	public @ResponseBody ResponseEntity<Object> 
	updateClient( @RequestParam(name = "firstname", required = true) String firstname, @RequestParam(name = "lastname", required = true) String lastname, @RequestParam(name = "idNumber", required = true) String idNumber, @RequestParam(name = "mobileNumber") String mobileNumber, @RequestParam(name = "physicalAddress") String physicalAddress, @PathVariable(required = true, name = "id") long id) throws Exception {
			Client client = new Client();
			client.setFirstname(firstname);
			client.setIdNumber(idNumber);
			client.setLastname(lastname);
			client.setMobileNumber(mobileNumber);
			client.setPhysicalAddress(physicalAddress);
			client.setId(id);
			
			try{
				Client clientResponse = clientService.saveClientDetails(client);
				return new ResponseEntity<Object>(clientResponse,HttpStatus.CREATED);
			}catch (Exception e) {
				return new ResponseEntity<>(new Exception(e.getLocalizedMessage()),HttpStatus.BAD_REQUEST);
			}
		}
	
	@GetMapping("/search")
	@ApiOperation(value = "Updating the client")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully saved batch"),
			@ApiResponse(code = 400, message = "There's a problem with the request sent", response = Exception.class),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "Internal error occured", response = Exception.class) })
	public @ResponseBody ResponseEntity<Object> 
	getClient( @RequestParam(name = "searchValue", required = true) String searchValue) throws Exception {
		
		try{
			Client clientResponse = clientRepository.findClient(searchValue);
			return new ResponseEntity<Object>(clientResponse,HttpStatus.CREATED);
		}catch (Exception e) {
			return new ResponseEntity<>(new Exception(e.getLocalizedMessage()),HttpStatus.BAD_REQUEST);
		}
	}

}
