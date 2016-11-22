In order to run this project first you need to update the database.properties file in
src/main/webapp/database.properties with your mysql username and password.

then run mvn clean install(command line or eclipse) to build the war(you can then throw it on tomcat).
or run mvn clean intall jetty:run to build and run the war file in a jetty conatiner (Jetty uses localhohst 8080 same as tomcat).