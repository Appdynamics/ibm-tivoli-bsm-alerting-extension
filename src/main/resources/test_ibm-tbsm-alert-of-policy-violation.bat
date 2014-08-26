set APP_NAME="ACME Online Bookstore Application"
set APP_ID=2
set PVN_ALERT_TIME="Tue Apr 17 14:02:55 PDT 2012"
set PRIORITY=1
set SEVERITY="ERROR"
set TAG="Response time policy violation"
set RULE_NAME="Business Transaction response time is much higher than normal"
set RULE_ID=8
set PVN_TIME_PERIOD_IN_MINUTES=5
set PVN_AFFECTED_ENTITY_TYPE="BUSINESS_TRANSACTION"
set PVN_AFFECTED_ENTITY_NAME="ViewCart.addToCart"
set PVN_AFFECTED_ENTITY_ID=86
set PVN_NUMBER_OF_EVALUATION_ENTITIES=2
set PVN_EVALUATION_ENTITY_TYPE_1="APPLICATION_COMPONENT_NODE"
set PVN_EVALUATION_ENTITY_NAME_1="Node_8003"
set PVN_EVALUATION_ENTITY_ID_1=3
set NUMBER_OF_TRIGGERED_CONDITIONS_1=0
set PVN_EVALUATION_ENTITY_TYPE_2="APPLICATION_COMPONENT_NODE"
set PVN_EVALUATION_ENTITY_NAME_2="Node_8000"
set PVN_EVALUATION_ENTITY_ID_2=2
set NUMBER_OF_TRIGGERED_CONDITIONS_2=2
set SCOPE_TYPE_1="APPLICATION_COMPONENT_NODE"
set SCOPE_NAME_1="Node_8000"
set SCOPE_ID_1=2
set PVN_TC_CONDITION_NAME_1="Average Response Time (ms) Baseline Condition"
set PVN_TC_CONDITION_ID_1=77
set OPERATOR_1="GREATER_THAN"
set CONDITION_UNIT_TYPE_1="ABSOLUTE"
set THRESHOLD_VALUE_1=5
set OBSERVED_VALUE_1=6
set SCOPE_TYPE_2="APPLICATION_COMPONENT_NODE"
set SCOPE_NAME_2="Node_8000"
set SCOPE_ID_2=2
set PVN_TC_CONDITION_NAME_2="Calls per Minute Condition"
set PVN_TC_CONDITION_ID_2=78
set OPERATOR_2="GREATER_THAN"
set CONDITION_UNIT_TYPE_2="ABSOLUTE"
set THRESHOLD_VALUE_2=50
set OBSERVED_VALUE_2=940
set SUMMARY_MESSAGE="Business Transaction response time is much higher than normal triggered at Tue Apr 17 14:02:55 PDT 2012. This policy was violated because the following set conditions were met for the ViewCart.addToCart Business Transaction for the last 5 minute(s):   For Evaluation Entity: Node_8003 Node  For Evaluation Entity: Node_8000 Node - set Average Response Time (ms) Baseline Condition is greater than 5. Observed value = 6 - Calls per Minute Condition is greater than 50. Observed value = 940"
set INCIDENT_ID=2
set DEEP_LINK_URL="http://WIN-FKL67IRSIPI:8090//#location=APP_INCIDENT_DETAIL&incident="
set EVENT_TYPE="POLICY_OPEN_CRITICAL"

call ibm-tbsm-alert %APP_NAME% %APP_ID% %PVN_ALERT_TIME% %PRIORITY% %SEVERITY% %TAG% %RULE_NAME% %RULE_ID% %PVN_TIME_PERIOD_IN_MINUTES% %PVN_AFFECTED_ENTITY_TYPE% %PVN_AFFECTED_ENTITY_NAME% %PVN_AFFECTED_ENTITY_ID% %PVN_NUMBER_OF_EVALUATION_ENTITIES% %PVN_EVALUATION_ENTITY_TYPE_1% %PVN_EVALUATION_ENTITY_NAME_1% %PVN_EVALUATION_ENTITY_ID_1% %NUMBER_OF_TRIGGERED_CONDITIONS_1% %PVN_EVALUATION_ENTITY_TYPE_2% %PVN_EVALUATION_ENTITY_NAME_2% %PVN_EVALUATION_ENTITY_ID_2% %NUMBER_OF_TRIGGERED_CONDITIONS_2% %SCOPE_TYPE_1% %SCOPE_NAME_1% %SCOPE_ID_1%  %PVN_TC_CONDITION_NAME_1% %PVN_TC_CONDITION_ID_1% %OPERATOR_1% %CONDITION_UNIT_TYPE_1% %THRESHOLD_VALUE_1% %OBSERVED_VALUE_1% %SCOPE_TYPE_2% %SCOPE_NAME_2% %SCOPE_ID_2%  %PVN_TC_CONDITION_NAME_2% %PVN_TC_CONDITION_ID_2% %OPERATOR_2% %CONDITION_UNIT_TYPE_2% %THRESHOLD_VALUE_2% %OBSERVED_VALUE_2% %SUMMARY_MESSAGE% %INCIDENT_ID% %DEEP_LINK_URL% %EVENT_TYPE%
