```
mvn install
mvn dependency:copy-dependencies
java -cp "target/grepservermp-1.0-SNAPSHOT.jar:target/dependency/*" com.grepservermp.app.App *portnumber*
```