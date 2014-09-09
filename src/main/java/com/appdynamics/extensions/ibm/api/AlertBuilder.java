package com.appdynamics.extensions.ibm.api;

import com.appdynamics.extensions.alerts.customevents.Event;
import com.appdynamics.extensions.alerts.customevents.HealthRuleViolationEvent;
import com.appdynamics.extensions.alerts.customevents.OtherEvent;
import com.appdynamics.extensions.ibm.Configuration;
import com.appdynamics.extensions.ibm.common.CommandConstants;
import org.apache.log4j.Logger;

/**
 * Builds an Alert from Health Rule violation event.
 */

public class AlertBuilder {

    private static Logger logger = Logger.getLogger(AlertBuilder.class);
    public static final String POLICY_CLOSE = "POLICY_CLOSE";

    public Alert buildAlertFromHealthRuleViolationEvent(HealthRuleViolationEvent violationEvent, Configuration config) {
        if(violationEvent != null && config != null){
            Alert alert = new Alert();
            alert.setSeverity(getSeverity(violationEvent.getEventType(), violationEvent.getSeverity()));
            alert.setFault(getFault(violationEvent));
            alert.setIncidentId(getIncidentId(violationEvent));
            alert.setMessage(getMessageText(violationEvent));
            return alert;
        }
        return null;
    }

    private String getSeverity(String eventType,String severity) {
        if(eventType != null && severity != null) {
            if (eventType.equalsIgnoreCase(POLICY_CLOSE)) {
                return "HARMLESS";
            } else if (severity.equalsIgnoreCase("WARN")) {
                return "WARNING";
            } else if (severity.equalsIgnoreCase("ERROR")) {
                return "CRITICAL";
            } else if (severity.equalsIgnoreCase("INFO")) {
                return "MINOR";
            }
        }
        return "UNKNOWN";
    }



    public Alert buildAlertFromOtherEvent(OtherEvent otherEvent, Configuration config) {
        if (otherEvent != null && config != null) {
            Alert alert = new Alert();
            alert.setSeverity(getSeverity(null, otherEvent.getSeverity()));
            alert.setFault(getFault(otherEvent));
            alert.setIncidentId(getIncidentId(otherEvent));
            alert.setMessage(getMessageText(otherEvent));
            return alert;
        }
        return null;
    }



    private String getFault(HealthRuleViolationEvent hrve) {
        return hrve.getHealthRuleName();
    }

    private String getFault(OtherEvent oe) {
        return oe.getEventNotificationName();
    }

    private String getIncidentId(HealthRuleViolationEvent hrve) {
        return hrve.getIncidentID();
    }


    private String getIncidentId(OtherEvent oe) {
        return oe.getEventNotificationId();
    }

    private String getMessageText(HealthRuleViolationEvent hrve) {
        StringBuilder builder = new StringBuilder("");
        Event event = (Event)hrve;
        builder.append(CommandConstants.APPNAME + event.getAppName() + ",");
        builder.append(CommandConstants.DEEP_LINK_URL + event.getDeepLinkUrl() + hrve.getIncidentID() + ",");
        builder.append(CommandConstants.AFFECTED_ENTITY_NAME + hrve.getAffectedEntityName() +  ",");
        builder.append(CommandConstants.RULE_NAME + hrve.getHealthRuleName());
        return builder.toString();
    }

    private String getMessageText(OtherEvent oe) {
        StringBuilder builder = new StringBuilder("");
        Event event = (Event)oe;
        builder.append(CommandConstants.APPNAME + event.getAppName() + ",");
        builder.append(CommandConstants.DEEP_LINK_URL + event.getDeepLinkUrl() + oe.getEventNotificationId() + ",");
        builder.append(CommandConstants.RULE_NAME + oe.getEventNotificationName());
        return builder.toString();
    }
}
