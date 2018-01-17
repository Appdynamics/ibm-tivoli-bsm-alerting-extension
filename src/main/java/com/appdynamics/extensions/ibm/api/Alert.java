package com.appdynamics.extensions.ibm.api;

public class Alert {
	
	private String message;
	
	private String severity;

    private String fault;

    private String incidentId;

    private String affectedEntity;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getFault() {
        return fault;
    }

    public void setFault(String fault) {
        this.fault = fault;
    }

    public String getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(String incidentId) {
        this.incidentId = incidentId;
    }

    public String getAffectedEntity() {
        return affectedEntity;
    }

    public void setAffectedEntity(String affectedEntity) {
        this.affectedEntity = affectedEntity;
    }
}
