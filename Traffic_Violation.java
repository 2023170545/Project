package Project;

public class Traffic_Violation {
    private String violationID;
    private String vehicleID;
    private String violationType;
    private String date;
    private double fineAmount;
    
    public Traffic_Violation(String violationID, String vehicleID, String violationType, String date) {
        this.violationID = violationID;
        this.vehicleID = vehicleID;
        this.violationType = violationType;
        this.date = date;
        this.fineAmount = calculateFine(violationType);
    }

    public Traffic_Violation(String violationID, String vehicleID, String violationType, String date, double fineAmount) {
        this.violationID = violationID;
        this.vehicleID = vehicleID;
        this.violationType = violationType;
        this.date = date;
        this.fineAmount = fineAmount;
    }

    private double calculateFine(String violationType) {
        switch (violationType.toLowerCase()) {
            case "speeding":
                return 100.0;
            case "traffic light breaking":
                return 150.0;
            case "parking violation":
                return 50.0;
            default:
                return 75.0; // Default fine amount
        }
    }
        
    public String toCSV() {
        return violationID + "," + vehicleID + "," + violationType + "," + date;
    }
    

    public String getViolationID() {
        return violationID;
    }

    public void setViolationID(String violationID) {
        this.violationID = violationID;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getViolationType() {
        return violationType;
    }

    public void setViolationType(String violationType) {
        this.violationType = violationType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(double fineAmount) {
        this.fineAmount = fineAmount;
    }
    
    
}
