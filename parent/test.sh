#!/bin/bash

curl -H "Content-Type: application/json" -H "Accept=[application/json], Cache-Control=[no-cache], User-Agent=[Apache CXF 3.0.5], connection=[keep-alive], Pragma=[no-cache], type=[add], Content-Type=[application/json], Date=[Mon, 15 Aug 2016 18:27:29 GMT]" -X POST -d 'Customer {companyName=AMEX, region=SW, active=false, firstName=Andy, lastName=Reed, streetAddr=null, city=null, state=Arizona, zip=44747, phone=3847474789}' http://localhost:8080/payment-war/PersonEJB/addCustomer 

curl -H "Content-Type: application/json" -H "Accept=[application/json], Cache-Control=[no-cache], User-Agent=[Apache CXF 3.0.5], connection=[keep-alive], Pragma=[no-cache], type=[add], Content-Type=[application/json], Date=[Mon, 15 Aug 2016 18:27:29 GMT]" -X POST -d 'Cus74789]' http://localhost:8080/rest/PersonEJB/addCustomer

