package com.appdynamics.extensions.ibm.common;


import com.appdynamics.extensions.ibm.Configuration;
import com.appdynamics.extensions.ibm.api.Alert;
import com.google.common.base.Strings;
import org.apache.commons.exec.CommandLine;

public class CommandBuilder {

    private static final String SEPARATOR = " ";

    public CommandLine buildCommand(Configuration config, Alert alert)  {
        String pathToExecutable = config.getPathToExecutable();
        CommandLine command = new CommandLine(pathToExecutable);
        if(!Strings.isNullOrEmpty(config.getServer())){
            command.addArgument(CommandConstants.SERVER + SEPARATOR + config.getServer(),false);
        } else if(!Strings.isNullOrEmpty(config.getPathToConfig())){
            command.addArgument(CommandConstants.CONFIG + SEPARATOR + config.getPathToConfig(),false);
        }
        command.addArgument(CommandConstants.SEVERITY + SEPARATOR + alert.getSeverity(),false);
        command.addArgument(CommandConstants.MESSAGE + " " + "\"" + alert.getMessage() + "\"",false);
        command.addArgument(CommandConstants.HOSTNAME + "=" + config.getHostname(),false);
        command.addArgument(CommandConstants.INSTANCE + "=" + config.getInstance(),false);
        command.addArgument(CommandConstants.FAULT + "=" + "\"" + alert.getFault() + "\"",false);
        command.addArgument(config.getAlertGroup(),false);
        command.addArgument(alert.getIncidentId(),false);
        return command;
    }


}
