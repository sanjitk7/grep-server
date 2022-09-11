# grep-server

Contains logic for the grep module and the server socket connection.

## Building the project

Execute the following steps in sequence for a new, clean build
```
$ mvn clean
$ mvn install
$ mvn dependency:copy-dependencies
$ mvn package
```

And finally to run the Jar, execute
```
$ java -cp "target/grepservermp-1.0-SNAPSHOT.jar:target/dependency/*" com.grepservermp.app.App *portnumber*
```

## Test cases

Test the unix4j library integration into the socket code. Most of the test cases are validation based, and check for whether a specific output is generated as part of the grep command input provided. 
