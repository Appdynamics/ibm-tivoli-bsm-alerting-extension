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
            command.addArgument(CommandConstants.SERVER,false);
            command.addArgument(config.getServer(),false);
        } else if(!Strings.isNullOrEmpty(config.getPathToConfig())){
            command.addArgument(CommandConstants.CONFIG);
            command.addArgument(config.getPathToConfig(),false);
        }
        command.addArgument(CommandConstants.SEVERITY,false);
        command.addArgument(alert.getSeverity(),false);
        command.addArgument(CommandConstants.MESSAGE,false);
        command.addArgument(alert.getMessage(),true);
        command.addArgument(CommandConstants.HOSTNAME + "=" ,false);
        command.addArgument(config.getHostname(),false);
        command.addArgument(CommandConstants.INSTANCE + "=",false);
        command.addArgument(config.getInstance(),true);
        command.addArgument(CommandConstants.FAULT + "=",false);
        command.addArgument(alert.getFault(),true);
        command.addArgument(config.getAlertGroup(),true);
        command.addArgument(alert.getIncidentId(),false);
        return command;
    }


}
