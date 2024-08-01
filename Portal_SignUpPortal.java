/*
 * ASU Spring 2024 CSE 360 11057
 * Authors: Haroon Radmard, Nicholas Abate, Aiden Felix, Jackson Silvey, Chirag Jagadish
 * File Version: 1.0.0
 * Original File Version: April 8, 2024
 * File Last Updated: April 8, 2024 
 * 
 * 1. File Description:
 * The SignUpPortal class represents a user interface for creating a new account in the system.
 * This class extends the Portal class, inheriting basic portal functionalities.
 * Upon instantiation, a SignUpPortal object requires a Database object to interact 
 * with the system's database.
 */

package asuJavaFX360;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

class SignUpPortal extends Portal {
	private Database database;
    private Stage signUpStage;
	
    public SignUpPortal(Database database) { //MP standing for medical professional, P standing for patient) {
        super(); // calls the constructor of the parent class (Portal)
        this.database = database;
    }
   
    @Override
    public void displayInterface() {
    	
    	this.signUpStage = new Stage();
        System.out.println("Displaying Sign Up Portal with dimensions: " + xDimension + "x" + yDimension);
        // Implement interface display specific to SignUpPortal
        signUpStage.setTitle("Account Creation");

        // Create a grid pane and set its properties
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        // Username label and text field
        Label userNameLabel = new Label("Username:");
        grid.add(userNameLabel, 0, 1);
        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        // Password label and password field
        Label pwLabel = new Label("Password:");
        grid.add(pwLabel, 0, 2);
        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);

        // Confirm Password label and password field
        Label confirmPwLabel = new Label("Confirm Password:");
        grid.add(confirmPwLabel, 0, 3);
        PasswordField confirmPwBox = new PasswordField();
        grid.add(confirmPwBox, 1, 3);

        // Create Account button
        Button btn = new Button("Create Account");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_CENTER);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);
        
     // Back button
        Button backBtn = new Button("Back");
        HBox backBtnBox = new HBox(10);
        backBtnBox.setAlignment(Pos.BOTTOM_CENTER);
        backBtnBox.getChildren().add(backBtn);
        grid.add(backBtnBox, 1, 5);

        // Set action on button click and add validation for username, password, and confirm password fields
        btn.setOnAction(e -> {
            String username = userTextField.getText().trim();
            String password = pwBox.getText().trim();
            String confirmPassword = confirmPwBox.getText().trim();
            
        //Handle formatting of user entries
            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                showError("Please fill in all fields.");
            } else if (username.length() > 10) {
                showError("Username cannot be more than 10 characters.");
            } else if (!username.matches("^[a-zA-Z].*")) {
                showError("Username must start with an alphabet.");
            } else if (password.length() < 8 || password.length() > 15) {
                showError("Password must be between 8 and 15 characters long.");
            } else if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) {
                showError("Password must contain at least one special character.");
            } else if (!password.equals(confirmPassword)) {
                showError("Passwords do not match.");
            } else {
                signUp(username, password, confirmPassword);
            }
        });


     // Set action on "Back" button click
        backBtn.setOnAction(e -> {
            // Navigate back to the login portal
            LoginPortal loginPortal = new LoginPortal(database);
            loginPortal.displayInterface();
            signUpStage.close();
            System.out.println("Sign Up Portal Closed");
        });

        // Create scene and set it on the stage
        Scene scene = new Scene(grid, this.xDimension, this.yDimension);
        signUpStage.setScene(scene);
        signUpStage.show();
    }
    
    private void signUp(String username, String password, String cPassword) {//cPassword stands for the string of the confirmed password field 
    	
    	//create account checker object to ensure that username has not already been taken 
    	AccountChecker checker = new AccountChecker();
    	boolean nameFound = checker.isExistingUsername(username, this.database);
    	
    	if (nameFound) {
    		showError("Username already in use");
    	}
    	else {
    		//If there is no error then make a new patient object and add the 
    		//inputed username and password to the new object 
    		Patient newPatient = new Patient();
    		newPatient.setUsername(username);
    		newPatient.setPassword(password);
    		database.addPatient(newPatient);
    
    		//go back to login portal now that the account is created 
    		LoginPortal loginPortal = new LoginPortal(database);
    		loginPortal.displayInterface();
    		this.signUpStage.close();
    		System.out.println("SignUp Portal Closed");
    	}
    }
    
    //method for making error object and displaying inputed string when called.
    private void showError(String message) { 
    	ErrorGenerator errorGenerator = new ErrorGenerator(message);
    	errorGenerator.showError();
    }
}