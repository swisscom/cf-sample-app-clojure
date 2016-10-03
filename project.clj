(defproject cf-sample-app-clojure "0.1.0-SNAPSHOT"
  :description "Sample Clojure application for Cloud Foundry"
  :author "Ivan Baldinotti"
  :email "ivan.baldinotti@swisscom.com"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.5.1"]
                 [ring/ring-defaults "0.2.1"]
                 [ring/ring-jetty-adapter "1.5.0"]
                 [hiccup "1.0.5"]]
  :plugins [[lein-ring "0.9.7"]]
  :ring {:handler cf-sample-app-clojure.core/app}
  :main cf-sample-app-clojure.core
  :aot [cf-sample-app-clojure.core])
