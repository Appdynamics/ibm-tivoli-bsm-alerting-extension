package com.appdynamics.extensions.ibm.api;

public class Alert {
	
	private AlertDetails message;
	
	private String severity;

    public AlertDetails getMessage() {
        return message;
    }

    public void setMessage(AlertDetails message) {
        this.message = message;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }
}
