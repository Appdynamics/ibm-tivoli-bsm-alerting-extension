/*
 * Copyright 2018. AppDynamics LLC and its affiliates.
 * All Rights Reserved.
 * This is unpublished proprietary source code of AppDynamics LLC and its affiliates.
 * The copyright notice above does not evidence any actual or intended publication of such source code.
 */

package com.appdynamics.extensions.ibm.common;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class ConfigUtil<T> {


	public T readConfig(String configFilename,Class<T> clazz) throws FileNotFoundException {
        Yaml yaml = new Yaml(new Constructor(clazz));
        T config = (T) yaml.load(new FileInputStream(configFilename));
        return config;
    }
}
