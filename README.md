## Selenium Test Framework

* Clean architecture
* Multi application support
* Restore session while developing to continue on same browser
* Extent reporting

### Install dependencies

```shell
mvn install -DskipTests
```

### Run All Suite

```shell
mvn clean test -Dheadless=true
```

### Run Specific Suite

```shell
mvn clean test -Dheadless=true -DsuiteFile=src/test/resources/suites/bookstore-tests.xml
```
