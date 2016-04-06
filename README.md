# CF Sample App Clojure

A Clojure sample web application that can be deployed to Cloud Foundry

## Usage

First clone the repository on your local machine, then execute the command:

```cli
lein uberjar
```

This will compile the application and create a standalone jar file that can be deployed to Cloud Foundry using the java buildpack.
To run it locally:

```cli
lein run
```

To deploy it to Cloud Foundry:

```cli
cf push my-clojure-app -b java_buildpack -p target/cf-sample-app-clojure-0.1.0-SNAPSHOT-standalone.jar
```
