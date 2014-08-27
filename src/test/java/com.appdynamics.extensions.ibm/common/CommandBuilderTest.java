package com.appdynamics.extensions.ibm.common;


import com.appdynamics.extensions.ibm.Configuration;
import com.appdynamics.extensions.ibm.api.Alert;
import com.appdynamics.extensions.ibm.api.AlertDetails;
import com.appdynamics.extensions.ibm.api.AlertHeatlhRuleVioEventDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
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
    CommandBuilder builder = new CommandBuilder();
    ConfigUtil<Configuration> configUtil = new ConfigUtil<Configuration>();

    @Test
    public void canSuccessfullyBuildCommand() throws JsonProcessingException, FileNotFoundException {
        Configuration config = configUtil.readConfig(this.getClass().getResource("/conf/config.windows.yaml").getFile(),Configuration.class);
        CommandLine commandLine = builder.buildCommand(config,createAlert());
        String[] strings = commandLine.toStrings();
        List<String> commandList = Arrays.asList(strings);
        Assert.assertTrue(commandList.contains(StringUtils.fixFileSeparatorChar(PATH_TO_EXEC)));
        Assert.assertTrue(commandList.contains("-S 192.168.9.81"));
        Assert.assertTrue(commandList.contains("-r CRITICAL"));
        Assert.assertTrue(commandList.contains("123"));
        Assert.assertTrue(commandList.contains("AppDynamics"));
        Assert.assertTrue(commandList.contains("hostname=WIN-BV86U9ALKFB"));
        Assert.assertTrue(commandList.contains("-m"));
        Assert.assertTrue(commandList.contains("instance=Controller"));
        Assert.assertTrue(commandList.contains("fault=\'Hello World\'"));
    }


    @Test
    public void canSuccessfullyBuildCommandWhenServerInfoInConfig() throws JsonProcessingException, FileNotFoundException {
        Configuration config = configUtil.readConfig(this.getClass().getResource("/conf/config.windows_from_file.yaml").getFile(),Configuration.class);
        CommandLine commandLine = builder.buildCommand(config,createAlert());
        String[] strings = commandLine.toStrings();
        List<String> commandList = Arrays.asList(strings);
        Assert.assertTrue(commandList.contains(StringUtils.fixFileSeparatorChar(PATH_TO_EXEC)));
        Assert.assertTrue(commandList.contains("-f C:\\IBM\\bin\\posteifmsg.cfg"));
        Assert.assertTrue(commandList.contains("-r CRITICAL"));
        Assert.assertTrue(commandList.contains("123"));
        Assert.assertTrue(commandList.contains("AppDynamics"));
        Assert.assertTrue(commandList.contains("hostname=WIN-BV86U9ALKFB"));
        Assert.assertTrue(commandList.contains("-m"));
        Assert.assertTrue(commandList.contains("instance=Controller"));
        Assert.assertTrue(commandList.contains("fault=\'Hello World\'"));
    }



    private Alert createAlert() {
        Alert alert = new Alert();
        alert.setSeverity(CRITICAL);
        alert.setMessage(createAlertDetails());
        return alert;
    }

    private AlertDetails createAlertDetails() {
        AlertHeatlhRuleVioEventDetails hrv = new AlertHeatlhRuleVioEventDetails();
        hrv.setSeverity(CRITICAL);
        hrv.setIncidentId("123");
        hrv.setHealthRuleName("Hello World");
        return hrv;
    }


}
