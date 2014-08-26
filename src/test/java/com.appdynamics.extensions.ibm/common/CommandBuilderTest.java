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
        System.out.print(commandLine.toString());
        String[] strings = commandLine.toStrings();
        List<String> commandList = Arrays.asList(strings);
        Assert.assertTrue(commandList.contains(StringUtils.fixFileSeparatorChar(PATH_TO_EXEC)));
        Assert.assertTrue(commandList.contains("-S 192.168.9.81"));
        Assert.assertTrue(commandList.contains("-r CRITICAL"));
        Assert.assertTrue(commandList.contains("Controller"));
        Assert.assertTrue(commandList.contains("AppDynamics"));
        Assert.assertTrue(commandList.contains("hostname=WIN-BV86U9ALKFB"));
        Assert.assertTrue(commandList.contains("-m"));
        //Assert.assertTrue(commandList.contains("'-m {\"Severity\":\"CRITICAL\",\"Evaluation Entities\":[]}'"));
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
        return hrv;
    }


}
