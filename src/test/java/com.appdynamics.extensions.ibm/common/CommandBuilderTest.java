package com.appdynamics.extensions.ibm.common;


import com.appdynamics.extensions.ibm.Configuration;
import com.appdynamics.extensions.ibm.api.Alert;
import com.appdynamics.extensions.ibm.api.AlertDetails;
import com.appdynamics.extensions.ibm.api.AlertHeatlhRuleVioEventDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.exec.CommandLine;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class CommandBuilderTest {

    public static final String PATH_TO_EXEC = "/usr/local/postemsg";
    public static final String CRITICAL = "CRITICAL";
    CommandBuilder builder = new CommandBuilder();

    @Test
    public void canSuccessfullyBuildCommand() throws JsonProcessingException {
        CommandLine commandLine = builder.buildCommand(createConfiguration(),createAlert());
        System.out.print(commandLine.toString());
        String[] strings = commandLine.toStrings();
        List<String> commandList = Arrays.asList(strings);
        Assert.assertTrue(commandList.contains(PATH_TO_EXEC));
        Assert.assertTrue(commandList.contains("\"-r "+ CRITICAL + "\""));
        Assert.assertTrue(commandList.contains("'-m {\"Severity\":\"CRITICAL\",\"Evaluation Entities\":[]}'"));
    }

    private Configuration createConfiguration(){
        Configuration configuration = new Configuration();
        configuration.setTimeout(10);
        configuration.setPathToExecutable(PATH_TO_EXEC);
        return configuration;
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
