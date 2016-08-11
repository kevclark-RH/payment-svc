#!/bin/bash

mvn -X clean install

rm /opt/jboss-eap-6.4/standalone/deployments/payment*

cd ../payment-ear/target

cp payment-ear-0.0.1-SNAPSHOT.ear /opt/jboss-eap-6.4/standalone/deployments

/opt/jboss-eap-6.4/bin/standalone.sh
