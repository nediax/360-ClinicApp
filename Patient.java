/*
 * ASU Spring 2024 CSE 360 11057
 * Authors: Haroon Radmard, Nicholas Abate, Aiden Felix, Jackson Silvey, Chirag Jagadish
 * File Version: 1.0.3
 * Original File Version: March 20, 2024
 * File Last Updated: April 9, 2024 
 * 
 * 1. File Description
 *  This is a helper file that stores the Patient class. The Patient class stores vital 
 *  information about the patient for the clinic to know. The patient is the general account 
 *  for those who are looking to use the clinic's system. The patient is only able to edit information about itself
 *  and only able to look at information about itself.   
 */

package asuJavaFX360;

import java.util.ArrayList;
import java.util.List;

class Patient {
	//Initializing all attributes to null 
	private String firstName = null;
	private String lastName = null;
	private String fullName = null;
	private String username = null;
	private String password = null;
	private String patientID = null;
	private String email = null;
	private String medicalHistory = null;
	private String phoneNumber = null;
	private int age;
	private String address = null;
	private String DOB = null;
	private String InsuranceID = null;
	private String Pharmacy = null;
	private List<Appointment> appointments = new ArrayList<>();
	private boolean isSetup = false; //only turned true when all other attributes have been correctly initialized 
	
	public void setIsSetup() {
		if (this.firstName != null) {
			isSetup = true; //if the first name is has been made then it is determined that RegistrationPortal has completed for this patient
		}
	}
	//helper methods to setup all attributes with correct information 
	public void setFirstName(String name) { 
		this.firstName = name;
	}
	
	public void setLastName(String name) {
		this.lastName = name;
	}
	
	public void setFullName(String first, String last) {
		this.fullName = first + " " + last;
	}
	
	public void setUsername(String name) {
		this.username = name;
	}
	
	public void setPassword(String pass) {
		this.password = pass;
	}
	
	public void setEmail(String mail) {
		this.email = mail;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setMedicalHistory(String history) {
		this.medicalHistory = history;
	}
	
	public void setPhoneNumber(String pNum) {
		this.phoneNumber = pNum;
	}
	
	public void setDOB(String dob) {
		this.DOB = dob;
	}
	
	public void setInsuranceID(String ID) {
		this.InsuranceID = ID;
	}
	
	public void setPharmacy(String pharmacy) {
		this.Pharmacy = pharmacy;
	}
	
	public void setPatientID(String id) {
		this.patientID = id;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
    public void addAppointment(Appointment appointment) {
        if (appointment != null) {
            this.appointments.add(appointment);
        }
    }

    // Method to remove an appointment from the list
    public boolean removeAppointment(Appointment appointment) {
        return this.appointments.remove(appointment);
    }

    // Method to get the list of appointments
    public List<Appointment> getAppointments() {
        return new ArrayList<>(this.appointments); // Return a copy to avoid direct manipulation
    }
    
    public String getAppointmentsAsString() {
        StringBuilder result = new StringBuilder();
        for (Appointment appointment : getAppointments()) { 
            
            result.append(String.format(
                "Date: %s, Temperature: %s, Height: %s cm, Weight: %s kg, Heart Rate: %s bpm, Blood Pressure: %s, Summary: %s\n\n",
                appointment.getDate(), 
                appointment.getTemperature(),
                appointment.getHeight(),
                appointment.getWeight(),
                appointment.getHeartRate(),
                appointment.getBloodPressure(),
                appointment.getSummaryOfVisit()
            ));
        }
        return result.toString().trim(); // Trim to remove the last newline characters
    }
    
    public void recreateAppointmentsFromString(String appointmentData) {
        if (appointmentData == null || appointmentData.trim().isEmpty()) {
            return; // No data to parse.
        }

        this.appointments = new ArrayList<>(); // Resetting or initializing the appointments list.

        // Split the appointmentData string into individual appointment strings.
        String[] appointmentStrings = appointmentData.split("\n\n");
        for (String singleAppointmentData : appointmentStrings) {
            try {
                // For each appointment, split the details.
                // Need to ensure we don't split by commas within the Summary text.
                String[] details = singleAppointmentData.split(", (?![^\\[]*\\])"); // Split by commas not within brackets if Summary includes commas.

                String date = extractValue(details[0], "Date: ");
                String temperature = extractValue(details[1], "Temperature: ");
                String height = extractValue(details[2], "Height: ").replace(" cm", ""); // Remove redundant units
                String weight = extractValue(details[3], "Weight: ").replace(" kg", ""); // Remove redundant units
                String heartRate = extractValue(details[4], "Heart Rate: ").replace(" bpm", ""); // Remove redundant units
                String bloodPressure = extractValue(details[5], "Blood Pressure: ");
                // Assuming summary might be the last part and could have commas itself.
                String summary = details.length > 6 ? singleAppointmentData.substring(singleAppointmentData.indexOf("Summary: ") + "Summary: ".length()) : "";

                Appointment appointment = new Appointment(date, temperature, height, weight, heartRate, bloodPressure, summary);
                this.appointments.add(appointment);
            } catch (Exception e) {
                e.printStackTrace(); // Log exception
            }
        }
    }


    // Helper method to extract value after a label
    private String extractValue(String detail, String label) {
        return detail.substring(detail.indexOf(label) + label.length()).trim();
    }

	
	//helper methods to get attributes 
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public String getFullName() {
		return this.fullName;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	//MIGHT NEED TO BE DELETED 
	public boolean checkCredentials(String username, String password) {
        return username.equals(this.username) && password.equals(this.password);
    }
	
	public boolean getIsSetup() {
		return this.isSetup;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public String getDOB() {
		return this.DOB;
	}
	
	public String getMedicalHistory() {
		return "No major operations; no known severe allergies";
	}
	
	public String getPharmacy() {
		return this.Pharmacy;
	}
	
	public String getInsuranceID() {
		return this.InsuranceID;
	}
	
	public String getPatientID() {
		return this.patientID;
	}
	
	//THE FOLLOWING IS A TEST METHOD TO BE DELETED BEFORE FINAL PRODUCT 
	public void printAll() {
		System.out.println(this.firstName);
		System.out.println(this.lastName);
		System.out.println(this.username);
		System.out.println(this.password);
	}
	
}