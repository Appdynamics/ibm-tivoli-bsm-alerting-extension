package com.appdynamics.extensions.ibm.common;


import com.appdynamics.extensions.ibm.Configuration;
import com.appdynamics.extensions.ibm.api.Alert;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.util.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

public class CommandBuilderTest {

    public static final String PATH_TO_EXEC = "C:\\IBM\\bin\\postemsg";
    public static final String CRITICAL = "CRITICAL";
    public static final String FAULT = "This is fault";
    public static final String MESSAGE = "This is msg";
    public static final String INCIDENT_ID = "123";
    CommandBuilder builder = new CommandBuilder();
    ConfigUtil<Configuration> configUtil = new ConfigUtil<Configuration>();

    @Test
    public void canSuccessfullyBuildCommand() throws FileNotFoundException {
        Configuration config = configUtil.readConfig(this.getClass().getResource("/conf/config.windows.yaml").getFile(),Configuration.class);
        Alert alert = createAlert();
        CommandLine commandLine = builder.buildCommand(config,alert);
        String[] strings = commandLine.toStrings();
        List<String> commandList = Arrays.asList(strings);
        Assert.assertTrue(commandList.contains(StringUtils.fixFileSeparatorChar(PATH_TO_EXEC)));
        Assert.assertTrue(commandList.contains(CommandConstants.SERVER));
        Assert.assertTrue(commandList.contains(config.getServer()));
        asserts(config, alert, commandList);
    }


    @Test
    public void canSuccessfullyBuildCommandWhenServerInfoInConfig() throws FileNotFoundException {
        Configuration config = configUtil.readConfig(this.getClass().getResource("/conf/config.windows_from_file.yaml").getFile(),Configuration.class);
        Alert alert = createAlert();
        CommandLine commandLine = builder.buildCommand(config,alert);
        String[] strings = commandLine.toStrings();
        List<String> commandList = Arrays.asList(strings);
        Assert.assertTrue(commandList.contains(StringUtils.fixFileSeparatorChar(PATH_TO_EXEC)));
        Assert.assertTrue(commandList.contains(CommandConstants.CONFIG));
        Assert.assertTrue(commandList.contains(config.getPathToConfig()));
        asserts(config, alert, commandList);
    }

    private void asserts(Configuration config, Alert alert, List<String> commandList) {
        Assert.assertTrue(commandList.contains(CommandConstants.SEVERITY));
        Assert.assertTrue(commandList.contains(alert.getSeverity()));
        Assert.assertTrue(commandList.contains(alert.getIncidentId()));
        Assert.assertTrue(commandList.contains(config.getAlertGroup()));
        Assert.assertTrue(commandList.contains(CommandConstants.HOSTNAME + "="));
        Assert.assertTrue(commandList.contains(config.getHostname()));
        Assert.assertTrue(commandList.contains(CommandConstants.MESSAGE));
        Assert.assertTrue(commandList.contains("\""+ alert.getMessage() + "\""));
        Assert.assertTrue(commandList.contains(CommandConstants.INSTANCE + "="));
        Assert.assertTrue(commandList.contains(config.getInstance()));
        Assert.assertTrue(commandList.contains(CommandConstants.FAULT + "="));
        Assert.assertTrue(commandList.contains("\"" + alert.getFault() + "\""));
    }


    private Alert createAlert() {
        Alert alert = new Alert();
        alert.setSeverity(CRITICAL);
        alert.setFault(FAULT);
        alert.setMessage(MESSAGE);
        alert.setIncidentId(INCIDENT_ID);
        return alert;
    }



}
