package com.emirates.employeePoc.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
* <h1>Employee Management</h1>
* The Employee Management implements an application that
* performs all CRUD operations, search operation, 
* bulk upload via excel sheet
* download list in excel sheet of current Employees.
* <p>
* <b>Note:</b> This Employee model class performs as Entity and VO object.
*
* @author  Paaks
* @version 1.0
* @since   2017-09-29
*/

@Document(collection = "employee")
public class Employee {
	
	@Id
	private String id;

	private String firstName;

	private String lastName;
	
	private String emailId;
	
	private String contact;
	
	private String dept;
	
	private String designation; 
	
	
	public Employee() {
	}

	
	public Employee(String id, String firstName, String lastName, String emailId, String contact, String dept,
			String designation) {
		super();
		this.id = id.toUpperCase();
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.contact = contact;
		this.dept = dept;
		this.designation = designation;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id.toUpperCase();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public String getContact() {
		return contact;
	}


	public void setContact(String contact) {
		this.contact = contact;
	}


	public String getDept() {
		return dept;
	}


	public void setDept(String dept) {
		this.dept = dept;
	}


	public String getDesignation() {
		return designation;
	}


	public void setDesignation(String designation) {
		this.designation = designation;
	}


	@Override
	public String toString() {
		StringBuilder employee = new StringBuilder();
		employee.append("Employee Id : ").append(this.getId()).append("\n");
		employee.append("First Name : ").append(this.getFirstName()).append("\n");
		employee.append("Last Name : ").append(this.getLastName()).append("\n");
		employee.append("Email ID : ").append(this.getEmailId()).append("\n");
		employee.append("Contact : ").append(this.getContact()).append("\n");
		employee.append("Department : ").append(this.getDept()).append("\n");
		employee.append("Designation : ").append(this.getDesignation());
		employee.append("\n");
		return employee.toString();
	}
}
