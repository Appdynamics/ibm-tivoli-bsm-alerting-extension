package com.appdynamics.extensions.ibm.common;


import com.appdynamics.extensions.ibm.Configuration;
import com.appdynamics.extensions.ibm.api.Alert;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.log4j.Logger;

public class CommandExecutor {

    private CommandBuilder commandBuilder = new CommandBuilder();

	private static Logger logger = Logger.getLogger(CommandExecutor.class);


	public boolean execute(Configuration config, Alert alert) throws CommandExecutorException {

        CommandLine command = null;
        try {
            command = commandBuilder.buildCommand(config, alert);
        } catch (JsonProcessingException e) {
            logger.error("Unable to serialize json");
            return false;
        }
       // if(logger.isDebugEnabled()){

            StringBuilder commBuilder = new StringBuilder();
            for(String comm : command.toStrings()){
                commBuilder.append(comm + " ");
            }
            System.out.print("Command to be executed is :: " + commBuilder.toString());
            logger.info("Command to be executed is :: " + commBuilder.toString());
       // }
    	DefaultExecutor executor = new DefaultExecutor();
        executor.setExitValue(0); //set 0 as the success value
        ExecuteWatchdog watchdog = new ExecuteWatchdog(config.getTimeout() * 1000);
        executor.setWatchdog(watchdog);
    	try {
    		int exitValue = executor.execute(command);
            logger.debug("Exit Value" + exitValue);
            if(exitValue != 0){
                logger.error("Unable to generate alert. ExitValue = " + exitValue);
                return false;
            }
		} catch (ExecuteException e) {
			logger.error("Execution failed with exit value:" + e.getExitValue());
			throw new CommandExecutorException("Execution failed with exit value:" + e.getExitValue(), e);
		} catch(Exception e) {
			logger.error("Execution failed with message "+e.getMessage(), e);
			throw new CommandExecutorException("Execution failed with message "+e.getMessage(), e);
		}
        return true;
    }



}
