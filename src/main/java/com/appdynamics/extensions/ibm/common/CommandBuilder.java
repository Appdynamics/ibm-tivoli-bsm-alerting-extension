package com.appdynamics.extensions.ibm.common;


import com.appdynamics.extensions.ibm.Configuration;
import com.appdynamics.extensions.ibm.api.Alert;
import com.appdynamics.extensions.ibm.api.AlertConstants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.exec.CommandLine;

public class CommandBuilder {

    private static final String SEPARATOR = " ";

    public CommandLine buildCommand(Configuration config, Alert alert) throws JsonProcessingException {
        String pathToExecutable = config.getPathToExecutable();
        CommandLine command = new CommandLine(pathToExecutable);
        command.addArgument(AlertConstants.SEVERITY + SEPARATOR + alert.getSeverity());
        ObjectMapper om = new ObjectMapper();
        command.addArgument(AlertConstants.MESSAGE + SEPARATOR + om.writeValueAsString(alert.getMessage()));
        return command;
    }
}
