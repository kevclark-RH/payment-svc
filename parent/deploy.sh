#!/bin/bash

mvn clean install

rm /opt/jboss-eap-6.4/standalone/deployments/Payment*

cd ../payment-war/target

cp PaymentWAR.war /opt/jboss-eap-6.4/standalone/deployments

/opt/jboss-eap-6.4/bin/standalone.sh
