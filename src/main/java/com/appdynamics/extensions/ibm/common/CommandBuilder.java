package com.appdynamics.extensions.ibm.common;


import com.appdynamics.extensions.ibm.Configuration;
import com.appdynamics.extensions.ibm.api.*;
import com.google.common.base.Strings;
import org.apache.commons.exec.CommandLine;

public class CommandBuilder {

    private static final String SEPARATOR = " ";

    public CommandLine buildCommand(Configuration config, Alert alert)  {
        String pathToExecutable = config.getPathToExecutable();
        CommandLine command = new CommandLine(pathToExecutable);
        if(!Strings.isNullOrEmpty(config.getServer())){
            command.addArgument(AlertConstants.SERVER + SEPARATOR + config.getServer(),false);
        } else if(!Strings.isNullOrEmpty(config.getPathToConfig())){
            command.addArgument(AlertConstants.CONFIG + SEPARATOR + config.getPathToConfig(),false);
        }
        command.addArgument(AlertConstants.SEVERITY + SEPARATOR + alert.getSeverity(),false);
        command.addArgument(AlertConstants.MESSAGE,false);
        command.addArgument(getMessageText(alert),true);
        command.addArgument(AlertConstants.HOSTNAME + "=" + config.getHostname(),false);
        command.addArgument(AlertConstants.INSTANCE + "=" + config.getInstance(),false);
        command.addArgument(AlertConstants.FAULT + "=" + getSubText(alert),false);
        command.addArgument(config.getAlertGroup(),false);
        command.addArgument(getIncidentId(alert),false);
        return command;
    }

    private String getSubText(Alert alert) {
        AlertDetails details = alert.getMessage();
        String subText = "";
        if(details instanceof AlertHeatlhRuleVioEventDetails){
            AlertHeatlhRuleVioEventDetails hrvDetails = (AlertHeatlhRuleVioEventDetails)details;
            subText = hrvDetails.getHealthRuleName();
        }
        else{
            AlertOtherEventDetails otherDetails = (AlertOtherEventDetails)details;
            subText = otherDetails.getEventNotificationName();
        }
        return "\"" + subText + "\"";
    }

    private String getIncidentId(Alert alert) {
        AlertDetails details = alert.getMessage();
        String incidentId = "";
        if(details instanceof AlertHeatlhRuleVioEventDetails){
            AlertHeatlhRuleVioEventDetails hrvDetails = (AlertHeatlhRuleVioEventDetails)details;
            incidentId = hrvDetails.getIncidentId();
        }
        else{
            AlertOtherEventDetails otherDetails = (AlertOtherEventDetails)details;
            incidentId = otherDetails.getEventNotificationId();
        }
        return incidentId;
    }

    private String getMessageText(Alert alert) {
        StringBuilder builder = new StringBuilder();
        builder.append(AlertConstants.APPNAME + alert.getMessage().getApplicationName() + ",");
        builder.append(AlertConstants.DEEP_LINK_URL + alert.getMessage().getDeepLinkUrl() + ",");
        AlertDetails details = alert.getMessage();
        if(details instanceof AlertHeatlhRuleVioEventDetails){
            AlertHeatlhRuleVioEventDetails hrvDetails = (AlertHeatlhRuleVioEventDetails)details;
            builder.append(AlertConstants.AFFECTED_ENTITY_NAME + hrvDetails.getAffectedEntityName() +  ",");
            builder.append(AlertConstants.RULE_NAME + hrvDetails.getHealthRuleName());
        }
        else{
            AlertOtherEventDetails otherDetails = (AlertOtherEventDetails)details;
            builder.append(AlertConstants.RULE_NAME + otherDetails.getEventNotificationName());
        }
        return builder.toString();
    }
}
