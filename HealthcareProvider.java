/*
 * ASU Spring 2024 CSE 360 11057
 * Authors: Haroon Radmard, Nicholas Abate, Aiden Felix, Jackson Silvey, Chirag Jagadish
 * File Version: 1.0.3
 * Original File Version: March 20, 2024
 * File Last Updated: April 9, 2024 
 * 
 * 1. File Description
 *  This is a helper file that stores the HealthcareProvider class. This class is an object 
 *  that represent a Health care worker for the clinic. This person could be the Doctor's who 
 *  practice this is or it could be a Nurse, this is anyone who is allowed to edit and look at 
 *  patient health information. 
 */

package asuJavaFX360;

class HealthcareProvider {
	//Attributes object should know about itself
		private String firstName;
		private String lastName;
		private String username;
		private String password;
	
	//constructor 
	public HealthcareProvider() {
		//Initializing all attributes to null 
		this.firstName = null;
		this.lastName = null;
		this.username = null;
		this.password = null;
    }
	
	//helper methods to setup all attributes with correct information 
		public void setFirstName(String name) { 
			this.firstName = name;
		}
		
		public void setLastName(String name) {
			this.lastName = name;
		}
		
		public void setUsername(String name) {
			this.username = name;
		}
		
		public void setPassword(String pass) {
			this.password = pass;
		}
		
		public String getUsername() {
			return this.username;
		}
		public String getPassword() {
			return this.password;
		}
		
		public String getFirstName() {
			return this.firstName;
		}
		
		public String getLastName() {
			return this.lastName;
		}
		
		//might be unused CHECK BEFORE DELETED 
		public boolean checkCredentials(String username, String password) {
	        return username.equals(this.username) && password.equals(this.password);
	    }
		//further implementation required 
		
		
		//THE FOLLOWING IS A TEST METHOD TO BE DELETED BEFORE FINAL PRODUCT 
		public void printAll() {
			System.out.println(this.firstName);
			System.out.println(this.lastName);
			System.out.println(this.username);
			System.out.println(this.password);
			
		}
}