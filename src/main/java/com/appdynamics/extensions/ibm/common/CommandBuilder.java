package com.appdynamics.extensions.ibm.common;


import com.appdynamics.extensions.ibm.Configuration;
import com.appdynamics.extensions.ibm.api.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.exec.CommandLine;

import java.io.File;

public class CommandBuilder {

    private static final String SEPARATOR = " ";

    public CommandLine buildCommand(Configuration config, Alert alert) throws JsonProcessingException {
        String pathToExecutable = config.getPathToExecutable();
        CommandLine command = new CommandLine(pathToExecutable);
        command.addArgument(AlertConstants.SERVER + SEPARATOR + config.getServer(),false);
        command.addArgument(AlertConstants.SEVERITY + SEPARATOR + alert.getSeverity(),false);
        command.addArgument(AlertConstants.MESSAGE,false);
        command.addArgument(getMessageText(alert),true);
        command.addArgument(AlertConstants.HOSTNAME + "=" + config.getHostname(),false);
        command.addArgument(config.getAlertGroup(),false);
        command.addArgument(config.getAgent(),false);
        return command;
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
