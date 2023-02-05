package com.lucy.devproblem.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "client")

public class Client {

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence")
    @Column(name = "id")
    @SequenceGenerator(name = "sequence", sequenceName = "client_sequence", allocationSize = 1)
    private long id;

    @Column(name = "first_name")
    private String firstname;
   
    @Column(name = "last_name")
    private String lastname;
    
    @Column(name = "mobile_number")
    private String mobileNumber;
    
    @Column(name = "id_number")
    private String idNumber;
    
    @Column(name = "physical_address")
    private String physicalAddress;
    
    @Override
   	public String toString() {
   		Gson gson = new GsonBuilder().setPrettyPrinting().create();
   		return gson.toJson(this);
   	}
    
}
