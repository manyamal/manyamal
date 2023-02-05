package com.lucy.devproblem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lucy.devproblem.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
	
	 @Query( value = "select c.id AS id, c.first_name, c.last_name, c.mobile_number, c.id_number, c.physical_address from Client c where LOWER(c.first_name) like lower(?1) OR c.id_number like lower(?1) OR c.mobile_number like lower(?1)", nativeQuery = true)
	    public Client findClient(String searchValue);
	 
	 @Query( value = "select c.id AS id, c.first_name, c.last_name, c.mobile_number, c.id_number, c.physical_address from Client c where c.id_number like lower(?1)", nativeQuery = true)
	    public Client findByIdNumber(String IdNumber);
	 
	 @Query( value = "select c.id AS id, c.first_name, c.last_name, c.mobile_number, c.id_number, c.physical_address from Client c where c.mobile_number like lower(?1)", nativeQuery = true)
	    public Client findByMobileNumber(String mobileNumber);

}
