/* 
 * ASU Spring 2024 CSE 360 11057
 * Authors: Haroon Radmard, Nicholas Abate, Aiden Felix, Jackson Silvey, Chirag Jagadish
 * Version: 1.0.1.0
 * Original Version: March 19, 2024
 * Last Updated: March 20, 2024
 * 
 * 1. Introduction
 * The following is the implementation of an office automation system for a pediatric doctor's office. The system is 
 * designed to be able to do the following. Provide, update, and store patient records. Establish communication 
 * between doctor and patient via a messaging terminal. And provide medication prescriptions. 
 * 	1.1 Clinic 49
 *   The following software system is designed for the fictional pediatric doctor's office of Clinic 49. This software is
 *   an academic exercise.
 *  1.2 Patient Experience
 *   TO BE ADDED 
 *  1.3 Medical Professional Experience 
 * 	 TO BE ADDED
 *  
 * 2. Detailed Design
 *  2.1 Interface Design
 *    2.1.1 GUI Design
 *     TO BE ADDED
 *    2.1.2 Web Design
 *     TO BE ADDED 
 *    2.1.3 Phone Application Design
 *     TO BE ADDED 
 *  2.2 Patient 
 *   TO BE ADDED
 *  2.3 Medical Professional
 *   TO BE ADDED
 *  2.4 Messaging Platform
 *   TO BE ADDED
 *  2.5 Patient Sign-Up
 *   TO BE ADDED 
 *  2.6 Patient Record Database
 *   TO BE ADDED 
 *   
 * 3. Further Notes
 * 	3.1  Github 
 *   Link to github repository: https://github.com/NicholasMAbate/CSE360_GroupProject49.git 
 *  
 *  
*/

package asuJavaFX360;

//import statements 
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	//Database that stores file system
	private static Database database = new Database();
	
    @Override
    public void start(Stage loginStage) {
        // Create frist-time LoginPortal and display it
        LoginPortal loginPortal = new LoginPortal(database);
        loginPortal.displayInterface();
        
        //HealthcareProvider admin = new HealthcareProvider();
        //admin.setUsername("Uadmin");
        //admin.setPassword("Padmin");
        //database.addHealthcareProvider(admin);
        
       // Patient testPatient = new Patient();
       // testPatient.setUsername("Tuser");
        //testPatient.setPassword("Tuser");
        //testPatient.setFirstName("John");
        //testPatient.setIsSetup();
       // database.addPatient(testPatient);
        
        
    }
    
    @Override
    public void init() {
    	/* Before the start of the display program is called in the previous method 
    	 * this method will load the stored file system onto the program so the programs database 
    	 * has access to all patients and health care workers */
    	database.loadFromFiles();
    	System.out.println("Loaded files!");
    }
    
    @Override
    public void stop() {
    	/* The moment before the program terminates the database is saved onto the file system in order to
    	 * store all new account creations and account changes. */
        database.saveToFiles();
        System.out.println("Program is closed");
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}