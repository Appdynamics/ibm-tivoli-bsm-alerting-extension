package com.appdynamics.extensions.ibm;


import com.appdynamics.extensions.alerts.customevents.EventBuilder;
import com.appdynamics.extensions.ibm.api.Alert;
import com.appdynamics.extensions.ibm.api.AlertBuilder;
import com.appdynamics.extensions.ibm.common.CommandExecutor;
import com.appdynamics.extensions.ibm.common.CommandExecutorException;
import com.appdynamics.extensions.ibm.common.ConfigUtil;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TBSMAlertExtensionTest {

    com.appdynamics.extensions.ibm.EventArgs eventArgs = new com.appdynamics.extensions.ibm.EventArgs();

    @Test
    public void canPostHRViolationEventWithMultipleEntityAndTriggerMultipleBaseline() throws FileNotFoundException, CommandExecutorException {
        TBSMAlertExtension alertExtension = getTBSMAlertExtension();
        Assert.assertTrue(alertExtension.processAnEvent(eventArgs.getHealthRuleViolationEventWithMultipleEvalEntityAndMultipleTriggerBaseline()));
    }


    @Test
    public void canPostOtherEvent() throws FileNotFoundException, CommandExecutorException {
        TBSMAlertExtension alertExtension = getTBSMAlertExtension();
        Assert.assertTrue(alertExtension.processAnEvent(eventArgs.getOtherEvent()));
    }

    @Test
    public void canPostHRViolationEventWithMultipleEvalEntityAndTriggerMultipleBaselineNoDetails() throws FileNotFoundException, CommandExecutorException {
        TBSMAlertExtension alertExtension = getTBSMAlertExtension();
        Assert.assertTrue(alertExtension.processAnEvent(eventArgs.getHealthRuleViolationEventWithMultipleEvalEntityAndMultipleTriggerBaseline()));
    }


    @Test
    public void canPostOtherEventWithNoDetails() throws FileNotFoundException, CommandExecutorException {
        TBSMAlertExtension alertExtension = getTBSMAlertExtension();
        Assert.assertTrue(alertExtension.processAnEvent(eventArgs.getOtherEvent()));
    }

    /*@Test
    public void integrationTest() throws FileNotFoundException {
        ConfigUtil<Configuration> configUtil = new ConfigUtil<Configuration>();
        Configuration config = configUtil.readConfig(this.getClass().getResource("/conf/config.linux.yaml").getFile(),Configuration.class);
        TBSMAlertExtension extension = new TBSMAlertExtension(config,new EventBuilder(),new AlertBuilder(),new CommandExecutor());
        Assert.assertTrue(extension.processAnEvent(eventArgs.getHealthRuleViolationEventWithMultipleEvalEntityAndMultipleTriggerBaseline()));
    }*/

    private TBSMAlertExtension getTBSMAlertExtension() throws CommandExecutorException {
        CommandExecutor commandExecutor = mock(CommandExecutor.class);
        Configuration config = createConfiguration();
        when(commandExecutor.execute((Configuration)any(),(Alert)any())).thenReturn(true);
        return new TBSMAlertExtension(config,new EventBuilder(),new AlertBuilder(),commandExecutor);
    }

    private Configuration createConfiguration(){
        Configuration configuration = new Configuration();
        configuration.setPathToExecutable("/usr/local/opcmsg");
        return configuration;
    }

}
