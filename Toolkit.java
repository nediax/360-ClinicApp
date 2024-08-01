/*
 * ASU Spring 2024 CSE 360 11057
 * Authors: Haroon Radmard, Nicholas Abate, Aiden Felix, Jackson Silvey, Chirag Jagadish
 * File Version: 1.0.2
 * Original File Version: March 20, 2024
 * File Last Updated: April 9, 2024 
 * 
 * 1. File Description
 *  This is a helper file that stores the extra class that do not fit in any other file. 
 *  Most of these classes will perform assistance with functionality of other classes.
 */

package asuJavaFX360;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/*This class is a helper class that calls upon the databases account authenticator in order to determine 
 * if the passed in username and password maps onto a valid object within the passed in database. */
class AccountChecker {
	//constructor
	public AccountChecker() {}
	
	/*This method checks if the login is a valid Health care provider within the system
	 * Returns a boolean that only returns true if and only if the inputed username AND password 
	 * match a valid username and password for a Health care provider account */
	public boolean isValidUserLoginHealthcareProvider(String username, String password, Database database) { 
		 return(database.authenticateHealthcareProvider(username, password));

	}
	
	//This method checks if the login is a valid Patient within the system. Returns true if such a Patient exists else, returns false. 
	public boolean isValidUserLoginPatient(String username, String password, Database database) { 
		 return(database.authenticatePatient(username, password));

	}
	
	public boolean isExistingUsername(String username, Database database) {
		return(database.authenticateUniqueUsername(username));
	}
	
	/* This method takes in a database, a username, and a password to determine 
     * if the inputed account has be correctly setup. If it has then it will return true 
     * if it has not then it will return false */
    public boolean isSignedUp(String username, String password, Database database) {
    	System.out.println(database.isSignedUp(username, password));//test line TO BE DELETED 
        return(database.isSignedUp(username, password));
    }
}

/* This class object will take on the main responsibility of generating the errors 
 * of the program. An error is generated when this error object is created then passed a string 
 * which will display the error. This is done by passing the inputed string to an Alert object
 * that is displayed to the user when there is an error. */
class ErrorGenerator {
	//Attribute 
	private String errorDisplayString;
	
	//constructor
	public ErrorGenerator(String error) {
		this.errorDisplayString = error;
	}
	
    public void showError() { //Error system: takes in a string to display that string to user as type of error 
    	Alert alert = new Alert(AlertType.ERROR);
    	alert.setTitle("Error");
    	alert.setHeaderText(null);
        alert.setContentText(errorDisplayString);
        alert.showAndWait();
    }
}