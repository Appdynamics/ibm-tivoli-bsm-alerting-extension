package com.appdynamics.extensions.ibm.common;


import com.appdynamics.extensions.ibm.Configuration;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;

public class ConfigUtilTest {
    ConfigUtil<Configuration> configUtil = new ConfigUtil<Configuration>();

    @Test
    public void canLoadLinuxConfigFile() throws FileNotFoundException {
        Configuration configuration = configUtil.readConfig(this.getClass().getResource("/conf/config.linux.yaml").getFile(),Configuration.class);
        Assert.assertTrue(configuration != null);
    }

    @Test
    public void canLoadWindowsConfigFile() throws FileNotFoundException {
        Configuration configuration = configUtil.readConfig(this.getClass().getResource("/conf/config.windows.yaml").getFile(),Configuration.class);
        Assert.assertTrue(configuration != null);
    }
}
