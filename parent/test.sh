#!/bin/bash

curl -H "Content-Type: application/json" -H "Headers: {Accept=[application/json], Cache-Control=[no-cache], User-Agent=[Apache CXF 3.0.5], connection=[keep-alive], Host=[localhost:9192], Pragma=[no-cache], type=[add], Content-Type=[application/json], Date=[Mon, 15 Aug 2016 18:27:29 GMT]}" -X POST -d 'Customer [companyName=AMEX, region=SW, active=false, firstName=Andy, lastName=Reed, streetAddr=null, city=null, state=Arizona, zip=44747, phone=3847474789]' http://localhost:9192/webservices/rest/PersonEJB/addCustomer 

