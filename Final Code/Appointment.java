package asuJavaFX360;

public class Appointment {
	
	//Instance variables
    private String date; 
    private String temperature; 
    private String height; 
    private String weight; 
    private String heartRate; 
    private String bloodPressure; 
    private String summaryOfVisit; 
    
    // Constructor (instead of setters for easy calling across classes)
    public Appointment(String date, String temperature, String height, String weight,
                       String heartRate, String bloodPressure, String summaryOfVisit) {
        this.date = date;
        this.temperature = temperature;
        this.height = height;
        this.weight = weight;
        this.heartRate = heartRate;
        this.bloodPressure = bloodPressure;
        this.summaryOfVisit = summaryOfVisit;
    }

    // Getters for Appointment attributes like date, patient temp., etc.
    public String getDate() {
        return date;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getHeight() {
        return height;
    }

    public String getWeight() {
        return weight;
    }

    public String getHeartRate() {
        return heartRate;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public String getSummaryOfVisit() {
        return summaryOfVisit;
    }
    
}

