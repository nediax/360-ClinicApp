/*
 * ASU Spring 2024 CSE 360 11057
 * Authors: Haroon Radmard, Nicholas Abate, Aiden Felix, Jackson Silvey, Chirag Jagadish
 * File Version: 1.0.3
 * Original File Version: April 8, 2024
 * File Last Updated: April 8, 2024 
 * 
 * 1. File Description:
 
 * This is a file that stores The LoginPortal class which represents a user interface for logging into a system. 
 * It provides functionality for users to input their username and password, 
 * login, or create a new account if none exists.
 * This class extends the Portal class, inheriting basic portal functionalities.
 * Upon instantiation, a LoginPortal object requires a Database object to interact 
 * with the system's database.
 */

package asuJavaFX360;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

class LoginPortal extends Portal {
    private Database database;
    private Stage loginStage;
	
    public LoginPortal(Database database) { //MP standing for medical professional, P standing for patient 
       super(); // calls the constructor of the parent class (Portal)
       this.database = database;
    }

    @Override
    public void displayInterface() {
    	System.out.println("Displaying Login Portal with dimensions: " + xDimension + "x" + yDimension);
    	//first create the stage
    	this.loginStage = new Stage();
    	loginStage.setTitle("Login Portal Interface");
    	
    	//input boxes for username and password
    	Label usernameLabel = new Label("Username:");
    	TextField usernameField = new TextField();
    	Label passwordLabel = new Label("Password:");
    	TextField passwordField = new TextField();
    	
    	//Preferred width for labels and text fields 
    	usernameLabel.setPrefWidth(this.xDimension / 4);
        passwordLabel.setPrefWidth(this.xDimension / 4);
        usernameField.setPrefWidth(this.xDimension / 4);
        passwordField.setPrefWidth(this.xDimension / 4);
    	
    	//login button
    	Button loginButton = new Button("Login");
    	
    	//new account button if account doesn't exist
    	Button createButton = new Button("Create New Account");
    	
    	   // Logic for login button action
        loginButton.setOnAction(event -> {
            // Validate username and password
            String username = usernameField.getText().trim();
            String password = passwordField.getText().trim();
            
            if (username.isEmpty() || password.isEmpty()) {
                showError("Username and password cannot be empty.");
            } else if (!username.matches("^[a-zA-Z].*")) {
                showError("Username must start with an alphabet.");
            } else {
                login(username, password);
            }
        });
    	
    	//logic for create account button
    	createButton.setOnAction(event -> signUp());
    	
    	// Create HBox for username input
        HBox usernameBox = new HBox(5);
        usernameBox.setAlignment(Pos.CENTER);
        usernameBox.getChildren().addAll(usernameLabel, usernameField);

        // Create HBox for password input
        HBox passwordBox = new HBox(5);
        passwordBox.setAlignment(Pos.CENTER);
        passwordBox.getChildren().addAll(passwordLabel, passwordField);

        // Create a VBox to organize components vertically
        VBox vBox = new VBox(10); // Set spacing between components
        vBox.setAlignment(Pos.CENTER); // Center-align components
        vBox.getChildren().addAll(usernameBox, passwordBox, loginButton, createButton);

    	
    	//set the Scene with the desired layout
    	Scene scene = new Scene(new StackPane(vBox), this.xDimension, this.yDimension);
    	this.loginStage.setScene(scene);
    	this.loginStage.show();
    }
    
    /*
     * Handles the action when the user clicks on the create account button.
     * It initializes a SignUpPortal instance and displays its interface, allowing 
     * the user to create a new account by providing necessary information.
     */ 
    private void signUp(){
    	SignUpPortal signupPortal = new SignUpPortal(database);
        signupPortal.displayInterface();
        this.loginStage.close();
        System.out.println("Login Screen Closed"); //test line TO BE DELETED 
    }
    
    /**
     * Handles the action when the user attempts to log in with the provided username and password.
     * Upon receiving the username and password, this method checks if the credentials correspond 
     * to a healthcare provider account, a patient account, or none. It then directs the user to 
     * the appropriate portal based on their account type or displays an error message if no account 
     * is found for the given credentials. */
    
    private void login(String username, String password) {
    	System.out.println("Login button clicked with username: " + username + " with password: " + password); //test line TO BE DELTED 
    	AccountChecker checker = new AccountChecker();
    	

    	// Check if the username and password correspond to a healthcare provider account
        if (checker.isValidUserLoginHealthcareProvider(username, password, database)) {
            // Initialize a HealthcareProviderPortal instance for healthcare provider access
            HealthcareProviderPortal healthcareProviderPortal = new HealthcareProviderPortal(database);
            
            // Display the interface of the HealthcareProviderPortal
            healthcareProviderPortal.displayInterface();
            
            // Close the login stage after successful login
            this.loginStage.close();
            
          //Keep track in console
            System.out.println("Login Screen Closed");
        }
        // Check if the username and password correspond to a patient account 
         
        else if (checker.isValidUserLoginPatient(username, password, database)) {
            // Check if the patient has completed the account registration process
            if (checker.isSignedUp(username, password, database)) {
                // Initialize a PatientPortal instance for patient access
                PatientPortal patientPortal = new PatientPortal(database, username);
                
                // Display the interface of the PatientPortal
                patientPortal.displayInterface();
                
                // Close the login stage after successful login
                this.loginStage.close();
                
              //test line TO BE DELETED 
                System.out.println("Login Screen Closed");
            } else { //if it has NOT gone through registration, patient is taken to Portal_PatientRegistrationPortal to complete
                // Initialize a RegistrationPortal instance for account registration
            	RegistrationPortal registrationPortal = new RegistrationPortal(database, username, password);
                
                // Display the interface of the RegistrationPortal for account registration
                registrationPortal.displayInterface();
                
                // Close the login stage after transitioning to the registration portal
                this.loginStage.close();
                
              //test line TO BE DELETED 
                System.out.println("Login Screen Closed");
            }
        } else {
            // Display an error message indicating invalid username or password
            showError("Invalid Username or Password");
            
          //test line TO BE DELETED 
            System.out.println("Not cool: You're trying to log in without a valid account!");
        }
    }
    
  //method for making error object and displaying inputed string when called.
    private void showError(String message) { 
    	ErrorGenerator errorGenerator = new ErrorGenerator(message);
    	errorGenerator.showError();
    }
}