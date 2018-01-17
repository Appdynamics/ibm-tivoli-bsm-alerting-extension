ibm-tivoli-bsm-monitoring-extension
===================================

## Use Case

IBM Tivoli Business Service Manager monitors business services and tracks them against business objectives and technology infrastructures. 
It shows the operational status of services using prebuilt reports, scorecards and dashboards for fast data analysis. 
Tivoli Business Service Manager helps you assess service levels throughout an organization for more effective service management.

### Prerequisites

- You should have the postemsg executable and the posteifmsg.cfg config file on the controller machine.
- Based on the Operating System, create a cache file. 
  On UNIX,	$TIVOLIHOME/tec/cache where $TIVOLIHOME = /etc/Tivoli
  On Windows,	$TIVOLIHOME\tec\cache.dat	where $TIVOLIHOME= %SystemRoot%\system32\ drivers\etc\Tivoli

## Installation Steps

 1. Run "mvn clean install". 

 2. Find the zip file at 'target/ibm-tbsm-alert.zip' or Download the IBM TBSM Alerting Extension zip from [AppDynamics Exchange](http://community.appdynamics.com/t5/AppDynamics-eXchange/idb-p/extensions)

 3. Unzip the ibm-tbsm-alert.zip file into <CONTROLLER_HOME_DIR>/custom/actions/ . You should have  <CONTROLLER_HOME_DIR>/custom/actions/ibm-tbsm-alert created.

 4. Check if you have custom.xml file in <CONTROLLER_HOME_DIR>/custom/actions/ directory. If yes, add the following xml to the <custom-actions> element.
 
   ```
      <action>
    		  <type>ibm-tbsm-alert</type>
          <!-- For Linux/Unix *.sh -->
     		  <executable>ibm-tbsm-alert.sh</executable>
          <!-- For windows *.bat -->
     		  <!--<executable>ibm-tbsm-alert.bat</executable>-->
      </action>
  ```
     
   If you don't have custom.xml already, create one with the below xml content
    
      ```
      <custom-actions>
          <action>
      		  <type>ibm-tbsm-alert</type>
            <!-- For Linux/Unix *.sh -->
       		  <executable>ibm-tbsm-alert.sh</executable>
            <!-- For windows *.bat -->
       		  <!--<executable>ibm-tbsm-alert.bat</executable>-->
     	    </action>
        </custom-actions>
      ```
   Uncomment the appropriate executable tag based on windows or linux/unix machine.
    
 5. Update the config.yaml file with path to the "postemsg" executable.

    ### Note
    Please make sure to not use tab (\t) while editing yaml files. You may want to validate the yaml file using a yaml validator http://yamllint.com/

    	
        ```	
            #complete path to the binary or exe which includes the binary or exe. Use proper separators for Windows and Unix. For windows, escape the "\" char with another "\"
            # For eg. "C:\\IBM\\bin\\postemsg"
            pathToExecutable: ""
            
            #IBM TBSM server name or IP address.Either specify server or pathToConfig. Not both
            server: ""
            
            #Path to config. Either specify server or pathToConfig.Not both.Use proper separators for Windows and Unix. For windows, escape the "\" char with another "\"
            #For eg. "C:\\IBM\\bin\\posteifmsg.cfg"
            pathToConfig : ""
            
            #host name of the machine where the extension is installed. You can run "hostname" on the command line and get the hostname of the machine
            hostname: ""
            
            #Alert group for IBM Tivoli
            alertGroup: "AppDynamics"
            
            #Instance field for IBM Tivoli
            instance: "Controller"
            
            # timeout in seconds to execute command
            timeout: 10

        ```        
         
Below is a sample command that gets generated based on the above config and AppDynamics parameters.
<pathToExecutable> -f <pathToConfig> -r CRITICAL -m "APP_NAME:<APP_NAME>,URL:<URL>,AFFECTED_ENTITY_NAME:<AFFECTED_ENTITY_NAME>,RULE_NAME:<RULE_NAME>" 
hostname=<hostname> instance=<instance> fault="<SUMMARY>" <alertGroup> <incidentId>  


 6. Now you are ready to use this extension as a custom action. In the AppDynamics UI, go to Alert & Respond -> Actions. Click Create Action. Select Custom Action and click OK. In the drop-down menu you can find the action called 'ibm-tbsm-alert'.




## Contributing

Find out more in the [AppDynamics Exchange](https://www.appdynamics.com/community/exchange/extension/ibm-tivoli-business-service-manager-alerting-extension/)

## Support

For any questions or feature request, please contact [AppDynamics Support](mailto:help@appdynamics.com).

**Version:** 2.0.4
**Controller Compatibility:** 3.7+

