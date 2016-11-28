# CF Sample App Clojure

A sample [Clojure](https://clojure.org/) application to deploy to Cloud Foundry which works out of the box.

## Run locally

1. Install the [Java JDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
1. Install [Leiningen](http://leiningen.org/)
1. Run `lein run`
1. Visit [http://localhost:3000](http://localhost:3000)

## Run in the cloud

1. Install the [Java JDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
1. Install [Leiningen](http://leiningen.org/)
1. Install the [cf CLI](https://github.com/cloudfoundry/cli#downloads)
1. Run `lein uberjar`
1. Run `cf create-service mongodb small my-mongodb`
1. Run `cf push --random-route`
1. Visit the given URL
