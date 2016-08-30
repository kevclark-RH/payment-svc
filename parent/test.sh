#!/bin/bash

curl -H "Content-Type: application/json" -H "Accept: application/json" -X POST -d '{"companyName":"REALgrillz","region":"SW","active":false,"firstName":"Andy","lastName":"Reed","streetAddr":"666 Rowdon Ln.","city":"San Francisco","state":"CA","zip":"44747","phone":"3847474789"}' http://localhost:8080/v1/PersonEJB/addCustomer/	 

