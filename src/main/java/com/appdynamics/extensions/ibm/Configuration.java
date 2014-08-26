package com.appdynamics.extensions.ibm;

public class Configuration {
	
	private String pathToExecutable;
    private String server;
    private String alertGroup;
    private String hostname;
    private String agent;
    private int timeout;

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public String getPathToExecutable() {
        return pathToExecutable;
    }

    public void setPathToExecutable(String pathToExecutable) {
        this.pathToExecutable = pathToExecutable;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getAlertGroup() {
        return alertGroup;
    }

    public void setAlertGroup(String alertGroup) {
        this.alertGroup = alertGroup;
    }

    public String getAgent() {
        return agent;
    }

    public void setAgent(String agent) {
        this.agent = agent;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }
}
