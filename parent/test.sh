#!/bin/bash

curl -H "Content-Type: application/json" -H "Accept: application/json" -X POST -d 'Customer {companyName=AMEX, region=SW, active=false, firstName=Andy, lastName=Reed, streetAddr=null, city=null, state=Arizona, zip=44747, phone=3847474789}' http://localhost:8080/payment-war/PersonEJB/addCustomer/ 

