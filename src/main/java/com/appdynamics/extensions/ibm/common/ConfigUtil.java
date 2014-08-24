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
