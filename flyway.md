## FLYWAY DATABASE MIGRATION

spring.jpa.hibernate.ddl-auto= **validate**

This property is set to _**validate**_ so that when we start our application, spring boot doesn't _create_, _create-drop_, 
or _update_ our schema. It will just validate without making any changes to the schema. Which allows flyway to serve its 
purpose of validating and alerting us if anything changes.

* In the **resources**' folder, create a new folder called _**db.migration**_