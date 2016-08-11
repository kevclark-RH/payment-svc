#!/bin/bash

mvn -X clean install -DskipTests 

rm ~/EAP-6.4.0/standalone/deployments/payment*

cd ../payment-ear/target

cp payment-ear-0.0.1-SNAPSHOT.ear ~/EAP-6.4.0/standalone/deployments

~/EAP-6.4.0/bin/standalone.sh
