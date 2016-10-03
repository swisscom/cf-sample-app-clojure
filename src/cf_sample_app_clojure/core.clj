(ns cf-sample-app-clojure.core
   (:gen-class)
   (:require [compojure.core :refer :all]
             [compojure.route :as route]
             [ring.adapter.jetty :as jetty]
             [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes app-routes
  (GET "/" [] "Welcome to Clojure on the Swisscom Application Cloud!")
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))

(defn -main
  [& [port]]
  (let [port (Integer. (or port (System/getenv "PORT") 3000))]
    (jetty/run-jetty #'app {:port  port
                            :join? false})))
