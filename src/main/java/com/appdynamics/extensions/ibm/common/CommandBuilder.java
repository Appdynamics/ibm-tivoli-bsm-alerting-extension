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
        // app name + deeplink url + affected entity + HR name
        command.addArgument(CommandConstants.MESSAGE,false);
        command.addArgument(alert.getMessage(),true);
        // attributes
        command.addArgument(CommandConstants.HOSTNAME + "=" + "\"" + config.getHostname() + "\"",false);
        command.addArgument(CommandConstants.INSTANCE + "=" + "\"" + config.getInstance() + "\"",false);
        command.addArgument(CommandConstants.FAULT + "=" + "\"" + alert.getFault() + "\"",false);
        command.addArgument(CommandConstants.AFFECTED_ENTITY_NAME + "=" + "\"" + alert.getAffectedEntity() + "\"",false);
        // from config
        command.addArgument(config.getAlertGroup(),true);
        // event incident id
        command.addArgument(alert.getIncidentId(),false);
        return command;
    }


}
